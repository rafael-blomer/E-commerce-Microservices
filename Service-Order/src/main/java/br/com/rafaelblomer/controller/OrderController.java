package br.com.rafaelblomer.controller;

import br.com.rafaelblomer.business.OrderService;
import br.com.rafaelblomer.business.dtos.OrderRequestDTO;
import br.com.rafaelblomer.business.dtos.OrderResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAuthority('ROLE_COMPRADOR')")
    @PostMapping
    public ResponseEntity<OrderResponseDTO> criarNovaOrdem (@RequestHeader("Authorization") String token, @Valid @RequestBody OrderRequestDTO orderDTO) {
        return ResponseEntity.ok().body(orderService.criarNovaOrdem(token, orderDTO));
    }
}
