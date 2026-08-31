package br.edu.ifba.saj.ads.poo.presentation;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import br.edu.ifba.saj.ads.poo.App;


public class MainController{

    @FXML 
    private BorderPane painelPrincipal;

    private App app;

    public void setApp(App app){
        this.app = app;
    }
    
    @FXML
    public void mostrarCadastro() throws Exception{
        Parent conteudo = app.carregarCadastro();
        painelPrincipal.setCenter(conteudo);
    }

    @FXML
    public void mostrarReserva() throws Exception{
        Parent conteudo = app.carregarReserva();
        painelPrincipal.setCenter(conteudo);
    }

    public void mostrarLista() throws Exception{
         Parent conteudo = app.carregarLista();
        painelPrincipal.setCenter(conteudo);
    }

    @FXML
    public void mostrarAuditoria() throws Exception{
        Parent conteudo = app.carregarAuditoria();
        painelPrincipal.setCenter(conteudo);
    }
}