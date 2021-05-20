package model;

import java.time.LocalDateTime;

public class Lancamento {

    private LocalDateTime datahora;
    private Double valor;
    private String numero;

    public Lancamento(LocalDateTime datahora, Double valor, String numero){
        this.datahora = datahora;
        this.valor = valor;
        this.numero = numero;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.datahora = datahora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
