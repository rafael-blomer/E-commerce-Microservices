package br.com.rafaelblomer.business.dtos;

import br.com.rafaelblomer.infrastructure.entities.enums.RoleUsuario;

import java.util.List;

public record UsuarioVendedorResponseDTO (Long id,
                                          String nome,
                                          String email,
                                          RoleUsuario tipoUsuario,
                                          String cnpj) implements UsuarioDefaultResponseDTO {
}
