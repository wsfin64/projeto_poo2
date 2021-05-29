package modelo;

public class ContaEspecial extends Conta{

    final Double limite;

    public ContaEspecial (Double limite, String numero){
        super(numero);
        this.limite = limite;
    }
}
