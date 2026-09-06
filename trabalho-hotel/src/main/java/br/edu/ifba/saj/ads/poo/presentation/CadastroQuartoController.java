package br.edu.ifba.saj.ads.poo.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.TipoQuarto;
import br.edu.ifba.saj.ads.poo.business.QuartoService;

public class CadastroQuartoController{

    @FXML private TextField campoNumero;
    @FXML private ComboBox<TipoQuarto> comboTipo;
    @FXML private TextField campoPreco;
    @FXML private TableView<Quarto> tabelaQuartos;
    @FXML private TableColumn<Quarto, Integer> colunaNumero;
    @FXML private TableColumn<Quarto, TipoQuarto> colunaTipo;
    @FXML private TableColumn<Quarto, Double> colunaPreco;

    private QuartoService quartoService;
    private Quarto quartoSelecionado;

    public void setQuartoService(QuartoService quartoService){
        this.quartoService = quartoService;

        comboTipo.setItems(FXCollections.observableArrayList(TipoQuarto.values()));

        colunaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        tabelaQuartos.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            quartoSelecionado = novo;
            if(novo != null){
                campoNumero.setText(String.valueOf(novo.getNumero()));
                comboTipo.setValue(novo.getTipo());
                campoPreco.setText(String.valueOf(novo.getPreco()));
            }
        });

        carregarTabela();
    }

    private void carregarTabela(){
        tabelaQuartos.setItems(FXCollections.observableArrayList(quartoService.buscarAtivos()));
        tabelaQuartos.refresh();
    }

    private void limparCampos(){
        campoNumero.clear();
        comboTipo.setValue(null);
        campoPreco.clear();
        quartoSelecionado = null;
        tabelaQuartos.getSelectionModel().clearSelection();
    }

    @FXML
    private void cadastrar(){
        try{
            int numero = Integer.parseInt(campoNumero.getText());
            double preco = Double.parseDouble(campoPreco.getText());
            Quarto novoQuarto = new Quarto(numero, comboTipo.getValue(), preco);
            quartoService.salvar(novoQuarto);
            new Alert(Alert.AlertType.INFORMATION, "Quarto cadastrado com sucesso!").show();
            limparCampos();
            carregarTabela();
        }catch(NumberFormatException e){
            new Alert(Alert.AlertType.ERROR, "Número e Preço precisam ser válidos.").show();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void editar(){
        if(quartoSelecionado == null){
            new Alert(Alert.AlertType.WARNING, "Selecione um quarto na tabela primeiro.").show();
            return;
        }
        try{
            quartoSelecionado.setNumero(Integer.parseInt(campoNumero.getText()));
            quartoSelecionado.setTipo(comboTipo.getValue());
            quartoSelecionado.setPreco(Double.parseDouble(campoPreco.getText()));
            quartoService.atualizar(quartoSelecionado);
            new Alert(Alert.AlertType.INFORMATION, "Quarto atualizado com sucesso!").show();
            limparCampos();
            carregarTabela();
        }catch(NumberFormatException e){
            new Alert(Alert.AlertType.ERROR, "Número e Preço precisam ser válidos.").show();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void excluir(){
        if(quartoSelecionado == null){
            new Alert(Alert.AlertType.WARNING, "Selecione um quarto na tabela primeiro.").show();
            return;
        }
        quartoService.deletar(quartoSelecionado.getId());
        new Alert(Alert.AlertType.INFORMATION, "Quarto excluído com sucesso!").show();
        limparCampos();
        carregarTabela();
    }
}