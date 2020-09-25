package br.ifsp.marketplus.storage.product;

import br.ifsp.marketplus.model.Product;
import br.ifsp.marketplus.storage.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

public class ProductDao implements Dao<UUID, Product> {

    private final Connection connection;
    private final ProductAdapter adapter;

    public ProductDao(Connection connection) {
        this.connection = connection;
        this.adapter = new ProductAdapter();
    }

    @Override
    public void createTable() {
        try (PreparedStatement statement = connection.prepareStatement(
          "CREATE TABLE IF NOT EXISTS `products` (" +
            "id          CHAR(36) NOT NULL PRIMARY KEY, " +
            "category_id CHAR(36) NOT NULL, " +
            "name        VARCHAR(36) NOT NULL, " +
            "price       DOUBLE NOT NULL, " +
            "discount    DOUBLE NOT NULL," +
            "FOREIGN KEY (category_id) REFERENCES categories(id));"
        )) {
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrUpdate(UUID key, Product model) {
        try (PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO `products` VALUES (?, ?, ?, ?, ?);"
        )) {
            adapter.insert(statement, model);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Product> getAll() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
