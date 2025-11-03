package br.com.rafaelblomer.business;

import br.com.rafaelblomer.business.converters.ProdutoConverter;
import br.com.rafaelblomer.business.dtos.*;
import br.com.rafaelblomer.business.exceptions.AcaoNaoPermitidaException;
import br.com.rafaelblomer.business.exceptions.ObjetoNaoEncontradoException;
import br.com.rafaelblomer.infrastructure.entities.Produto;
import br.com.rafaelblomer.infrastructure.client.UsuarioClient;
import br.com.rafaelblomer.infrastructure.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoConverter produtoConverter;

    @Autowired
    private UsuarioClient usuarioClient;

    public ProdutoResponseDTO criarNovoProduto(String token, @Valid ProdutoCadastroDTO cadastroDTO) {
        UsuarioDTO usuario = buscarUsuarioPorToken(token);
        Produto produto = produtoConverter.paraProdutoEntidade(cadastroDTO, usuario.id());
        repository.save(produto);
        return produtoConverter.paraProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO adicionarQuantidadeDeProduto(String token, ProdutoMovimentacaoEstoqueDTO movimentacaoDTO) {
        UsuarioDTO usuario = buscarUsuarioPorToken(token);
        Produto produto = buscarProdutoPorId(movimentacaoDTO.idProduto());
        verificarProdutoUsuario(produto, usuario);
        produto.setQuantidadeTotal(produto.getQuantidadeTotal() + movimentacaoDTO.quantidade());
        repository.save(produto);
        return produtoConverter.paraProdutoResponseDTO(produto);
    }

    //RabbitMQ
    public void retirarQuantidadeDeProduto(ProdutoMovimentacaoRetiradaDTO movimentacaoDTO) {
        Produto produto = buscarProdutoPorId(movimentacaoDTO.idProduto());
        produto.setQuantidadeTotal(produto.getQuantidadeTotal() - movimentacaoDTO.quantidadeComprada());
        repository.save(produto);
    }

    public ProdutoResponseDTO buscarUmProdutoPorId(Long id) {
        return produtoConverter.paraProdutoResponseDTO(buscarProdutoPorId(id));
    }

    //ÚTEIS

    private UsuarioDTO buscarUsuarioPorToken(String token) {
        return usuarioClient.buscarUsuarioToken(token).getBody();
    }

    private void verificarProdutoUsuario(Produto produto, UsuarioDTO usuario) {
        if (!produto.getIdUsuario().equals(usuario.id()))
            throw new AcaoNaoPermitidaException("Você não tem permissão para realizar essa ação.");
    }

    private Produto buscarProdutoPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("O produto não foi encontrado"));
    }

    public Page<ProdutoResponseDTO> buscarTodosProdutos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(produtoConverter::paraProdutoResponseDTO);
    }
}
