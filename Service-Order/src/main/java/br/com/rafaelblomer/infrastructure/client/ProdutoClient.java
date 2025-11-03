package br.com.rafaelblomer.infrastructure.client;

import br.com.rafaelblomer.business.dtos.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Service-Produto", url = "${service-product.url}")
public interface ProdutoClient {

    @GetMapping("/buscarporid/{id}")
    ResponseEntity<ProdutoDTO> buscarUmProduto(@RequestHeader("Authorization") String token, @PathVariable Long id);
}
