package modelo;


import java.time.LocalDateTime;

public class Lancamento {
    private LocalDateTime dataHora;
    private double valor;
    private String numeroConta;

    public Lancamento(LocalDateTime dataHora, double valor, String numeroConta) {
        this.dataHora = dataHora;
        this.valor = valor;
        this.numeroConta = numeroConta;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public double getValor() {
        return valor;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String toString() {
        return "Horário: " + this.dataHora.toString() + ", Quantia: " + this.valor + ", Conta de " + (this.valor >= 0 ? "destino: " : "origem: ") + this.numeroConta;
    }
}
