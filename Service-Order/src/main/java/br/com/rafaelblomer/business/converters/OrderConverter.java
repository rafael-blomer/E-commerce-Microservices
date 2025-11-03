package br.com.rafaelblomer.business.converters;

import br.com.rafaelblomer.business.dtos.OrderRequestDTO;
import br.com.rafaelblomer.business.dtos.OrderResponseDTO;
import br.com.rafaelblomer.business.dtos.ProdutoDTO;
import br.com.rafaelblomer.business.dtos.UsuarioDTO;
import br.com.rafaelblomer.infrastructure.entities.Order;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderConverter {

    public OrderResponseDTO paraOrderResponseDTO(Order order, ProdutoDTO produto, UsuarioDTO usuario) {
        return new OrderResponseDTO(
                order.getDataCompra(),
                produto.nome(),
                order.getQuantidadeComprada(),
                produto.preco(),
                produto.preco() * order.getQuantidadeComprada(),
                usuario.nome(),
                usuario.email(),
                usuario.endereco());
    }

    public Order paraOrderEntidade(@Valid OrderRequestDTO orderDTO, UsuarioDTO usuario, ProdutoDTO produto) {
        return Order.builder()
                .dataCompra(LocalDateTime.now())
                .idComprador(usuario.id())
                .idVendedor(produto.idUsuario())
                .idProduto(produto.id())
                .quantidadeComprada(orderDTO.quantidadeComprada())
                .build();
    }
}
