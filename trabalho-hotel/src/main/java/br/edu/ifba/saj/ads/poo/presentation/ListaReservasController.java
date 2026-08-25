package br.edu.ifba.saj.ads.poo.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;
import br.edu.ifba.saj.ads.poo.model.Reserva;

public class ListaReservasController {
    
    @FXML 
    private ListView<Reserva> listaReservas;

    private GerenciadorReservas gerenciador;

    public void setGerenciador(GerenciadorReservas gerenciador){
        this.gerenciador = gerenciador;
        listaReservas.getItems().addAll(gerenciador.getReservas());
    }
}
