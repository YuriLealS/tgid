package callbacks;

import models.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.WebhookService;

@Service
public class EmpresaCallbackServiceImpl implements EmpresaCallbackService {

    @Autowired
    private WebhookService webhookService;

    @Override
    public void enviarCallback(Transacao transacao) {

        String mensagem = "Transação realizada: " + transacao.getTipo() +
                "\nValor: " + transacao.getValor() +
                "\nData: " + transacao.getData() +
                "\nCliente: " + transacao.getCliente().getNome() +
                "\nDocumento da Empresa: " + transacao.getCliente().getCpf();

        // Envia o callback para o webhook.site
        webhookService.enviarCallback(mensagem);
    }
}
