package br.com.rafaelblomer.business.dtos;

import java.time.LocalDateTime;

public record OrderResponseDTO(LocalDateTime dataHoraCompra,
                               String nomeProduto,
                               Integer quantidadeComprada,
                               Double precoPorUnidade,
                               Double precoTotal,
                               String nomeComprador,
                               String emailComprador,
                               String enderecoComprador) {
}
