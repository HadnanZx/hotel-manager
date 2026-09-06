package br.edu.ifba.saj.ads.poo.model;

public class Usuario extends AbstractModel<Long>{

    private String nome;
    private String email;
    private String senha;
    private PerfilUsuario perfil;

    public Usuario(){
    }

    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = PerfilUsuario.COMUM;
    }

    public Usuario(String nome, String email, String senha, PerfilUsuario perfil){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public PerfilUsuario getPerfil(){
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil){
        this.perfil = perfil;
    }
}