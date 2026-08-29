package br.edu.ifba.saj.ads.poo.business;
import java.util.List;

public interface GenericService<T, ID> {
    T criar(T entidade) throws Exception;
    List<T> listarTodos();
}
