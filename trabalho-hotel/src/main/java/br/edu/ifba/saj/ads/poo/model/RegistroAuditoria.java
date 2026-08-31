package br.edu.ifba.saj.ads.poo.model;

public class RegistroAuditoria extends AbstractModel<Long>{

    private String acao;
    private Usuario autor;

    public RegistroAuditoria(){
    }

    public RegistroAuditoria(String acao, Usuario autor){
        this.acao = acao;
        this.autor = autor;
    }

    public String getAcao(){
        return acao;
    }

    public void setAcao(String acao){
        this.acao = acao;
    }

    public Usuario getAutor(){
        return autor;
    }

    public void setAutor(Usuario autor){
        this.autor = autor;
    }

    public String getNomeAutor(){
        return autor != null ? autor.getNome() : "Desconhecido";
    }
}