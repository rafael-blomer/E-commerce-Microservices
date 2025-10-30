package br.com.rafaelblomer.business.converters;

import br.com.rafaelblomer.business.dtos.ProdutoCadastroDTO;
import br.com.rafaelblomer.business.dtos.ProdutoResponseDTO;
import br.com.rafaelblomer.infrastructure.entities.Produto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {
    public Produto paraProdutoEntidade(@Valid ProdutoCadastroDTO cadastroDTO, Long idUsuario) {
        return new Produto(
                cadastroDTO.nome(),
                cadastroDTO.descricao(),
                cadastroDTO.preco(),
                cadastroDTO.quantidadeTotal(),
                idUsuario);
    }

    public ProdutoResponseDTO paraProdutoResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidadeTotal(),
                produto.getIdUsuario());
    }
}
