package br.com.rafaelblomer.controllers;

import br.com.rafaelblomer.business.ProdutoService;
import br.com.rafaelblomer.business.dtos.ProdutoCadastroDTO;
import br.com.rafaelblomer.business.dtos.ProdutoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criarproduto")
    ResponseEntity<ProdutoResponseDTO> criarNovoProduto(@RequestHeader("Authorization") String token, @Valid @RequestBody ProdutoCadastroDTO cadastroDTO) {
        return ResponseEntity.ok().body(produtoService.criarNovoProduto(token, cadastroDTO));
    }
}
