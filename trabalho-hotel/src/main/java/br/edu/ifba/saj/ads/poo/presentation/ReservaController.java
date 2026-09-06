package br.edu.ifba.saj.ads.poo.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import java.time.LocalDate;
import br.edu.ifba.saj.ads.poo.model.Hospede;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.Reserva;
import br.edu.ifba.saj.ads.poo.business.HospedeService;
import br.edu.ifba.saj.ads.poo.business.QuartoService;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;

public class ReservaController{

    @FXML private ComboBox<Hospede> comboHospede;
    @FXML private ComboBox<Quarto> comboQuarto;
    @FXML private DatePicker dataCheckin;
    @FXML private DatePicker dataCheckout;
    @FXML private Button botaoReservar;
    @FXML private TableView<Reserva> tabelaReservas;
    @FXML private TableColumn<Reserva, String> colunaHospede;
    @FXML private TableColumn<Reserva, String> colunaQuarto;
    @FXML private TableColumn<Reserva, LocalDate> colunaCheckin;
    @FXML private TableColumn<Reserva, LocalDate> colunaCheckout;

    private HospedeService hospedeService;
    private QuartoService quartoService;
    private GerenciadorReservas gerenciador;
    private Reserva reservaSelecionada;

    public void setServices(HospedeService hospedeService, QuartoService quartoService, GerenciadorReservas gerenciador){
        this.hospedeService = hospedeService;
        this.quartoService = quartoService;
        this.gerenciador = gerenciador;

        comboHospede.getItems().addAll(hospedeService.buscarAtivos());
        comboQuarto.getItems().addAll(quartoService.buscarAtivos());

        colunaHospede.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getHospede().getNome()));
        colunaQuarto.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getQuarto().getNumero())));
        colunaCheckin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        colunaCheckout.setCellValueFactory(new PropertyValueFactory<>("checkout"));

        tabelaReservas.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            reservaSelecionada = novo;
            if(novo != null){
                comboHospede.setValue(novo.getHospede());
                comboQuarto.setValue(novo.getQuarto());
                dataCheckin.setValue(novo.getCheckin());
                dataCheckout.setValue(novo.getCheckout());
            }
        });

        carregarTabela();
    }

    private void carregarTabela(){
        tabelaReservas.setItems(FXCollections.observableArrayList(gerenciador.buscarAtivos()));
        tabelaReservas.refresh();
    }

    private void limparCampos(){
        comboHospede.setValue(null);
        comboQuarto.setValue(null);
        dataCheckin.setValue(null);
        dataCheckout.setValue(null);
        reservaSelecionada = null;
        tabelaReservas.getSelectionModel().clearSelection();
    }

    @FXML
    private void reservar(){
        Reserva novaReserva = new Reserva(comboHospede.getValue(), comboQuarto.getValue(), dataCheckin.getValue(), dataCheckout.getValue());
        try{
            gerenciador.salvar(novaReserva);
            new Alert(Alert.AlertType.INFORMATION, "Sua reserva foi concluída!").show();
            limparCampos();
            carregarTabela();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void editar(){
        if(reservaSelecionada == null){
            new Alert(Alert.AlertType.WARNING, "Selecione uma reserva na tabela primeiro.").show();
            return;
        }
        reservaSelecionada.setHospede(comboHospede.getValue());
        reservaSelecionada.setQuarto(comboQuarto.getValue());
        reservaSelecionada.setCheckin(dataCheckin.getValue());
        reservaSelecionada.setCheckout(dataCheckout.getValue());
        try{
            gerenciador.atualizar(reservaSelecionada);
            new Alert(Alert.AlertType.INFORMATION, "Reserva atualizada com sucesso!").show();
            limparCampos();
            carregarTabela();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void excluir(){
        if(reservaSelecionada == null){
            new Alert(Alert.AlertType.WARNING, "Selecione uma reserva na tabela primeiro.").show();
            return;
        }
        gerenciador.deletar(reservaSelecionada.getId());
        new Alert(Alert.AlertType.INFORMATION, "Reserva excluída com sucesso!").show();
        limparCampos();
        carregarTabela();
    }
}