package br.edu.ifba.saj.ads.poo.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;
import java.time.LocalDate;
import br.edu.ifba.saj.ads.poo.model.Hospede;
import br.edu.ifba.saj.ads.poo.model.Quarto;

public class ReservaController {

    @FXML
    private ComboBox<Hospede> comboHospede;

    @FXML
    private ComboBox<Quarto> comboQuarto;

    @FXML
    private DatePicker dataCheckin;

    
    @FXML
    private DatePicker dataCheckout;

    
    @FXML
    private Button botaoReservar;

    private GerenciadorReservas gerenciador;

    public void setGerenciador(GerenciadorReservas gerenciador){
        this.gerenciador = gerenciador;
        comboHospede.getItems().addAll(gerenciador.getHospedes());
        comboQuarto.getItems().addAll(gerenciador.getQuartos());
    }

    @FXML
    private void reservar(){
        Hospede hospedeEscolhido = comboHospede.getValue();
        Quarto quartoEscolhido = comboQuarto.getValue();

        LocalDate checkinEscolhido = dataCheckin.getValue();
        LocalDate checkoutEscolhido = dataCheckout.getValue();

        boolean sucesso = gerenciador.criarReserva(hospedeEscolhido, quartoEscolhido, checkinEscolhido, checkoutEscolhido);

        Alert alerta;
        if(sucesso){
            alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Sua reserva foi concluida!");
        }else{
            alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Não foi possivel concluir sua reserva.");
        }
        alerta.show();
    }

    

}