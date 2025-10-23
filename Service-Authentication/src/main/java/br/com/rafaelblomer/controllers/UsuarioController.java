package br.com.rafaelblomer.controllers;

import br.com.rafaelblomer.business.UsuarioService;
import br.com.rafaelblomer.business.dtos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criarvendedor")
    public ResponseEntity<UsuarioVendedorResponseDTO> criarNovoVendedor (@Valid @RequestBody UsuarioVendedorCadastroDTO vendedorDTO) {
        return ResponseEntity.ok().body(usuarioService.criarNovoVendedor(vendedorDTO));
    }

    @PostMapping("/criarcomprador")
    public ResponseEntity<UsuarioCompradorResponseDTO> criarNovoComprador (@Valid @RequestBody UsuarioCompradorCadastroDTO compradorDTO) {
        return ResponseEntity.ok().body(usuarioService.criarNovoComprador(compradorDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> realizarLogin(@Valid @RequestBody UsuarioLoginDTO loginDTO) {
        return ResponseEntity.ok().body(usuarioService.realizarLogin(loginDTO));
    }

    @GetMapping("/buscarusuario")
    public ResponseEntity<UsuarioDefaultResponseDTO> buscarUsuarioToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioDTOPorTokenJWT(token));
    }
}
