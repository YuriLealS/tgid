package callbacks;

import models.Transacao;

public interface EmpresaCallbackService {
    void enviarCallback(Transacao transacao);
}