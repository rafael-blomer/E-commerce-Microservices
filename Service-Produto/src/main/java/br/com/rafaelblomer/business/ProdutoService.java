package br.com.rafaelblomer.business;

import br.com.rafaelblomer.business.converters.ProdutoConverter;
import br.com.rafaelblomer.business.dtos.ProdutoCadastroDTO;
import br.com.rafaelblomer.business.dtos.ProdutoResponseDTO;
import br.com.rafaelblomer.business.dtos.UsuarioDTO;
import br.com.rafaelblomer.infrastructure.entities.Produto;
import br.com.rafaelblomer.infrastructure.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoConverter produtoConverter;

    public ProdutoResponseDTO criarNovoProduto(String token, @Valid ProdutoCadastroDTO cadastroDTO) {
        Produto produto = produtoConverter.paraProdutoEntidade(cadastroDTO);
        repository.save(produto);
        return produtoConverter.paraProdutoResponseDTO(produto);
    }

    //ÚTEIS

    private UsuarioDTO buscarUsuarioPorToken(String token) {
        return null;
    }
}
