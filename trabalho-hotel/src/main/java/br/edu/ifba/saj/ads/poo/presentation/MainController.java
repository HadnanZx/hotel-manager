package br.edu.ifba.saj.ads.poo.presentation;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import br.edu.ifba.saj.ads.poo.App;


public class MainController {

    @FXML 
    private BorderPane painelPrincipal;

    private App app;

    public void setApp(App app){
        this.app = app;
    }
    
    @FXML
    private void mostrarCadastro() throws Exception{
        Parent conteudo = app.carregarCadastro();
        painelPrincipal.setCenter(conteudo);
    }

    @FXML
    private void mostrarReserva() throws Exception{
        Parent conteudo = app.carregarReserva();
        painelPrincipal.setCenter(conteudo);
    }
}
