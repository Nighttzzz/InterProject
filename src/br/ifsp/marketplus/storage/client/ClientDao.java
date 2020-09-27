package br.ifsp.marketplus.storage.client;

import br.ifsp.marketplus.model.Client;
import br.ifsp.marketplus.storage.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ClientDao implements Dao<UUID, Client> {

    private final Connection connection;
    private final ClientAdapter adapter;

    public ClientDao(Connection connection) {
        this.connection = connection;
        this.adapter = new ClientAdapter();
    }

    public void createTable() {
        try (PreparedStatement statement = connection.prepareStatement(
          "CREATE TABLE IF NOT EXISTS `clients` (" +
            "id         CHAR(36) NOT NULL PRIMARY KEY, " +
            "name       VARCHAR(80) NOT NULL, " +
            "email      VARCHAR(80) NOT NULL, " +
            "cpf        INT(9) NOT NULL, " +
            "created_at LONG NOT NULL);"
        )) {
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrUpdate(UUID key, Client client) {
        try (PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO `clients` VALUES (?, ?, ?, ?, ?);"
        )) {
            adapter.insert(statement, client);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromId(UUID key, Client client) {
        try (PreparedStatement statement = connection.prepareStatement(
              "DELETE FROM `clients` WHERE id = ?;"
        )) {
            adapter.delete(statement, client);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection<Client> getAll() {
        Set<Client> clients = new HashSet<>();

        try (PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM `clients`"
        )) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                clients.add(adapter.read(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

}
