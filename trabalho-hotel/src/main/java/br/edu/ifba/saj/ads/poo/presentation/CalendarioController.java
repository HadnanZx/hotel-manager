package br.edu.ifba.saj.ads.poo.presentation;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.Reserva;
import br.edu.ifba.saj.ads.poo.business.QuartoService;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;

public class CalendarioController{

    @FXML private ComboBox<Quarto> comboQuarto;
    @FXML private GridPane gridCalendario;

    private QuartoService quartoService;
    private GerenciadorReservas gerenciadorReservas;

    public void setServices(QuartoService quartoService, GerenciadorReservas gerenciadorReservas){
        this.quartoService = quartoService;
        this.gerenciadorReservas = gerenciadorReservas;
        comboQuarto.setItems(FXCollections.observableArrayList(quartoService.buscarTodos()));
        comboQuarto.setOnAction(e -> desenharCalendario());
        if(!comboQuarto.getItems().isEmpty()){
            comboQuarto.setValue(comboQuarto.getItems().get(0));
            desenharCalendario();
        }
    }

    private void desenharCalendario(){
        gridCalendario.getChildren().clear();
        Quarto quarto = comboQuarto.getValue();
        if(quarto == null){
            return;
        }

        YearMonth mesAtual = YearMonth.now();
        LocalDate primeiroDia = mesAtual.atDay(1);
        int diaSemanaInicial = primeiroDia.getDayOfWeek().getValue() % 7;

        String[] cabecalhos = {"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"};
        for(int i = 0; i < 7; i++){
            Label lbl = new Label(cabecalhos[i]);
            lbl.setStyle("-fx-font-weight: bold;");
            gridCalendario.add(lbl, i, 0);
        }

        List<Reserva> reservas = gerenciadorReservas.buscarTodos();

        int linha = 1;
        int coluna = diaSemanaInicial;
        for(int dia = 1; dia <= mesAtual.lengthOfMonth(); dia++){
            LocalDate data = mesAtual.atDay(dia);
            boolean ocupado = false;

            for(Reserva r : reservas){
                if(r.getQuarto().getId().equals(quarto.getId())
                        && !data.isBefore(r.getCheckin())
                        && data.isBefore(r.getCheckout())){
                    ocupado = true;
                    break;
                }
            }

            Label lblDia = new Label(String.valueOf(dia));
            lblDia.setPrefSize(35, 35);
            lblDia.setAlignment(Pos.CENTER);
            lblDia.setStyle(ocupado
                ? "-fx-background-color: #f5a3a3; -fx-alignment: center;"
                : "-fx-background-color: #a3f5a3; -fx-alignment: center;");

            gridCalendario.add(lblDia, coluna, linha);

            coluna++;
            if(coluna > 6){
                coluna = 0;
                linha++;
            }
        }
    }
}