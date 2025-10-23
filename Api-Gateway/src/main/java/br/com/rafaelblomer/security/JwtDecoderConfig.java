package br.com.rafaelblomer.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwtDecoderConfig {

    @Value("${jwt.public.pem}")
    private RSAPublicKey publicKey;

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withPublicKey(publicKey).build();
    }
}
