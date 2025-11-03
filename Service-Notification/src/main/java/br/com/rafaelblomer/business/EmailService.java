package br.com.rafaelblomer.business;

import br.com.rafaelblomer.business.dtos.MessageEmailCompraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmails(MessageEmailCompraDTO dto) {
        enviarEmailParaComprador(dto);
        enviarEmailParaVendedor(dto);
    }

    private void enviarEmailParaComprador(MessageEmailCompraDTO dto) {
        String assunto = "Confirmação de compra - " + dto.nomeProduto();
        String corpo = String.format("""
                Olá, %s!
                
                Sua compra foi realizada com sucesso. Aqui estão os detalhes:
                
                🛒 Produto: %s
                📦 Quantidade: %d
                💰 Valor total: R$ %.2f
                
                Obrigado por comprar conosco!
                """,
                dto.nomeComprador(),
                dto.nomeProduto(),
                dto.quantidadeComprada(),
                dto.precoTotal()
        );

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(dto.emailComprador());
        mensagem.setSubject(assunto);
        mensagem.setText(corpo);

        mailSender.send(mensagem);
    }

    private void enviarEmailParaVendedor(MessageEmailCompraDTO dto) {
        String assunto = "Nova venda realizada - " + dto.nomeProduto();
        String corpo = String.format("""
                Olá, vendedor!
                
                Você acaba de realizar uma nova venda.
                
                🧍 Comprador: %s
                📧 E-mail do comprador: %s
                🛒 Produto: %s
                📦 Quantidade: %d
                💰 Valor total: R$ %.2f
                
                Parabéns pela venda!
                """,
                dto.nomeComprador(),
                dto.emailComprador(),
                dto.nomeProduto(),
                dto.quantidadeComprada(),
                dto.precoTotal()
        );

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(dto.emailVendedor());
        mensagem.setSubject(assunto);
        mensagem.setText(corpo);

        mailSender.send(mensagem);
    }
}
