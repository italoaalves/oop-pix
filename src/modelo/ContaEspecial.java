package modelo;


public class ContaEspecial extends Conta {
    private double limite;

    public ContaEspecial(String numero, double limite) {
        super(numero);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public void debitar(double valor) {
        double resto = this.getSaldo() - valor;

        if(resto >= 0) {
            super.debitar(valor);
        } else {
            if(Math.abs(resto) > this.limite) throw new IllegalStateException("Limite insuficiente");
            super.debitar(valor+resto);
            this.limite += resto;
        }
    }
}
