package br.ifsp.marketplus.dao;

import java.util.Collection;

public interface DaoInterface<T> {

    void createTable();

    void insertOrUpdate(T model);

    Collection<T> getAll();

}
