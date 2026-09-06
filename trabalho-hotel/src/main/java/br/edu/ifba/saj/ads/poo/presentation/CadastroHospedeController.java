package br.edu.ifba.saj.ads.poo.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import br.edu.ifba.saj.ads.poo.model.Hospede;
import br.edu.ifba.saj.ads.poo.business.HospedeService;

public class CadastroHospedeController{

    @FXML private TextField campoNome;
    @FXML private TextField campoCpf;
    @FXML private Button botaoCadastrar;
    @FXML private TableView<Hospede> tabelaHospedes;
    @FXML private TableColumn<Hospede, String> colunaNome;
    @FXML private TableColumn<Hospede, String> colunaCpf;

    private HospedeService hospedeService;
    private Hospede hospedeSelecionado;

    public void setHospedeService(HospedeService hospedeService){
        this.hospedeService = hospedeService;
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tabelaHospedes.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            hospedeSelecionado = novo;
            if(novo != null){
                campoNome.setText(novo.getNome());
                campoCpf.setText(novo.getCpf());
            }
        });
        carregarTabela();
    }

    private void carregarTabela(){
        tabelaHospedes.setItems(FXCollections.observableArrayList(hospedeService.buscarAtivos()));
        tabelaHospedes.refresh();
    }

    private void limparCampos(){
        campoNome.clear();
        campoCpf.clear();
        hospedeSelecionado = null;
        tabelaHospedes.getSelectionModel().clearSelection();
    }

    @FXML
    private void cadastrar(){
        Hospede novoHospede = new Hospede(campoNome.getText(), campoCpf.getText());
        try{
            hospedeService.salvar(novoHospede);
            new Alert(Alert.AlertType.INFORMATION, "Hóspede cadastrado com sucesso!").show();
            limparCampos();
            carregarTabela();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void editar(){
        if(hospedeSelecionado == null){
            new Alert(Alert.AlertType.WARNING, "Selecione um hóspede na tabela primeiro.").show();
            return;
        }
        hospedeSelecionado.setNome(campoNome.getText());
        hospedeSelecionado.setCpf(campoCpf.getText());
        try{
            hospedeService.atualizar(hospedeSelecionado);
            new Alert(Alert.AlertType.INFORMATION, "Hóspede atualizado com sucesso!").show();
            limparCampos();
            carregarTabela();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void excluir(){
        if(hospedeSelecionado == null){
            new Alert(Alert.AlertType.WARNING, "Selecione um hóspede na tabela primeiro.").show();
            return;
        }
        hospedeService.deletar(hospedeSelecionado.getId());
        new Alert(Alert.AlertType.INFORMATION, "Hóspede excluído com sucesso!").show();
        limparCampos();
        carregarTabela();
    }
}