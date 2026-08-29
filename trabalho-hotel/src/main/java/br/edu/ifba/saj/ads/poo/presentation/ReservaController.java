package br.edu.ifba.saj.ads.poo.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import java.time.LocalDate;
import br.edu.ifba.saj.ads.poo.model.Hospede;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.Reserva;
import br.edu.ifba.saj.ads.poo.business.HospedeService;
import br.edu.ifba.saj.ads.poo.business.QuartoService;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;

public class ReservaController{

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

    private HospedeService hospedeService;
    private QuartoService quartoService;
    private GerenciadorReservas gerenciador;

    public void setServices(HospedeService hospedeService, QuartoService quartoService, GerenciadorReservas gerenciador) {
        this.hospedeService = hospedeService;
        this.quartoService = quartoService;
        this.gerenciador = gerenciador;
        comboHospede.getItems().addAll(hospedeService.buscarTodos());
        comboQuarto.getItems().addAll(quartoService.buscarTodos());
    }

    @FXML
    private void reservar(){
        Hospede hospedeEscolhido = comboHospede.getValue();
        Quarto quartoEscolhido = comboQuarto.getValue();
        LocalDate checkinEscolhido = dataCheckin.getValue();
        LocalDate checkoutEscolhido = dataCheckout.getValue();

        Reserva novaReserva = new Reserva(hospedeEscolhido, quartoEscolhido, checkinEscolhido, checkoutEscolhido);

        try{
            gerenciador.salvar(novaReserva);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Sua reserva foi concluida!");
            alerta.show();
        }catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText(e.getMessage());
            alerta.show();
        }
    }
}