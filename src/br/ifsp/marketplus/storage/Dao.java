package br.ifsp.marketplus.storage;

import java.util.Collection;

public interface Dao<K, V> {

    void createTable();

    void insertOrUpdate(K key, V model);

    Collection<V> getAll();

}
