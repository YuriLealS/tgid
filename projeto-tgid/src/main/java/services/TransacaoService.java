package services;

import models.Transacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    private List<Transacao> transacoes = new ArrayList<>();
    private long proximoId = 1;

    public List<Transacao> listarTransacoes() {
        return transacoes;
    }

    public void cadastrarTransacao(Transacao transacao) {
        transacao.setId(proximoId++);
        transacoes.add(transacao);
    }

    public Transacao buscarTransacaoPorId(long id) {
        for (Transacao transacao : transacoes) {
            if (transacao.getId() == id) {
                return transacao;
            }
        }
        return null; // Transacao não encontrada
    }

}
