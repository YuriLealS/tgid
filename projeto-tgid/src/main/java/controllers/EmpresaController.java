package controller;

import models.Empresa;
import models.Transacao;
import models.TipoTransacao;
import services.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List<Empresa> empresas = empresaService.listarEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarEmpresa(@Valid @RequestBody Empresa empresa) {
        empresaService.cadastrarEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarEmpresaPorId(@PathVariable long id) {
        Empresa empresa = empresaService.buscarEmpresaPorId(id);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEmpresa(@PathVariable long id, @Valid @RequestBody Empresa empresaAtualizada) {
        Empresa empresaExistente = empresaService.buscarEmpresaPorId(id);
        if (empresaExistente != null) {
            empresaService.atualizarEmpresa(id, empresaAtualizada);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable long id) {
        Empresa empresaExistente = empresaService.buscarEmpresaPorId(id);
        if (empresaExistente != null) {
            empresaService.deletarEmpresa(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/deposito")
    public ResponseEntity<Void> depositar(@PathVariable long id, @RequestParam double valor) {
        empresaService.depositar(id, valor);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/saque")
    public ResponseEntity<Void> sacar(@PathVariable long id, @RequestParam double valor) {
        empresaService.sacar(id, valor);
        return ResponseEntity.ok().build();
    }
}
