package br.com.rafaelblomer.infrastructure.entities;

import br.com.rafaelblomer.infrastructure.entities.enums.RoleUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("VENDEDOR")
public class Vendedor extends Usuario{

    @Column(unique = true)
    private String cnpj;

    public Vendedor() {
    }

    public Vendedor(String nome, String email, String senha, String cnpj) {
        super(email, nome, RoleUsuario.ROLE_VENDEDOR, senha);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
