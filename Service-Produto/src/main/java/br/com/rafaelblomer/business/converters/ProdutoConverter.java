package br.com.rafaelblomer.business.converters;

import br.com.rafaelblomer.business.dtos.ProdutoCadastroDTO;
import br.com.rafaelblomer.business.dtos.ProdutoResponseDTO;
import br.com.rafaelblomer.infrastructure.entities.Produto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {
    public Produto paraProdutoEntidade(@Valid ProdutoCadastroDTO cadastroDTO) {
        return new Produto();
    }

    public ProdutoResponseDTO paraProdutoResponseDTO(br.com.rafaelblomer.infrastructure.entities.Produto produto) {
    }
}
