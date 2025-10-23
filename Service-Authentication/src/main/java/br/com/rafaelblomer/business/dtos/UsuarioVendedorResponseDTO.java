package br.com.rafaelblomer.business.dtos;

import br.com.rafaelblomer.infrastructure.entities.enums.RoleUsuario;

import java.util.List;

public record UsuarioVendedorResponseDTO (String nome,
                                          String email,
                                          RoleUsuario tipoUsuario,
                                          String cnpj,
                                          List<Long> idsProdutos) implements UsuarioDefaultResponseDTO {
}
