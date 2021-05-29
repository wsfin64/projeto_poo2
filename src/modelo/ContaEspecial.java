package modelo;

public class ContaEspecial extends Conta{

    final Double limite;

    public ContaEspecial (Double limite, String numero){
        super(numero);
        this.limite = limite;
    }

    @Override
    public void debitar(double quantia) throws Exception{
        double limite = 0.0 - this.limite;

        if(this.saldo - quantia < limite){
            throw new Exception("O valor ultrapassa o limite disponível");
        }

        this.saldo = this.saldo - quantia;
    }
}
