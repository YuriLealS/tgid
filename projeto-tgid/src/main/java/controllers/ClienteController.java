package controllers;

import jakarta.validation.Valid;
import models.Cliente;
import services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody Cliente cliente) {
        clienteService.cadastrarCliente(cliente);
        return ResponseEntity.ok().build();
    }

}

