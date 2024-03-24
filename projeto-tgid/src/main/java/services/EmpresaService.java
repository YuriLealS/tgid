package services;

import models.Empresa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    private List<Empresa> empresas = new ArrayList<>();
    private long proximoId = 1;

    public List<Empresa> listarEmpresas() {
        return empresas;
    }

    public void cadastrarEmpresa(Empresa empresa) {
        empresa.setId(proximoId++);
        empresas.add(empresa);
    }

    public Empresa buscarEmpresaPorId(long id) {
        for (Empresa empresa : empresas) {
            if (empresa.getId() == id) {
                return empresa;
            }
        }
        return null; // Empresa não encontrada
    }

    public void atualizarEmpresa(long id, Empresa empresaAtualizada) {
        for (int i = 0; i < empresas.size(); i++) {
            Empresa empresa = empresas.get(i);
            if (empresa.getId() == id) {
                empresaAtualizada.setId(id);
                empresas.set(i, empresaAtualizada);
                break;
            }
        }
    }

    public void deletarEmpresa(long id) {
        empresas.removeIf(empresa -> empresa.getId() == id);
    }

    public void depositar(long id, double valor) {
        Empresa empresa = buscarEmpresaPorId(id);
        if (empresa != null) {
            empresa.setSaldo(empresa.getSaldo() + valor);
        }
    }

    public void sacar(long id, double valor) {
        Empresa empresa = buscarEmpresaPorId(id);
        if (empresa != null && empresa.getSaldo() >= valor) {
            empresa.setSaldo(empresa.getSaldo() - valor);
        }
    }

}
