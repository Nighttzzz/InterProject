package br.ifsp.marketplus.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLAdapter<T> {

    T read(ResultSet set) throws SQLException;

    void insert(PreparedStatement statement, T object) throws SQLException;

    void delete(PreparedStatement statement, T object) throws SQLException;

}
