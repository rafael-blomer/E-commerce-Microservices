package br.com.rafaelblomer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(
                                "/service-authentication/criarvendedor",
                                "/service-authentication/login",
                                "/service-authentication/criarcomprador",
                                "/service-authentication/buscarporid/**"
                        ).permitAll()
                        .anyExchange().permitAll() // 👈 deixa o gateway só rotear
                );

        return http.build();
    }
}
