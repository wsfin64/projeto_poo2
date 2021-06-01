package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lancamento {

    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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

    public String toString(){
        if (valor > 0){
            return "Data: " + datahora.format(formater) + " Valor: " + valor + " Conta origem: " + numero;
        }

        return "Data: " + datahora.format(formater) + " Valor: " + valor + " Conta destino: " + numero;
    }
}
