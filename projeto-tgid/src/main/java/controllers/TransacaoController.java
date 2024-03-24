package controllers;


import models.Transacao;
import services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTransacoes();
    }

    @PostMapping
    public ResponseEntity<?> cadastrarTransacao(@Valid @RequestBody Transacao transacao) {
        transacaoService.cadastrarTransacao(transacao);
        return ResponseEntity.ok().build();
    }

}

