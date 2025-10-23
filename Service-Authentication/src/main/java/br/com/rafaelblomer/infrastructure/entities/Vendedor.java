package br.com.rafaelblomer.infrastructure.entities;

import br.com.rafaelblomer.infrastructure.entities.enums.RoleUsuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("VENDEDOR")
public class Vendedor extends Usuario{

    private String cnpj;
    private List<Long> idsProdutos;

    public Vendedor() {
    }

    public Vendedor(String nome, String email, String senha, String cnpj) {
        super(email, nome, RoleUsuario.ROLE_VENDEDOR, senha);
        this.cnpj = cnpj;
        this.idsProdutos = new ArrayList<>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Long> getIdsProdutos() {
        return idsProdutos;
    }

    public void setIdsProdutos(List<Long> idsProdutos) {
        this.idsProdutos = idsProdutos;
    }
}
