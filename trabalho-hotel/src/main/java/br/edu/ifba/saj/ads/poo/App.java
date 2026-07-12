package br.edu.ifba.saj.ads.poo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;
import br.edu.ifba.saj.ads.poo.presentation.CadastroHospedeController;

public class App extends Application {

    private GerenciadorReservas gerenciador = new GerenciadorReservas();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastro-hospede.fxml"));
        Parent raiz = loader.load();

        CadastroHospedeController controller = loader.getController();
        controller.setGerenciador(gerenciador);

        Scene cena = new Scene(raiz, 400, 300);
        stage.setScene(cena);
        stage.setTitle("Sistema do Hotel");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}