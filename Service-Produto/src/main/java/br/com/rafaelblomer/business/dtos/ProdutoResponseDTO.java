package br.com.rafaelblomer.business.dtos;


public record ProdutoResponseDTO(Long id,
                                 String nome,
                                 String descricao,
                                 Double preco,
                                 Integer quantidadeDisponivel,
                                 Long idUsuario) {
}
