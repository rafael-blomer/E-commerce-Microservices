package br.com.rafaelblomer.business;

import br.com.rafaelblomer.business.converters.UsuarioConverter;
import br.com.rafaelblomer.business.dtos.*;
import br.com.rafaelblomer.infrastructure.entities.Comprador;
import br.com.rafaelblomer.infrastructure.entities.Usuario;
import br.com.rafaelblomer.infrastructure.entities.Vendedor;
import br.com.rafaelblomer.infrastructure.repositories.UsuarioRepository;
import br.com.rafaelblomer.infrastructure.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioConverter converter;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public UsuarioVendedorResponseDTO criarNovoVendedor(@Valid UsuarioVendedorCadastroDTO vendedorDTO) {
        Vendedor vendedorEntity = converter.paraVendedorEntidade(vendedorDTO);
        vendedorEntity.setSenha(encoder.encode(vendedorEntity.getSenha()));
        repository.save(vendedorEntity);
        return converter.paraVendedorResponseDTO(vendedorEntity);
    }

    public UsuarioCompradorResponseDTO criarNovoComprador(@Valid UsuarioCompradorCadastroDTO compradorDTO) {
        Comprador compradorEntity = converter.paraCompradorEntidade(compradorDTO);
        compradorEntity.setSenha(encoder.encode(compradorEntity.getSenha()));
        repository.save(compradorEntity);
        return converter.paraCompradorResponseDTO(compradorEntity);
    }

    public String realizarLogin(@Valid UsuarioLoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtService.generateToken(authentication);
        return "Bearer " + jwtToken;
    }

    public UsuarioDefaultResponseDTO buscarUsuarioDTOPorTokenJWT(String token) {
        Usuario usuario = buscarUsuarioEntidadePorToken(token);
        if (usuario instanceof Comprador comprador)
            return converter.paraCompradorResponseDTO(comprador);
        else if (usuario instanceof Vendedor vendedor)
            return converter.paraVendedorResponseDTO(vendedor);
        else
            throw new IllegalArgumentException("Tipo de usuário desconhecido");
    }

    //ÚTEIS

    private Usuario buscarUsuarioEntidadePorToken(String token) {
        return jwtService.extrairUsuarioToken(token.substring(7));
    }
}
