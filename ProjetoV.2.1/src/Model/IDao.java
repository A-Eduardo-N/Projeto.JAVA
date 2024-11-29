package Model;

import java.util.List;

public interface IDao<T> {
    void adicionar(T t);
    void atualizar(T t);
    void remover(T t);
    List<T> listar();
}
