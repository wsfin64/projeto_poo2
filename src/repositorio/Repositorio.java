package repositorio;

import modelo.Conta;
import modelo.Correntista;
import modelo.Lancamento;

import java.util.ArrayList;
import java.util.TreeMap;

// Classe que vai salvas os objetos Correntista, Contas e Lançamentos em listas.
public class Repositorio {

    private TreeMap<String, Correntista> correntistas = new TreeMap<>();
    private TreeMap<String, Conta> contas = new TreeMap<>();
    private ArrayList<Lancamento> lancamentos = new ArrayList<>();

    // ------------- CONTA -------------------
    public void adicionarConta(Conta conta){
        contas.put(conta.getChavePiks(), conta);
    }

    public void removerConta(Conta conta){
        contas.remove(conta.getChavePiks());
    }

    public Conta localizarConta(String chavePIKs){
        return contas.get(chavePIKs);
    }

    // ---------------- CORRENTISTA ---------------------
    public void adicionarCorrentista(Correntista correntista){
        correntistas.put(correntista.getCpf(), correntista);
    }

    public void removerCorrentista(Correntista correntista){
        correntistas.remove(correntista.getCpf());
    }

    public Correntista localizarCorrentista(String cpf){
        return correntistas.get(cpf);
    }

    // ------------- LANÇAMENTO ----------------------
    public void adicionarLancamento(Lancamento lancamento){
        lancamentos.add(lancamento);
    }

    public void removerLancamento(Lancamento lancamento){
        lancamentos.remove(lancamento);
    }

    // -------------- GETTERS ---------------------

    public ArrayList<Conta> getContas(){
        return new ArrayList<>(contas.values());
    }

    public ArrayList<Correntista> getCorrentistas(){
        return new ArrayList<>(correntistas.values());
    }

    public ArrayList<Lancamento> getLancamentos(){
        return lancamentos;
    }

    public int getTotalContas(){
        return contas.size();
    }

    public int getTotalCorrentistas(){
        return correntistas.size();
    }

    public int getTotalLancamentos(){
        return lancamentos.size();
    }


}
