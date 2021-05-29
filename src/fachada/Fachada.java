package fachada;

import modelo.Conta;
import repositorio.Repositorio;

import java.util.ArrayList;

public class Fachada {

    private static Repositorio repositorio = new Repositorio();

    public static ArrayList<Conta> listarContas(){
        return repositorio.getContas();
    }


}
