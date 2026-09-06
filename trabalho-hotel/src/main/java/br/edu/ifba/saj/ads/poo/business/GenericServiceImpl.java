package br.edu.ifba.saj.ads.poo.business;

import java.time.LocalDateTime;
import java.util.List;
import br.edu.ifba.saj.ads.poo.model.AbstractModel;
import br.edu.ifba.saj.ads.poo.data.GenericDAO;

public abstract class GenericServiceImpl<T extends AbstractModel<ID>, ID>
        implements GenericService<T, ID>{

    private final GenericDAO<T, ID> dao;

    public GenericServiceImpl(GenericDAO<T, ID> dao){
        this.dao = dao;
    }

    @Override
    public ID salvar(T entidade) throws RegraDeNegocioException{
        validar(entidade);
        entidade.setCreatedBy(UsuarioService.getUsuarioLogado());
        entidade.setUpdatedBy(UsuarioService.getUsuarioLogado());
        return dao.salvar(entidade);
    }

    @Override
    public void atualizar(T entidade) throws RegraDeNegocioException{
        validar(entidade);
        entidade.setUpdatedBy(UsuarioService.getUsuarioLogado());
        dao.atualizar(entidade);
    }

    @Override
    public T buscarPorId(ID id){
        return dao.buscarPorId(id);
    }

    @Override
    public void deletar(ID id){
        T entidade = dao.buscarPorId(id);
        if(entidade != null){
            entidade.setDeletedAt(LocalDateTime.now());
            entidade.setDeletedBy(UsuarioService.getUsuarioLogado());
            dao.atualizar(entidade);
        }
    }

    @Override
    public List<T> buscarTodos(){
        return dao.buscarTodos();
    }
}