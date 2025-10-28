package br.com.rafaelblomer.business.dtos;


public record ProdutoResponseDTO(String nome,
                                 String descricao,
                                 Double preco,
                                 Integer quantidadeDisponivel,
                                 Long idUsuario) {
}
