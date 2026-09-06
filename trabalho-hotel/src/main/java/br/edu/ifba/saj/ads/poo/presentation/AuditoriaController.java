package br.edu.ifba.saj.ads.poo.presentation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import br.edu.ifba.saj.ads.poo.model.AbstractModel;
import br.edu.ifba.saj.ads.poo.model.Usuario;
import br.edu.ifba.saj.ads.poo.business.HospedeService;
import br.edu.ifba.saj.ads.poo.business.QuartoService;
import br.edu.ifba.saj.ads.poo.business.GerenciadorReservas;
import br.edu.ifba.saj.ads.poo.business.UsuarioService;

public class AuditoriaController{

    @FXML private ComboBox<String> comboTipo;
    @FXML private TableView<LinhaAuditoria> tabelaAuditoria;
    @FXML private TableColumn<LinhaAuditoria, String> colunaRegistro;
    @FXML private TableColumn<LinhaAuditoria, String> colunaCriadoPor;
    @FXML private TableColumn<LinhaAuditoria, String> colunaCriadoEm;
    @FXML private TableColumn<LinhaAuditoria, String> colunaAlteradoPor;
    @FXML private TableColumn<LinhaAuditoria, String> colunaAlteradoEm;

    private HospedeService hospedeService;
    private QuartoService quartoService;
    private GerenciadorReservas gerenciadorReservas;
    private UsuarioService usuarioService;

    public void setServices(HospedeService hospedeService, QuartoService quartoService, GerenciadorReservas gerenciadorReservas, UsuarioService usuarioService){
        this.hospedeService = hospedeService;
        this.quartoService = quartoService;
        this.gerenciadorReservas = gerenciadorReservas;
        this.usuarioService = usuarioService;
        inicializarColunas();
        inicializarCombo();
    }

    private void inicializarColunas(){
        colunaRegistro.setCellValueFactory(new PropertyValueFactory<>("registro"));
        colunaCriadoPor.setCellValueFactory(new PropertyValueFactory<>("criadoPor"));
        colunaCriadoEm.setCellValueFactory(new PropertyValueFactory<>("criadoEm"));
        colunaAlteradoPor.setCellValueFactory(new PropertyValueFactory<>("alteradoPor"));
        colunaAlteradoEm.setCellValueFactory(new PropertyValueFactory<>("alteradoEm"));
    }

    private void inicializarCombo(){
        comboTipo.setItems(FXCollections.observableArrayList("Hóspedes", "Quartos", "Reservas", "Usuários"));
        comboTipo.setOnAction(e -> carregarTabela());
        comboTipo.setValue("Hóspedes");
        carregarTabela();
    }

    private void carregarTabela(){
        String tipo = comboTipo.getValue();
        List<? extends AbstractModel<?>> registros;

        switch(tipo){
            case "Quartos":
                registros = quartoService.buscarTodos();
                break;
            case "Reservas":
                registros = gerenciadorReservas.buscarTodos();
                break;
            case "Usuários":
                registros = usuarioService.buscarTodos();
                break;
            default:
                registros = hospedeService.buscarTodos();
        }

        tabelaAuditoria.setItems(FXCollections.observableArrayList(mapear(registros)));
    }

    private List<LinhaAuditoria> mapear(List<? extends AbstractModel<?>> registros){
        List<LinhaAuditoria> linhas = new ArrayList<>();
        for(AbstractModel<?> r : registros){
            linhas.add(new LinhaAuditoria(
                r.toString(),
                formatarUsuario(r.getCreatedBy()),
                formatarData(r.getCreatedAt()),
                formatarUsuario(r.getUpdatedBy()),
                formatarData(r.getUpdatedAt())
            ));
        }
        return linhas;
    }

    private String formatarUsuario(Usuario usuario){
        return usuario != null ? usuario.getNome() : "Desconhecido";
    }

    private String formatarData(LocalDateTime data){
        return data != null ? data.toString() : "-";
    }
}