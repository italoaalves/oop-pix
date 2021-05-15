package modelo;


public class ContaEspecial extends Conta{
    private double limite;

    public ContaEspecial(String numero, double saldo, String chavePiks, String tipoChavePiks, Correntista correntista, double limite) {
        super(numero, saldo, chavePiks, tipoChavePiks, correntista);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
