package models;

import callbacks.EmpresaCallbackService;
import callbacks.EmpresaCallbackServiceImpl;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import services.ClienteEmailNotificationServiceImpl;
import services.ClienteNotificationService;

import java.util.List;

public class Empresa {

    @Id
    @Positive(message = "O valor do id deve ser um número positivo.")
    private long id;

    @NotBlank(message = "O nome da empresa não pode estar em branco.")
    private String nome;

    @NotBlank(message = "A empresa deve ter um documento.")
    @Pattern(regexp = "\\d{14}", message = "O documento deve ser um CNPJ válido (14 dígitos, somente números).")
    private Integer cnpj;

    @Positive(message = "O saldo deve ser um valor positivo.")
    private double saldo;

    @Positive(message = "A taxa deve ser um valor positivo.")
    private final double taxa;

    @Size(min = 1, message = "Pelo menos um tipo de taxa deve ser especificado.")
    private List<TipoTaxa> tiposTaxas;


    //Construtor
    public Empresa(long id,String nome, Integer cnpj, double taxa, List<TipoTaxa> tiposTaxas) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.saldo = 0.0;
        this.taxa = taxa;
        this.tiposTaxas = tiposTaxas;
    }

    // Métodos

    public void depositar(double valor) {
        double saldoAtualizado = valor - calcularTaxa();
        saldo += saldoAtualizado;
    }

    public boolean sacar(double valor) {
        if (valor > saldo) {
            return false; // Saldo insuficiente
        }
        double saldoAtualizado = valor - calcularTaxa();
        saldo -= saldoAtualizado;
        return true;
    }

    private double calcularTaxa() {
        return taxa * tiposTaxas.size();
    }

    public void processarTransacao(Transacao transacao) {
        double valor = transacao.getValor();
        if (transacao.getTipo() == TipoTransacao.DEPOSITO) {
            depositar(valor);
        } else {
            boolean sucesso = sacar(valor);
            if (!sucesso) {
                // Transação de saque falhou devido a saldo insuficiente
                // Aqui você pode implementar uma lógica para lidar com a falha
            }
        }

        // Envio de notificação para o cliente
        ClienteNotificationService notificationService = new ClienteEmailNotificationServiceImpl();
        notificationService.enviarNotificacao(transacao.getCliente(), transacao);

        // Callback para a empresa
        EmpresaCallbackService callbackService = new EmpresaCallbackServiceImpl() {
        };
        callbackService.enviarCallback(transacao);
    }


    // Getters e Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCnpj() {
        return cnpj;
    }

    public void setCnpj(Integer cnpj) {
        this.cnpj = cnpj;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getTaxa() {
        return taxa;
    }

    public List<TipoTaxa> getTiposTaxas() {
        return tiposTaxas;
    }

    public void setTiposTaxas(List<TipoTaxa> tiposTaxas) {
        this.tiposTaxas = tiposTaxas;
    }
}

