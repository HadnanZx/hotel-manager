package br.edu.ifba.saj.ads.poo.presentation;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import br.edu.ifba.saj.ads.poo.App;
import br.edu.ifba.saj.ads.poo.model.Usuario;
import br.edu.ifba.saj.ads.poo.model.PerfilUsuario;
import br.edu.ifba.saj.ads.poo.business.UsuarioService;


public class MainController{

    @FXML
    private BorderPane painelPrincipal;

    @FXML
    private Button botaoAuditoria;

    @FXML
    private Button botaoUsuario;

    private App app;

    public void setApp(App app){
        this.app = app;
        restringirAcessoAdmin();
    }

    private void restringirAcessoAdmin(){
        Usuario logado = UsuarioService.getUsuarioLogado();
        boolean isAdmin = logado != null && logado.getPerfil() == PerfilUsuario.ADMIN;

        botaoAuditoria.setVisible(isAdmin);
        botaoAuditoria.setManaged(isAdmin);
        botaoUsuario.setVisible(isAdmin);
        botaoUsuario.setManaged(isAdmin);
    }

    @FXML
    public void mostrarCadastro() throws Exception{
        Parent conteudo = app.carregarCadastro();
        painelPrincipal.setCenter(conteudo);
    }

    @FXML
    public void mostrarReserva() throws Exception{
        Parent conteudo = app.carregarReserva();
        painelPrincipal.setCenter(conteudo);
    }

    @FXML
    public void mostrarCalendario() throws Exception{
        Parent conteudo = app.carregarCalendario();
        painelPrincipal.setCenter(conteudo);
    }

    @FXML
    public void mostrarAuditoria() throws Exception{
        Parent conteudo = app.carregarAuditoria();
        painelPrincipal.setCenter(conteudo);
    }

    @FXML
    public void mostrarUsuario() throws Exception{
        Parent conteudo = app.carregarCadastroUsuario();
        painelPrincipal.setCenter(conteudo);
    }

    @FXML
    public void sair() throws Exception{
        Stage stage = (Stage) painelPrincipal.getScene().getWindow();
        app.voltarParaLogin(stage);
    }
    @FXML
    public void mostrarQuarto() throws Exception{
        Parent conteudo = app.carregarCadastroQuarto();
        painelPrincipal.setCenter(conteudo);
    }
}