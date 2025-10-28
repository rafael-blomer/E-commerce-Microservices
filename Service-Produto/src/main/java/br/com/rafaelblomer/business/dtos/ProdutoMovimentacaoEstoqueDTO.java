package br.com.rafaelblomer.business.dtos;

import jakarta.validation.constraints.NotNull;

public record ProdutoMovimentacaoEstoqueDTO(@NotNull Long idProduto,
                                            @NotNull Integer quantidade) {
}
