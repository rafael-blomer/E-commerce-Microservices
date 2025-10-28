package br.com.rafaelblomer.business.converters;

import br.com.rafaelblomer.business.dtos.UsuarioCompradorCadastroDTO;
import br.com.rafaelblomer.business.dtos.UsuarioCompradorResponseDTO;
import br.com.rafaelblomer.business.dtos.UsuarioVendedorCadastroDTO;
import br.com.rafaelblomer.business.dtos.UsuarioVendedorResponseDTO;
import br.com.rafaelblomer.infrastructure.entities.Comprador;
import br.com.rafaelblomer.infrastructure.entities.Vendedor;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {
    public Vendedor paraVendedorEntidade(UsuarioVendedorCadastroDTO vendedorDTO) {
        return new Vendedor(vendedorDTO.nome(),
                vendedorDTO.email(),
                vendedorDTO.senha(),
                vendedorDTO.cnpj());
    }

    public Comprador paraCompradorEntidade( UsuarioCompradorCadastroDTO compradorDTO) {
        return new Comprador(compradorDTO.email(),
                compradorDTO.nome(),
                compradorDTO.senha(),
                compradorDTO.cpf(),
                compradorDTO.endereco());
    }

    public UsuarioVendedorResponseDTO paraVendedorResponseDTO(Vendedor vendedorEntity) {
        return new UsuarioVendedorResponseDTO(
                vendedorEntity.getId(),
                vendedorEntity.getNome(),
                vendedorEntity.getEmail(),
                vendedorEntity.getRole(),
                vendedorEntity.getCnpj());
    }

    public UsuarioCompradorResponseDTO paraCompradorResponseDTO(Comprador compradorEntity) {
        return new UsuarioCompradorResponseDTO(
                compradorEntity.getId(),
                compradorEntity.getNome(),
                compradorEntity.getEmail(),
                compradorEntity.getRole(),
                compradorEntity.getCpf(),
                compradorEntity.getEnderecoEntrega());
    }
}
