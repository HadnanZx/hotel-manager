package br.edu.ifba.saj.ads.poo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Label labelNome = new Label("Nome:");
        TextField campoNome = new TextField();

        Label labelCpf = new Label("CPF:");
        TextField campoCpf = new TextField();

        Button botaoCadastrar = new Button("Cadastrar");

        VBox raiz = new VBox(labelNome, campoNome, labelCpf, campoCpf, botaoCadastrar);

        Scene cena = new Scene(raiz, 400, 300);
        stage.setScene(cena);
        stage.setTitle("Cadastro de Hóspede");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}