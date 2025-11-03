package br.com.rafaelblomer.business.dtos;

public record MessageEmailCompraDTO(String nomeComprador,
                                    String emailComprador,
                                    String emailVendedor,
                                    String nomeProduto,
                                    Integer quantidadeComprada,
                                    Double precoTotal) {
}
