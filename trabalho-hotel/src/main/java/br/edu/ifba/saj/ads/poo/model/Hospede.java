package br.edu.ifba.saj.ads.poo.model;

public class Hospede extends AbstractModel<Long> {
    private String nome;
    private String cpf;

    public Hospede (String nome, String cpf) {
        
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome(){
        return nome;
    }
      public String getCpf(){
        return cpf;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    @Override
    public String toString() {
    return nome;
    }
}
