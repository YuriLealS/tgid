package services;

import models.Cliente;
import models.Transacao;

public abstract class ClienteNotificationService {
    public abstract void enviarNotificacao(Cliente cliente, Transacao transacao);
}

