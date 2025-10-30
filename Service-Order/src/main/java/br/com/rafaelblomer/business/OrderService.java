package br.com.rafaelblomer.business;

import br.com.rafaelblomer.business.converters.OrderConverter;
import br.com.rafaelblomer.business.dtos.OrderRequestDTO;
import br.com.rafaelblomer.business.dtos.OrderResponseDTO;
import br.com.rafaelblomer.business.dtos.ProdutoDTO;
import br.com.rafaelblomer.business.dtos.UsuarioDTO;
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

    public OrderResponseDTO criarNovaOrdem(String token, @Valid OrderRequestDTO orderDTO) {
        UsuarioDTO usuario = usuarioClient.buscarUsuarioToken(token).getBody();
        ProdutoDTO produto = produtoClient.buscarUmProduto(orderDTO.idProduto()).getBody();
        Order order = converter.paraOrderEntidade(orderDTO, usuario, produto);
        repository.save(order);
        return converter.paraOrderResponseDTO(order);
    }
}
