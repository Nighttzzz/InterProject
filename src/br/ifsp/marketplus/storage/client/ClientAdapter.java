package br.ifsp.marketplus.storage.client;

import br.ifsp.marketplus.model.Client;
import br.ifsp.marketplus.storage.SQLAdapter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ClientAdapter implements SQLAdapter<Client> {

    @Override
    public Client read(ResultSet set) throws SQLException {
        UUID id = UUID.fromString(set.getString("id"));

        String name = set.getString("name");
        String email = set.getString("email");

        int cpf = set.getInt("cpf");

        long createdAt = set.getLong("created_at");

        return new Client(id, name, email, cpf, createdAt);
    }

    @Override
    public void insert(PreparedStatement statement, Client client) throws SQLException {
        statement.setString(1, client.getId().toString());

        statement.setString(2, client.getName());
        statement.setString(3, client.getEmail());

        statement.setInt(4, client.getCpf());
        statement.setLong(5, client.getCreatedAt());
    }
}
