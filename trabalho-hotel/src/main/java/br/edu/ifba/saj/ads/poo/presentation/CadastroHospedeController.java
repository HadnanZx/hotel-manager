package br.edu.ifba.saj.ads.poo.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import br.edu.ifba.saj.ads.poo.model.Hospede;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;
import br.edu.ifba.saj.ads.poo.App;

public class CadastroHospedeController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCpf;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoIrParaReservas;

    private GerenciadorReservas gerenciador;
    private App app;

    public void setApp(App app) {
    this.app = app;
}

    public void setGerenciador(GerenciadorReservas gerenciador) {
        this.gerenciador = gerenciador;
    }

    @FXML
    private void cadastrar() {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();

        Hospede novoHospede = new Hospede(nome, cpf);
        gerenciador.cadastrarHospede(novoHospede);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Hóspede cadastrado com sucesso!");
        alerta.show();

        campoNome.clear();
        campoCpf.clear();

    }

    
        @FXML
        private void irParaReservas() throws Exception {
        app.abrirTelaReserva();
        }
}