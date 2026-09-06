package br.edu.ifba.saj.ads.poo.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import br.edu.ifba.saj.ads.poo.model.Usuario;
import br.edu.ifba.saj.ads.poo.model.PerfilUsuario;
import br.edu.ifba.saj.ads.poo.business.UsuarioService;

public class CadastroUsuarioController{

    @FXML private TextField campoNome;
    @FXML private TextField campoEmail;
    @FXML private PasswordField campoSenha;
    @FXML private ComboBox<PerfilUsuario> comboPerfil;
    @FXML private Button botaoCadastrar;
    @FXML private TableView<Usuario> tabelaUsuarios;
    @FXML private TableColumn<Usuario, String> colunaNome;
    @FXML private TableColumn<Usuario, String> colunaEmail;
    @FXML private TableColumn<Usuario, PerfilUsuario> colunaPerfil;

    private UsuarioService usuarioService;
    private Usuario usuarioSelecionado;

    public void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;

        comboPerfil.setItems(FXCollections.observableArrayList(PerfilUsuario.values()));

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));

        tabelaUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            usuarioSelecionado = novo;
            if(novo != null){
                campoNome.setText(novo.getNome());
                campoEmail.setText(novo.getEmail());
                campoSenha.setText(novo.getSenha());
                comboPerfil.setValue(novo.getPerfil());
            }
        });

        carregarTabela();
    }

    private void carregarTabela(){
        tabelaUsuarios.setItems(FXCollections.observableArrayList(usuarioService.buscarAtivos()));
        tabelaUsuarios.refresh();
    }

    private void limparCampos(){
        campoNome.clear();
        campoEmail.clear();
        campoSenha.clear();
        comboPerfil.setValue(null);
        usuarioSelecionado = null;
        tabelaUsuarios.getSelectionModel().clearSelection();
    }

    @FXML
    private void cadastrar(){
        Usuario novoUsuario = new Usuario(campoNome.getText(), campoEmail.getText(), campoSenha.getText(), comboPerfil.getValue());
        try{
            usuarioService.salvar(novoUsuario);
            new Alert(Alert.AlertType.INFORMATION, "Usuário cadastrado com sucesso!").show();
            limparCampos();
            carregarTabela();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void editar(){
        if(usuarioSelecionado == null){
            new Alert(Alert.AlertType.WARNING, "Selecione um usuário na tabela primeiro.").show();
            return;
        }
        usuarioSelecionado.setNome(campoNome.getText());
        usuarioSelecionado.setEmail(campoEmail.getText());
        usuarioSelecionado.setSenha(campoSenha.getText());
        usuarioSelecionado.setPerfil(comboPerfil.getValue());
        try{
            usuarioService.atualizar(usuarioSelecionado);
            new Alert(Alert.AlertType.INFORMATION, "Usuário atualizado com sucesso!").show();
            limparCampos();
            carregarTabela();
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void excluir(){
        if(usuarioSelecionado == null){
            new Alert(Alert.AlertType.WARNING, "Selecione um usuário na tabela primeiro.").show();
            return;
        }
        usuarioService.deletar(usuarioSelecionado.getId());
        new Alert(Alert.AlertType.INFORMATION, "Usuário excluído com sucesso!").show();
        limparCampos();
        carregarTabela();
    }
}