package repositorio;

import modelo.Conta;
import modelo.Correntista;
import modelo.Lancamento;

import java.util.ArrayList;
import java.util.TreeMap;

public class Repositorio {
    TreeMap<String, Correntista> correntistas = new TreeMap<>();
    TreeMap<String, Conta> contas = new TreeMap<>();
    ArrayList<Lancamento> lancamentos = new ArrayList<>();

    public TreeMap<String, Correntista> getCorrentistas() {
        return correntistas;
    }

    public void adicionar(Correntista correntista) {
        this.correntistas.put(correntista.getCpf(), correntista);
    }

    public void remover(Correntista correntista) {
        this.correntistas.remove(correntista.getCpf());
    }

    public Correntista localizarCorrentista(String cpf) {
        return this.correntistas.get(cpf);
    }

    public int getTotalCorrentistas(){
        return this.correntistas.size();
    }

    public TreeMap<String, Conta> getContas() {return this.contas; }

    public void adicionar(Conta conta) {
        this.contas.put(conta.getChavePiks(), conta);
    }

    public void remover(Conta conta) {
        contas.remove(conta.getChavePiks());
    }

    public Conta localizarConta(String chavePIKS) {
        if (!this.contas.containsKey(chavePIKS))
            throw new IllegalStateException("Conta de chave:" + chavePIKS + " não encontrada.");

        return this.contas.get(chavePIKS);
    }

    public int getTotalContas(){
        return this.contas.size();
    }

    public void adicionar(Lancamento lancamento) { this.lancamentos.add(lancamento);}

    public void remover(Lancamento lancamento) { this.lancamentos.remove(lancamento);}

    public ArrayList<Lancamento> getLancamentos() { return this.lancamentos;}

    public int getTotalLancamentos(){
        return this.lancamentos.size();
    }
}
