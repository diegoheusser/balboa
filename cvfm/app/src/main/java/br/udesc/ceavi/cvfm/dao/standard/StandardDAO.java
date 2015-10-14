package br.udesc.ceavi.cvfm.dao.standard;

import java.util.List;

public interface StandardDAO<T> {

    void insert(T t);
    void delete(T t);
    void update(T t);
    List<T> seekAll();

}
