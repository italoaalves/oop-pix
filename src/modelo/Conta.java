package modelo;


import java.util.ArrayList;

public class Conta {
    private final String numero;
    private double saldo;
    private String chavePiks;
    private String tipoChavePiks;
    private final ArrayList<Lancamento> lancamentos = new ArrayList<>();
    private Correntista correntista;

    public Conta(String numero, double saldo, String chavePiks, String tipoChavePiks, Correntista correntista) {
        this.numero = numero;
        this.saldo = saldo;
        this.chavePiks = chavePiks;
        this.tipoChavePiks = tipoChavePiks;
        this.correntista = correntista;
    }

    public Conta(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void creditar(double valor) {
        if(valor <= 0) {
            throw new IllegalArgumentException("valor inválido.");
        }

        this.saldo += valor;
    }

    public void debitar(double valor) {
        if(this.saldo-valor < 0) {
            throw new IllegalStateException("Saldo insuficiente.");
        }

        this.saldo -= valor;
    }

    public void transferir(Conta conta, double valor) {
        if(this.saldo-valor < 0) {
            throw new IllegalStateException("Saldo insuficiente.");
        }

        this.saldo -= valor;
        conta.creditar(valor);
    }

    public String getChavePiks() {
        return chavePiks;
    }

    public void setChavePiks(String chavePiks) {
        this.chavePiks = chavePiks;
    }

    public String getTipoChavePiks() {
        return tipoChavePiks;
    }

    public void setTipoChavePiks(String tipoChavePiks) {
        this.tipoChavePiks = tipoChavePiks;
    }

    public String getNumero() {
        return numero;
    }

    public ArrayList<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public Correntista getCorrentista() {
        return correntista;
    }

    public void setCorrentista(Correntista correntista) {
        this.correntista = correntista;
    }

    public void adicionarLancamento(Lancamento lancamento) {
        this.lancamentos.add(lancamento);
    }

    public String toString() {
        return "Numero: " + this.numero + ", Chave Piks: " + this.chavePiks + ", Saldo: " + this.saldo;
    }
}
