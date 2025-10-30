package br.com.rafaelblomer.business.dtos;

public record UsuarioDTO(Long id,
                         String nome,
                         String email,
                         String cnpj) {
}
