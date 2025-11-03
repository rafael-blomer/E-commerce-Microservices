package br.com.rafaelblomer.infrastructure.configs;

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

    @Value("${broker.exchange.email.sendorder}")
    private String OrderExchange;

    @Value("${broker.queue.email.sendorder}")
    private String enviarEmailQueue;

    @Value("${broker.routing.email.sendorder}")
    private String routingKeyenviarEmail;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(OrderExchange);
    }

    @Bean
    public Queue envioDeEmailQueue() {
        return new Queue(enviarEmailQueue, true);
    }

    @Bean
    public Binding bindingAtualizarProduto(Queue envioDeEmailQueue, DirectExchange exchange) {
        return BindingBuilder.bind(envioDeEmailQueue).to(exchange).with(routingKeyenviarEmail);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
