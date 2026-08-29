package br.edu.ifba.saj.ads.poo.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import br.edu.ifba.saj.ads.poo.model.Hospede;
import br.edu.ifba.saj.ads.poo.business.HospedeService;

public class CadastroHospedeController{

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCpf;

    @FXML
    private Button botaoCadastrar;

    private HospedeService hospedeService;

    public void setHospedeService(HospedeService hospedeService){
        this.hospedeService = hospedeService;
    }

    @FXML
    private void cadastrar(){
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();

        Hospede novoHospede = new Hospede(nome, cpf);

        try{
            hospedeService.salvar(novoHospede);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Hóspede cadastrado com sucesso!");
            alerta.show();
            campoNome.clear();
            campoCpf.clear();
        }catch(Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText(e.getMessage());
            alerta.show();
        }
    }
}