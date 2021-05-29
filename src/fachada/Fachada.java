package fachada;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Lancamento;
import repositorio.Repositorio;

import java.util.ArrayList;

public class Fachada {

    private static Repositorio repositorio = new Repositorio();

    //  --- LISTAGEM DAS CONTAS, CORRENTISTAS E LANCAMENTOS ----
    public static ArrayList<Conta> listarContas(){
        return repositorio.getContas();
    }

    public static ArrayList<Correntista> listarCorrentistas(){
        return repositorio.getCorrentistas();
    }

    public static ArrayList<Lancamento> listarLancamentos(){
        return repositorio.getLancamentos();
    }

    // --------- CRIAÇÃO DAS CONTAS ------------
    public static Conta criarConta(String numero, String cpf, String telefone, String email, String nome) throws Exception{
        Correntista correntista = repositorio.localizarCorrentista(cpf);
        if (correntista != null){
            throw new Exception("Correntista já existe: " + cpf);
        }

        correntista = new Correntista(cpf, nome, telefone, email);
        Conta conta = new Conta(numero);

        conta.setCorrentista(correntista);
        correntista.setConta(conta);

        repositorio.adicionarCorrentista(correntista);

        return conta;
    }

    public static ContaEspecial criarContaEspecial(String numero, double limite, String cpf, String telefone, String email, String nome) throws Exception{
        Correntista correntista = repositorio.localizarCorrentista(cpf);
        if (correntista != null){
            throw new Exception("Correntista já existe: " + cpf);
        }

        correntista = new Correntista(cpf, nome, telefone, email);
        ContaEspecial conta = new ContaEspecial(limite, numero);

        conta.setCorrentista(correntista);
        correntista.setConta(conta);

        repositorio.adicionarCorrentista(correntista);
        return conta;
    }



}
