package modelo;


import java.util.ArrayList;

public class Conta {
    private final String numero;
    private double saldo;
    private String chavePiks;
    private String tipoChavePiks;
    private ArrayList<Lancamento> lancamentos;
    private final Correntista correntista;

    public Conta(String numero, double saldo, String chavePiks, String tipoChavePiks, Correntista correntista) {
        this.numero = numero;
        this.saldo = saldo;
        this.chavePiks = chavePiks;
        this.tipoChavePiks = tipoChavePiks;
        this.lancamentos = new ArrayList<>();
        this.correntista = correntista;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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
}
