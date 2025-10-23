package br.com.rafaelblomer.infrastructure.security;

import java.time.Instant;
import java.util.stream.Collectors;

import br.com.rafaelblomer.business.exceptions.ObjetoNaoEncontradoException;
import br.com.rafaelblomer.infrastructure.entities.Usuario;
import br.com.rafaelblomer.infrastructure.repositories.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;
    private final UsuarioRepository usuarioRepository;

    public JwtService(JwtEncoder encoder, JwtDecoder decoder, UsuarioRepository usuarioRepository) {
        this.encoder = encoder;
        this.decoder = decoder;
        this.usuarioRepository = usuarioRepository;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 3600L; // 1 hora

        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("ecommerce-service")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scopes) // <-- roles vão aqui
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String extrairEmailToken(String token) {
        Jwt jwt = decoder.decode(token);
        return jwt.getSubject();
    }

    public String extrairRoleToken(String token) {
        Jwt jwt = decoder.decode(token);
        return (String) jwt.getClaims().get("scope");
    }

    public Usuario extrairUsuarioToken(String token) {
        Jwt jwt = decoder.decode(token);
        String email = jwt.getSubject();
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ObjetoNaoEncontradoException("Usuário não encontrado: " + email));
    }
}
