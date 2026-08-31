package br.edu.ifba.saj.ads.poo.presentation;

import br.edu.ifba.saj.ads.poo.business.GerenciadorAuditoria;
import br.edu.ifba.saj.ads.poo.model.RegistroAuditoria;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AuditoriaController {

    @FXML private TableView<RegistroAuditoria> tabelaAuditoria;
    @FXML private TableColumn<RegistroAuditoria, String> colunaAcao;
    @FXML private TableColumn<RegistroAuditoria, String> colunaAutor;
    @FXML private TableColumn<RegistroAuditoria, String> colunaHorario;

    private GerenciadorAuditoria gerenciadorAuditoria;

    public void setGerenciadorAuditoria(GerenciadorAuditoria gerenciadorAuditoria) {
        this.gerenciadorAuditoria = gerenciadorAuditoria;
        carregarDados();
    }

    private void carregarDados() {
        colunaAcao.setCellValueFactory(new PropertyValueFactory<>("acao"));
        colunaAutor.setCellValueFactory(new PropertyValueFactory<>("nomeAutor"));
        colunaHorario.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        tabelaAuditoria.setItems(FXCollections.observableArrayList(gerenciadorAuditoria.buscarTodos()));
    }
}