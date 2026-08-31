package br.edu.ifba.saj.ads.poo.presentation;

import br.edu.ifba.saj.ads.poo.App;
import br.edu.ifba.saj.ads.poo.business.RegraDeNegocioException;
import br.edu.ifba.saj.ads.poo.business.UsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtSenha;
    @FXML private Label lblErro;

    private UsuarioService usuarioService;
    private App app;

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    public void handleLogin(ActionEvent event) throws Exception {
        try {
            usuarioService.autenticar(txtEmail.getText(), txtSenha.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.abrirTelaPrincipal(stage);
        } catch (RegraDeNegocioException e) {
            lblErro.setText(e.getMessage());
        }
    }
}