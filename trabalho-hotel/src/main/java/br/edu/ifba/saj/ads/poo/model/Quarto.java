package br.edu.ifba.saj.ads.poo.model;

public class Quarto {
    private int numero;
    private TipoQuarto tipo;
    private double preco;

    public Quarto(int numero, TipoQuarto tipo, double preco){
        this.numero = numero;
        this.tipo = tipo;
        this.preco = preco;
    }

    public int getNumero(){
        return numero;
    }
     public TipoQuarto getTipo(){
        return tipo;
    }
     public double getPreco(){
        return preco;
    }


}
