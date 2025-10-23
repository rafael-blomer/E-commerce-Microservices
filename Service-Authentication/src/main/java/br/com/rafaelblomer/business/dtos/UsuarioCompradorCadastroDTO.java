package br.com.rafaelblomer.business.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCompradorCadastroDTO (@NotBlank String nome,
                                           @NotBlank @Email String email,
                                           @NotBlank String senha,
                                           @NotBlank String cpf,
                                           @NotBlank String endereco){
}
