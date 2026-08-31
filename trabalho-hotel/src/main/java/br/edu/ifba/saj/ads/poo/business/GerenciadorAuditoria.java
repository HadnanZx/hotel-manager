package br.edu.ifba.saj.ads.poo.business;

import br.edu.ifba.saj.ads.poo.data.RegistroAuditoriaDAO;
import br.edu.ifba.saj.ads.poo.model.RegistroAuditoria;
import br.edu.ifba.saj.ads.poo.model.Usuario;

public class GerenciadorAuditoria extends GenericServiceImpl<RegistroAuditoria, Long> {

    private static GerenciadorAuditoria instancia;

    public GerenciadorAuditoria() {
        super(new RegistroAuditoriaDAO());
        instancia = this;
    }

    @Override
    public void validar(RegistroAuditoria entidade) {
    }

    public static void registrar(String acao) {
        if (instancia == null) return;
        Usuario autor = UsuarioService.getUsuarioLogado();
        RegistroAuditoria registro = new RegistroAuditoria(acao, autor);
        try {
            instancia.salvar(registro);
        } catch (RegraDeNegocioException e) {
        }
    }
}