package br.edu.ifba.saj.ads.poo.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import br.edu.ifba.saj.ads.poo.model.Hospede;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;

public class CadastroHospedeController {
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCpf;

    @FXML
    private Button botaoCadastrar;

    private GerenciadorReservas gerenciador;


    public void setGerenciador(GerenciadorReservas gerenciador){
        this.gerenciador = gerenciador;
    }


    @FXML
    private void cadastrar(){
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();

        Hospede novoHospede = new Hospede(nome, cpf);
        gerenciador.cadastrarHospede(novoHospede);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Hóspede Cadastrado!");
        alerta.show();

        campoNome.clear();
        campoCpf.clear();
    }
   




}
