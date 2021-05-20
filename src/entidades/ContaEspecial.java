package entidades;

public class ContaEspecial extends Conta{

    final Double limite;

    public ContaEspecial (Double limite, String numero, Correntista correntista ){
        super(numero, correntista);
        this.limite = limite;
    }
}
