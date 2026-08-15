package br.edu.ifba.saj.ads.poo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;
import br.edu.ifba.saj.ads.poo.presentation.CadastroHospedeController;
import br.edu.ifba.saj.ads.poo.presentation.MainController;
import br.edu.ifba.saj.ads.poo.presentation.ReservaController;

public class App extends Application {

    private GerenciadorReservas gerenciador = new GerenciadorReservas();
    private Stage stagePrincipal;

    @Override
    
    public void start(Stage stage) throws Exception {
        this.stagePrincipal = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent raizPrincipal = loader.load();

        MainController mainController = loader.getController();
        mainController.setApp(this);
        mainController.mostrarCadastro();

        Scene cena = new Scene(raizPrincipal, 700, 400);
        stage.setScene(cena);
        stage.setTitle("Sistema do Hotel");
        stage.show();
    }
    public void abrirTelaCadastro() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastro-hospede.fxml"));
        Parent raiz = loader.load();

        CadastroHospedeController controller = loader.getController();
        controller.setGerenciador(gerenciador);
        controller.setApp(this);

        stagePrincipal.setScene(new Scene(raiz, 400, 300));
    }

    public void abrirTelaReserva() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reserva-quarto.fxml"));
        Parent raiz = loader.load();

        ReservaController controller = loader.getController();
        controller.setGerenciador(gerenciador);

        stagePrincipal.setScene(new Scene(raiz, 400, 400));
    }

    public Parent carregarCadastro() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastro-hospede.fxml"));
        Parent raiz = loader.load();

        CadastroHospedeController controller = loader.getController();
        controller.setGerenciador(gerenciador);

        return raiz;
    }

    public Parent carregarReserva() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reserva-quarto.fxml"));
        Parent raiz = loader.load();

        ReservaController controller = loader.getController();
        controller.setGerenciador(gerenciador);

        return raiz;
    }

    public static void main(String[] args) {
        launch(args);
    }
}