package br.com.rafaelblomer.business.dtos;

public sealed interface UsuarioDefaultResponseDTO
        permits UsuarioVendedorResponseDTO, UsuarioCompradorResponseDTO {
}
