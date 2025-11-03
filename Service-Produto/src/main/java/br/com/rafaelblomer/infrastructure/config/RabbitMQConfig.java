package br.com.rafaelblomer.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.exchange.product.update}")
    private String OrderExchange;

    @Value("${broker.queue.product.update}")
    private String atualizarQuantidadeProdutoQueue;

    @Value("${broker.routing.product.update}")
    private String routingKeyAtualizarQuantidadeProduto;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(OrderExchange);
    }

    @Bean
    public Queue atualizarProdutoQueue() {
        return new Queue(atualizarQuantidadeProdutoQueue, true);
    }

    @Bean
    public Binding bindingAtualizarProduto(Queue atualizarProdutoQueue, DirectExchange exchange) {
        return BindingBuilder.bind(atualizarProdutoQueue).to(exchange).with(routingKeyAtualizarQuantidadeProduto);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
