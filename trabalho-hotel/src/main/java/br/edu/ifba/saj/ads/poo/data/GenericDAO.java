package br.edu.ifba.saj.ads.poo.data;
import java.util.List;

public interface GenericDAO<T, ID> {
    void salvar(T entidade);
    T buscarPorId(ID id);
    List<T> listarTodos();
    void deletar(ID id);
}
