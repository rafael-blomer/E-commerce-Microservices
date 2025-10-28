package br.com.rafaelblomer.business.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoCadastroDTO(@NotBlank String nome,
                                 @NotBlank String descricao,
                                 @NotNull Double preco,
                                 @NotNull Integer quantidadeTotal) {
}
