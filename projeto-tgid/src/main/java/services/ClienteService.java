package services;

import models.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();
    private long proximoId = 1;

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public void cadastrarCliente(Cliente cliente) {
        cliente.setId(proximoId++);
        clientes.add(cliente);
    }

    public Cliente buscarClientePorId(long id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null; // Cliente não encontrado
    }

    public void atualizarCliente(long id, Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            if (cliente.getId() == id) {
                clienteAtualizado.setId(id);
                clientes.set(i, clienteAtualizado);
                break;
            }
        }
    }

    public void deletarCliente(long id) {
        clientes.removeIf(cliente -> cliente.getId() == id);
    }
}
