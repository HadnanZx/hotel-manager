package br.edu.ifba.saj.ads.poo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import br.edu.ifba.saj.ads.poo.business.HospedeService;
import br.edu.ifba.saj.ads.poo.business.QuartoService;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;
import br.edu.ifba.saj.ads.poo.data.HospedeDAO;
import br.edu.ifba.saj.ads.poo.data.QuartoDAO;
import br.edu.ifba.saj.ads.poo.data.ReservaDAO;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.TipoQuarto;
import br.edu.ifba.saj.ads.poo.presentation.CadastroHospedeController;
import br.edu.ifba.saj.ads.poo.presentation.MainController;
import br.edu.ifba.saj.ads.poo.presentation.ReservaController;
import br.edu.ifba.saj.ads.poo.presentation.ListaReservasController;

public class App extends Application{

    private HospedeService hospedeService = new HospedeService(new HospedeDAO());
    private QuartoService quartoService = new QuartoService(new QuartoDAO());
    private GerenciadorReservas gerenciador = new GerenciadorReservas(new ReservaDAO());
    private Stage stagePrincipal;

    private void popularQuartosIniciais() throws Exception {
        quartoService.salvar(new Quarto(101, TipoQuarto.SOLTEIRO, 150.0));
        quartoService.salvar(new Quarto(102, TipoQuarto.SOLTEIRO, 150.0));
        quartoService.salvar(new Quarto(201, TipoQuarto.CASAL, 250.0));
        quartoService.salvar(new Quarto(202, TipoQuarto.CASAL, 250.0));
        quartoService.salvar(new Quarto(301, TipoQuarto.SUITE, 400.0));
    }

    @Override
    public void start(Stage stage) throws Exception{
        this.stagePrincipal = stage;
        popularQuartosIniciais();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent raizPrincipal = loader.load();

        MainController mainController = loader.getController();
        mainController.setApp(this);
        mainController.mostrarCadastro();

        Scene cena = new Scene(raizPrincipal, 700, 400);
        stage.setScene(cena);
        stage.setTitle("Sistema do Hotel");
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

    public Parent carregarLista() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lista-reservas.fxml"));
        Parent raiz = loader.load();
        ListaReservasController controller = loader.getController();
        controller.setGerenciador(gerenciador);
        return raiz;
    }

    public static void main(String[] args){
        launch(args);
    }
}