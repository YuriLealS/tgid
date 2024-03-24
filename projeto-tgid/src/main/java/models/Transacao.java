package models;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public class Transacao {
    @Id
    @Positive(message = "O valor do id deve ser um número positivo.")
    @NotNull
    private long id;

    @Positive(message = "O valor da transação deve ser um número positivo.")
    private double valor;

    @NotNull(message = "A data da transação não pode ser nula.")
    private Date data;

    @NotNull(message = "O tipo da transação não pode ser nulo.")
    private TipoTransacao tipo;

    @NotNull(message = "O cliente da transação não pode ser nulo.")
    @Valid
    private Cliente cliente;

    // Construtor

    public Transacao(long id, double valor, Date data, TipoTransacao tipo, Cliente cliente) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    // Getters e setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
