package model;

import java.util.ArrayList;
import java.util.TreeMap;

// Classe que vai salvas os objetos Correntista, Contas e Lançamentos em listas.
public class Repositorio {

    private TreeMap<String, Correntista> correntistas;
    private TreeMap<String, Conta> contas;
    private ArrayList<Lancamento> lancamentos;

    public Repositorio(){

    }

    public TreeMap<String, Correntista> getCorrentistas() {
        return correntistas;
    }

    public TreeMap<String, Conta> getContas() {
        return contas;
    }

    public ArrayList<Lancamento> getLancamentos() {
        return lancamentos;
    }
}
