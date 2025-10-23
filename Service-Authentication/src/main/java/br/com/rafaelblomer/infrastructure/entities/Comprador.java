package br.com.rafaelblomer.infrastructure.entities;

import br.com.rafaelblomer.infrastructure.entities.enums.RoleUsuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMPRADOR")
public class Comprador extends Usuario{

    private String cpf;
    private String enderecoEntrega;

    public Comprador() {
    }

    public Comprador(String email, String nome, String senha, String cpf, String enderecoEntrega) {
        super(email, nome, RoleUsuario.ROLE_COMPRADOR, senha);
        this.cpf = cpf;
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }
}
