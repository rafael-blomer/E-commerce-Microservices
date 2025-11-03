package br.com.rafaelblomer.business.producers;

import br.com.rafaelblomer.business.dtos.MessageEmailCompraDTO;
import br.com.rafaelblomer.business.dtos.MessageProdutoCompradoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.exchange.email.sendorder}")
    private String emailExchange;

    @Value("${broker.routing.email.sendorder}")
    private String routingKeyEmail;

    @Value("${broker.exchange.product.update}")
    private String produtoExchange;

    @Value("${broker.routing.product.update}")
    private String routingKeyProduto;

    public void publicarMensagemCompraEmail(MessageEmailCompraDTO dto) {
        rabbitTemplate.convertAndSend(emailExchange, routingKeyEmail, dto);
    }

    public void publicarMensagemEstoqueProduto(MessageProdutoCompradoDTO dto) {
        rabbitTemplate.convertAndSend(produtoExchange, routingKeyProduto, dto);
    }
}
