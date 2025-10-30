package br.com.rafaelblomer.controllers;

import br.com.rafaelblomer.business.ProdutoService;
import br.com.rafaelblomer.business.dtos.ProdutoCadastroDTO;
import br.com.rafaelblomer.business.dtos.ProdutoMovimentacaoEstoqueDTO;
import br.com.rafaelblomer.business.dtos.ProdutoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criarproduto")
    ResponseEntity<ProdutoResponseDTO> criarNovoProduto(@RequestHeader("Authorization") String token, @Valid @RequestBody ProdutoCadastroDTO cadastroDTO) {
        return ResponseEntity.ok().body(produtoService.criarNovoProduto(token, cadastroDTO));
    }

    @PatchMapping("/adicionarproduto")
    ResponseEntity<ProdutoResponseDTO> adicionarQuantidadeDeProduto(@RequestHeader("Authorization") String token, @Valid @RequestBody ProdutoMovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {
        return ResponseEntity.ok().body(produtoService.adicionarQuantidadeDeProduto(token, movimentacaoEstoqueDTO));
    }

    @GetMapping("/buscarprodutos")
    ResponseEntity<Page<ProdutoResponseDTO>> buscarTodosProdutos(Pageable pageable) {
        return ResponseEntity.ok().body(produtoService.buscarTodosProdutos(pageable));
    }

    @GetMapping("/buscarporid/{id}")
    ResponseEntity<ProdutoResponseDTO> buscarUmProduto(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoService.buscarUmProdutoPorId(id));
    }
}
