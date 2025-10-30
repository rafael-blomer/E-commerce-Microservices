package br.com.rafaelblomer.business.dtos;


public record ProdutoDTO(String nome,
                         String descricao,
                         Double preco,
                         Integer quantidadeDisponivel,
                         Long idUsuario) {
}
