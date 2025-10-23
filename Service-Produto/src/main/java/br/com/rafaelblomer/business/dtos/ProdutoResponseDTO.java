package br.com.rafaelblomer.business.dtos;

import br.com.rafaelblomer.infrastructure.entities.enums.StatusProduto;

public record ProdutoResponseDTO(String nome,
                                 String descricao,
                                 Double preco,
                                 Integer quantidade,
                                 StatusProduto status,
                                 Long idUsuario) {
}
