package br.com.rafaelblomer.business;

import br.com.rafaelblomer.business.converters.OrderConverter;
import br.com.rafaelblomer.business.dtos.*;
import br.com.rafaelblomer.business.exceptions.RetiradaProdutoIlegalException;
import br.com.rafaelblomer.business.producers.OrderProducer;
import br.com.rafaelblomer.infrastructure.client.ProdutoClient;
import br.com.rafaelblomer.infrastructure.client.UsuarioClient;
import br.com.rafaelblomer.infrastructure.entities.Order;
import br.com.rafaelblomer.infrastructure.repositories.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderConverter converter;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private ProdutoClient produtoClient;

    @Autowired
    private OrderProducer orderProducer;

    public OrderResponseDTO criarNovaOrdem(String token, @Valid OrderRequestDTO orderDTO) {
        UsuarioDTO comprador = buscarUsuarioToken(token);
        ProdutoDTO produto = buscarProdutoId(token, orderDTO.idProduto());
        UsuarioDTO vendedor = usuarioClient.buscarUsuarioId(produto.idUsuario()).getBody();
        verificarQuantidadeRetirada(produto.quantidadeDisponivel(), orderDTO.quantidadeComprada());
        Order order = converter.paraOrderEntidade(orderDTO, comprador, produto);
        repository.save(order);
        Double precoTotal = produto.preco() * order.getQuantidadeComprada();
        orderProducer.publicarMensagemCompraEmail(new MessageEmailCompraDTO(comprador.nome(), comprador.email(), vendedor.email(), produto.nome(), order.getQuantidadeComprada(), precoTotal));
        orderProducer.publicarMensagemEstoqueProduto(new MessageProdutoCompradoDTO(produto.id(), order.getQuantidadeComprada()));
        return converter.paraOrderResponseDTO(order, produto, comprador);
    }

    //Úteis

    private void verificarQuantidadeRetirada(Integer quantidadeTotalProduto, Integer quantidadeASerRetirada) {
        if (quantidadeASerRetirada > quantidadeTotalProduto)
            throw new RetiradaProdutoIlegalException("Existem menos produtos em estoque do que a quantidade desejada.");
    }

    private UsuarioDTO buscarUsuarioToken(String token) {
        return usuarioClient.buscarUsuarioToken(token).getBody();
    }


    private ProdutoDTO buscarProdutoId(String token, Long idProduto) {
        return produtoClient.buscarUmProduto(token, idProduto).getBody();
    }
}
