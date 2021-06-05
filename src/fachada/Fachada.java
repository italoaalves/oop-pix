package fachada;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 * Grupo: Felipe Galdino de Sousa
 * 		  Ítalo Alixandre Alves
 **********************************/

import java.time.LocalDateTime;
import java.util.*;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Lancamento;
import repositorio.Repositorio;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();

	public static TreeMap<String, Conta> listarContas() {
		return repositorio.getContas();
	}
	public static TreeMap<String, Correntista> listarCorrentistas() {
		return repositorio.getCorrentistas();
	}
	public static ArrayList<Lancamento> listarLancamentos() {
		return repositorio.getLancamentos();
	}

	public static Conta criarConta(String numero, String cpf, String telefone, String email, String nome) throws Exception{
		Correntista corr = repositorio.localizarCorrentista(cpf);
		if(corr != null)
			throw new Exception("correntista ja existe:"+cpf);

		corr = new Correntista(cpf, nome, telefone, email);
		Conta c = new Conta(numero);

		c.setCorrentista(corr);
		corr.setConta(c);

		repositorio.adicionar(corr);
		return c;
	}

	public static ContaEspecial criarConta(String numero, double limite, String cpf, String telefone, String email, String nome) throws Exception{
		Correntista corr = repositorio.localizarCorrentista(cpf);
		if(corr != null)
			throw new Exception("correntista ja existe: "+cpf);

		corr = new Correntista(cpf, nome, telefone, email);
		ContaEspecial c = new ContaEspecial(numero, limite);

		c.setCorrentista(corr);
		corr.setConta(c);
		repositorio.adicionar(corr);

		return c;
	}

	public static void creditar(String cpf, double quantia) throws Exception {
		Correntista correntista = repositorio.localizarCorrentista(cpf);
		if(correntista == null)
			throw new IllegalArgumentException("correntista inexistente");

		correntista.getConta().creditar(quantia);
	}

	public static void transferir(String cpf, String chavePIKS, double quantia) throws Exception {
		Correntista correntista = repositorio.localizarCorrentista(cpf);

		if(correntista == null)
			throw new IllegalArgumentException("correntista inexistente");
		
		Conta contaorigem = correntista.getConta();
		Conta contadestino  = repositorio.localizarConta(chavePIKS);

		if(contadestino == null)
			throw new Exception("conta destino invalida");

		contaorigem.transferir(contadestino, quantia);

		Lancamento lancamentoOrigem = new Lancamento(LocalDateTime.now(), -quantia, contaorigem.getNumero());
		Lancamento lancamentoDestino = new Lancamento(LocalDateTime.now(), quantia, contadestino.getNumero());

		contaorigem.adicionarLancamento(lancamentoOrigem);
		contadestino.adicionarLancamento(lancamentoDestino);

		repositorio.adicionar(lancamentoOrigem);
		repositorio.adicionar(lancamentoDestino);
	}

	public static void apagarConta(String cpf) throws Exception {
		Correntista correntista = repositorio.localizarCorrentista(cpf);
		if(correntista == null)
			throw new Exception("correntista inexistente");
		
		Conta conta = correntista.getConta();
		if(conta.getSaldo()==0) {
			if(conta.getChavePiks() != null) {
				repositorio.remover(conta);
			}
			if(!conta.getLancamentos().isEmpty()) {
				for(Lancamento l: conta.getLancamentos()) {
					repositorio.remover(l);
				}
			}
		} else {
			throw new IllegalStateException("A conta possui saldo e por isso não pode ser removida");
		}

		repositorio.remover(correntista);
	}

	public static Conta obterContaTop() {
		TreeMap<String, Conta> contas = listarContas();
		Conta contaTop = null;
		int maiorLancamentos = 0;

		for (Conta conta : contas.values()) {
			if (conta.getLancamentos().size() > maiorLancamentos){
				contaTop = conta;
				maiorLancamentos = conta.getLancamentos().size();
			}
		}

		if (contaTop == null) throw new IllegalStateException("Não há conta top");

		return contaTop;
	}

	public static Conta obterConta(String cpf) {
		return repositorio.localizarCorrentista(cpf).getConta();
	}

	public static void criarChavePIKS(String cpf, String tipochave) {
		Conta conta = Fachada.obterConta(cpf);

		conta.setChavePiks(gerarChavePIKS(cpf, tipochave, conta));
		repositorio.adicionar(conta);
	}

	private static String gerarChavePIKS(String cpf, String tipochave, Conta conta) {
		String chave;

		switch(tipochave) {
			case "cpf":
				chave = cpf;
				break;
			case "email":
				chave = conta.getCorrentista().getEmail();
				break;
			case "telefone":
				chave = conta.getCorrentista().getTelefone();
				break;
			default:
				StringBuilder chaveBuilder = new StringBuilder();
				Random generator = new Random();

				for(int i = 0; i < 9; i++) chaveBuilder.append(generator.nextInt(9));
				chave = chaveBuilder.toString();
		}
		if(unica(chave)) {
			return chave;
		} else if (tipochave.equals("aleatorio")){
			return gerarChavePIKS(cpf, tipochave, conta);
		} else {
			throw new IllegalStateException("Chave já existe");
		}
	}

	private static boolean unica(String chave) {
		Conta conta = repositorio.localizarConta(chave);
		if(conta != null) return false;
		return true;
	}
}
