package br.com.rafaelblomer.business.dtos;

import jakarta.validation.constraints.NotNull;

public record OrderRequestDTO(@NotNull Long idProduto,
                              @NotNull Integer quantidadeComprada) {
}
