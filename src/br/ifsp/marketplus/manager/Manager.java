package br.ifsp.marketplus.manager;

import java.util.List;
import java.util.UUID;

public interface Manager<K> {

    K findById(UUID id);

    K findByName(String name);

    void insert(K object);

}
