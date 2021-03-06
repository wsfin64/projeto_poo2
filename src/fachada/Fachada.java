package fachada;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Lancamento;
import repositorio.Repositorio;

import java.security.spec.ECField;
import java.time.LocalDateTime;
import java.util.*;

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

        for (Conta cc : repositorio.getContas()){

            if (cc.getNumero().equals(numero)){
                throw new Exception("Já existe uma conta com este número");
            }
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

        if (conta.getChavePiks() == null){
            conta.setChavePiks(chave);
            repositorio.adicionarConta(conta);
        }else {
            conta.setChavePiks(chave);
        }

    }

    public static void creditar(String cpf, double quantia) throws Exception{
        Correntista correntista = repositorio.localizarCorrentista(cpf);

        if (correntista == null){
            throw new Exception("Correntista inexistente");
        }

        Conta conta = correntista.getConta();
        conta.creditar(quantia);
    }

    public static void transferir(String cpf, String chavePIKS, double quantia) throws Exception{
        Correntista correntista = repositorio.localizarCorrentista(cpf);
        if (correntista == null){
            throw new Exception("Correntista inexistente");

        }

        Conta contaOrigem = correntista.getConta();

        Conta contaDestino = repositorio.localizarConta(chavePIKS);
        if (contaDestino == null){
            throw new Exception("Conta de destino inválida");
        }

        if (contaDestino.equals(contaOrigem)){
            throw new Exception("As contas de origem e destino são iguais");
        }

        contaOrigem.transferir(contaDestino, quantia);
        Lancamento lancamentoOrigem = new Lancamento(LocalDateTime.now(), (0 - quantia), contaDestino.getNumero());
        Lancamento lancamentoDestino = new Lancamento(LocalDateTime.now(), quantia, contaOrigem.getNumero());

        contaOrigem.adicionarLancamento(lancamentoOrigem);
        contaDestino.adicionarLancamento(lancamentoDestino);

        repositorio.adicionarLancamento(lancamentoOrigem);
        repositorio.adicionarLancamento(lancamentoDestino);
    }

    public static void apagarConta(String cpf) throws Exception {
        Correntista correntista = repositorio.localizarCorrentista(cpf);

        if (correntista == null){
            throw new Exception("Correntista inexistente");
        }

        Conta conta = correntista.getConta();
        if (conta.getSaldo() == 0){
            List<Lancamento> lancamentos = conta.getLancamentos();

            if (!lancamentos.isEmpty()){
                for (Lancamento la : lancamentos){
                    repositorio.removerLancamento(la);
                }
            }

            repositorio.removerCorrentista(conta.getCorrentista());
            repositorio.removerConta(conta);
        }
        else {
            throw new Exception("A conta não está zerada!");
        }
    }

    public static Conta obterContaTop() throws Exception{
        int maior = 0;
        Conta contaTop = null;

        if (repositorio.getContas().size() == 0){
            throw new Exception("Não há contas no repositario");
        }

        for (Conta conta : repositorio.getContas()){
            if (conta.getLancamentos().size() > maior){
                maior = conta.getLancamentos().size();
                contaTop = conta;
            }
        }

        if (contaTop == null){
            throw new Exception("Não há contas tops");
        }

        return contaTop;
    }

    public static Conta obterConta(String cpf) throws Exception{
        Correntista correntista = repositorio.localizarCorrentista(cpf);

        if (correntista == null){
            throw new Exception("Correntista inexistente");
        }

        Conta conta = correntista.getConta();

        return conta;
    }
}
