package fachada;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Lancamento;
import repositorio.Repositorio;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Random;

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

    //teste
    public static void apagarConta(String cpf){
        System.out.println(cpf);
    }

    // teste
    public static void criarChavePIKS(String cpf, String tipoChave) throws Exception {

        Correntista correntista = repositorio.localizarCorrentista(cpf);

        if (correntista == null){
            throw new Exception("Correntista inexistente");
        }

        Conta conta = correntista.getConta();

        String chave;

        switch (tipoChave){
            case "cpf":
                chave = correntista.getCpf();
                break;
            case "email":
                chave = correntista.getEmail();
                break;
            case "telefone":
                chave = correntista.getTelefone();
                break;
            case "aleatorio":
                String ch = "";

                for (int i = 1; i <= 8; i++){
                    Random numero = new Random();
                    int numeroSorteado = numero.nextInt((9) + 1);
                    ch = ch + Integer.toString(numeroSorteado);
                }

                chave = ch;
                break;
            default:
                throw new Exception("A chave PIKS não pode ser um valor em branco");
        }

        Conta conta_busca = repositorio.localizarConta(chave);

        if (conta_busca != null){
            throw new Exception("Já existe uma chave neste valor");
        }

        conta.setChavePiks(chave);

        repositorio.adicionarConta(conta);

    }
}
