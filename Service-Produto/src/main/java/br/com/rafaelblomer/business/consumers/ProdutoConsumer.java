package br.com.rafaelblomer.business.consumers;

import br.com.rafaelblomer.business.ProdutoService;
import br.com.rafaelblomer.business.dtos.ProdutoMovimentacaoRetiradaDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConsumer {

    @Autowired
    private ProdutoService produtoService;

    @RabbitListener(queues = "${broker.queue.product.update}")
    public void consumirAtualizacaoQuantidadeProduto(@Payload ProdutoMovimentacaoRetiradaDTO dto) {
        produtoService.retirarQuantidadeDeProduto(dto);
    }
}
