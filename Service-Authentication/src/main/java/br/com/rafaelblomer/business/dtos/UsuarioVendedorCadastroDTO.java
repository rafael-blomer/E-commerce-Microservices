package br.com.rafaelblomer.business.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioVendedorCadastroDTO(@NotBlank String nome,
                                         @NotBlank @Email String email,
                                         @NotBlank String senha,
                                         @NotBlank String cnpj) {
}
