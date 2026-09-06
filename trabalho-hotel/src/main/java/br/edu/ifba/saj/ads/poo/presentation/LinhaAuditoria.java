package br.edu.ifba.saj.ads.poo.presentation;

public class LinhaAuditoria{

    private String registro;
    private String criadoPor;
    private String criadoEm;
    private String alteradoPor;
    private String alteradoEm;

    public LinhaAuditoria(String registro, String criadoPor, String criadoEm, String alteradoPor, String alteradoEm){
        this.registro = registro;
        this.criadoPor = criadoPor;
        this.criadoEm = criadoEm;
        this.alteradoPor = alteradoPor;
        this.alteradoEm = alteradoEm;
    }

    public String getRegistro(){
        return registro;
    }

    public String getCriadoPor(){
        return criadoPor;
    }

    public String getCriadoEm(){
        return criadoEm;
    }

    public String getAlteradoPor(){
        return alteradoPor;
    }

    public String getAlteradoEm(){
        return alteradoEm;
    }
}
