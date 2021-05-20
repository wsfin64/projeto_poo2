package entidades;

import java.util.ArrayList;

public class Conta {

    private String numero;
    private Double saldo;
    private String chavePiks;
    private String tipochavePiks;
    final ArrayList<Lancamento> lancamentos = new ArrayList<>();
    private Correntista correntista;

    public Conta(String numero, Correntista correntista){
        this.numero = numero;
        this.correntista = correntista;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getChavePiks() {
        return chavePiks;
    }

    public void setChavePiks(String chavePiks) {
        this.chavePiks = chavePiks;
    }

    public String getTipochavePiks() {
        return tipochavePiks;
    }

    public void setTipochavePiks(String tipochavePiks) {
        this.tipochavePiks = tipochavePiks;
    }

    public ArrayList<Lancamento> getLancamentos() {
        return lancamentos;
    }


    public Correntista getCorrentista() {
        return correntista;
    }

    public void setCorrentista(Correntista correntista) {
        this.correntista = correntista;
    }
}
