package br.com.rafaelblomer.business.consumers;

import br.com.rafaelblomer.business.EmailService;
import br.com.rafaelblomer.business.dtos.MessageEmailCompraDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${broker.queue.email.sendorder}")
    public void consumirEnvioDeEmais(@Payload MessageEmailCompraDTO dto) {
        emailService.enviarEmails(dto);
    }
}
