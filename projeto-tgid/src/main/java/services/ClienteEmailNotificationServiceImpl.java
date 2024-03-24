package services;

import models.Cliente;
import models.Transacao;
import org.springframework.stereotype.Service;

@Service
public class ClienteEmailNotificationServiceImpl extends ClienteNotificationService {
    @Override
    public void enviarNotificacao(Cliente cliente, Transacao transacao) {
        // Implementação do serviço de notificação por e-mail ou SMS para o cliente
        System.out.println("Enviando notificação para o cliente " + cliente.getNome() + " (" + cliente.getEmail() + "):");
        System.out.println("Transação realizada: " + transacao.getTipo());
        System.out.println("Valor: " + transacao.getValor());
        System.out.println("Data: " + transacao.getData());
        System.out.println("Obrigado por usar nossos serviços!");
    }
}