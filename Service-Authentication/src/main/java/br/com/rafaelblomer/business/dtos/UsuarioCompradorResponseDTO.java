package br.com.rafaelblomer.business.dtos;

import br.com.rafaelblomer.infrastructure.entities.enums.RoleUsuario;

public record UsuarioCompradorResponseDTO(Long id,
                                          String nome,
                                          String email,
                                          RoleUsuario tipoUsuario,
                                          String cpf,
                                          String endereco) implements UsuarioDefaultResponseDTO {
}
