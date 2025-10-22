package br.com.rafaelblomer.business;

import br.com.rafaelblomer.business.dtos.*;
import br.com.rafaelblomer.infrastructure.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioVendedorResponseDTO criarNovoVendedor(@Valid UsuarioVendedorCadastroDTO vendedorDTO) {
    }

    public UsuarioCompradorResponseDTO criarNovoComprador(@Valid UsuarioCompradorCadastroDTO compradorDTO) {
    }

    public String realizarLogin(@Valid UsuarioLoginDTO loginDTO) {
    }


    public UsuarioDefaultResponseDTO buscarUsuarioPorTokenJWT(String token) {
    }

    public Void desativarUsuario(String token) {

        //Se for vendedor, desativar produtos
    }
}
