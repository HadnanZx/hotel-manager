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
import br.edu.ifba.saj.ads.poo.presentation.ListaReservasController;

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

      public Parent carregarLista() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lista-reservas.fxml"));
        Parent raiz = loader.load();

        ListaReservasController controller = loader.getController();
        controller.setGerenciador(gerenciador);

        return raiz;
    }


    public static void main(String[] args) {
        launch(args);
    }
}