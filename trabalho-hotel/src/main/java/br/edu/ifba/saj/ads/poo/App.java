package br.edu.ifba.saj.ads.poo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import br.edu.ifba.saj.ads.poo.business.HospedeService;
import br.edu.ifba.saj.ads.poo.business.QuartoService;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;
import br.edu.ifba.saj.ads.poo.business.UsuarioService;
import br.edu.ifba.saj.ads.poo.data.HospedeDAO;
import br.edu.ifba.saj.ads.poo.data.QuartoDAO;
import br.edu.ifba.saj.ads.poo.data.ReservaDAO;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.TipoQuarto;
import br.edu.ifba.saj.ads.poo.model.Usuario;
import br.edu.ifba.saj.ads.poo.model.PerfilUsuario;
import br.edu.ifba.saj.ads.poo.presentation.CadastroHospedeController;
import br.edu.ifba.saj.ads.poo.presentation.MainController;
import br.edu.ifba.saj.ads.poo.presentation.ReservaController;
import br.edu.ifba.saj.ads.poo.presentation.LoginController;
import br.edu.ifba.saj.ads.poo.presentation.AuditoriaController;
import br.edu.ifba.saj.ads.poo.presentation.CadastroUsuarioController;
import br.edu.ifba.saj.ads.poo.presentation.CalendarioController;
import br.edu.ifba.saj.ads.poo.presentation.CadastroQuartoController;

public class App extends Application{

    private HospedeService hospedeService = new HospedeService(new HospedeDAO());
    private QuartoService quartoService = new QuartoService(new QuartoDAO());
    private GerenciadorReservas gerenciador = new GerenciadorReservas(new ReservaDAO());
    private UsuarioService usuarioService = new UsuarioService();
    private Stage stagePrincipal;

    private void popularQuartosIniciais() throws Exception{
        quartoService.salvar(new Quarto(101, TipoQuarto.SOLTEIRO, 150.0));
        quartoService.salvar(new Quarto(102, TipoQuarto.SOLTEIRO, 150.0));
        quartoService.salvar(new Quarto(201, TipoQuarto.CASAL, 250.0));
        quartoService.salvar(new Quarto(202, TipoQuarto.CASAL, 250.0));
        quartoService.salvar(new Quarto(301, TipoQuarto.SUITE, 400.0));
    }

    private void popularUsuarioInicial() throws Exception{
        usuarioService.salvar(new Usuario("Administrador", "admin@hotel.com", "123", PerfilUsuario.ADMIN));
    }

    @Override
    public void start(Stage stage) throws Exception{
        this.stagePrincipal = stage;
        popularQuartosIniciais();
        popularUsuarioInicial();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent raizLogin = loader.load();

        LoginController loginController = loader.getController();
        loginController.setUsuarioService(usuarioService);
        loginController.setApp(this);

        Scene cena = new Scene(raizLogin, 700, 400);
        stage.setScene(cena);
        stage.setTitle("Sistema do Hotel - Login");
        stage.show();
    }

    public void abrirTelaPrincipal(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent raizPrincipal = loader.load();

        MainController mainController = loader.getController();
        mainController.setApp(this);
        mainController.mostrarCadastro();

        Scene cena = new Scene(raizPrincipal, 750, 500);
        stage.setScene(cena);
        stage.setTitle("Sistema do Hotel");
        stage.show();
    }

    public void voltarParaLogin(Stage stage) throws Exception{
        UsuarioService.logout();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent raizLogin = loader.load();
        LoginController loginController = loader.getController();
        loginController.setUsuarioService(usuarioService);
        loginController.setApp(this);
        Scene cena = new Scene(raizLogin, 700, 400);
        stage.setScene(cena);
        stage.setTitle("Sistema do Hotel - Login");
        stage.show();
    }

    public Parent carregarCadastro() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastro-hospede.fxml"));
        Parent raiz = loader.load();
        CadastroHospedeController controller = loader.getController();
        controller.setHospedeService(hospedeService);
        return raiz;
    }

    public Parent carregarReserva() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reserva-quarto.fxml"));
        Parent raiz = loader.load();
        ReservaController controller = loader.getController();
        controller.setServices(hospedeService, quartoService, gerenciador);
        return raiz;
    }

    public Parent carregarCalendario() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("calendario.fxml"));
        Parent raiz = loader.load();
        CalendarioController controller = loader.getController();
        controller.setServices(quartoService, gerenciador);
        return raiz;
    }

    public Parent carregarAuditoria() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("auditoria.fxml"));
        Parent raiz = loader.load();
        AuditoriaController controller = loader.getController();
        controller.setServices(hospedeService, quartoService, gerenciador, usuarioService);
        return raiz;
    }

    public Parent carregarCadastroUsuario() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastro-usuario.fxml"));
        Parent raiz = loader.load();
        CadastroUsuarioController controller = loader.getController();
        controller.setUsuarioService(usuarioService);
        return raiz;
    }
    public Parent carregarCadastroQuarto() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastro-quarto.fxml"));
        Parent raiz = loader.load();
        CadastroQuartoController controller = loader.getController();
        controller.setQuartoService(quartoService);
        return raiz;
    }

    public static void main(String[] args){
        launch(args);
    }
}