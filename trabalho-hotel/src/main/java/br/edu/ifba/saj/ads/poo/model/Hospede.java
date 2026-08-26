package br.edu.ifba.saj.ads.poo.model;

public class Hospede extends AbstractModel<Hospede> {
    private String nome;
    private String cpf;

    public Hospede (String nome, String cpf) {
        super();
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome(){
        return nome;
    }
      public String getCpf(){
        return cpf;
    }
    @Override
    public String toString() {
    return nome;
    }
}
