package br.edu.ifba.saj.ads.poo.business;

import br.edu.ifba.saj.ads.poo.data.UsuarioDAO;
import br.edu.ifba.saj.ads.poo.model.Usuario;

public class UsuarioService extends GenericServiceImpl<Usuario, Long>{

    private static Usuario usuarioLogado;

    public UsuarioService(){
        super(new UsuarioDAO());
    }

    @Override
    public void validar(Usuario usuario) throws RegraDeNegocioException{
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new RegraDeNegocioException("E-mail inválido.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new RegraDeNegocioException("Senha inválida.");
        }
    }

    public Usuario autenticar(String email, String senha) throws RegraDeNegocioException{
        for (Usuario u : buscarTodos()) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                usuarioLogado = u;
                return u;
            }
        }
        throw new RegraDeNegocioException("E-mail ou senha incorretos.");
    }

    public static Usuario getUsuarioLogado(){
        return usuarioLogado;
    }
}