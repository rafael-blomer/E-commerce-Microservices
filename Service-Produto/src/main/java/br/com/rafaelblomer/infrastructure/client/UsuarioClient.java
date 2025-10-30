package br.com.rafaelblomer.infrastructure.client;

import br.com.rafaelblomer.business.dtos.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Service-Authentication", url = "${service-authentication.url}")
public interface UsuarioClient {

    @GetMapping("/buscarusuario")
    ResponseEntity<UsuarioDTO> buscarUsuarioToken(@RequestHeader("Authorization") String token);
}
