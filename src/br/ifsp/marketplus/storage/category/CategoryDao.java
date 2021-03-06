package br.ifsp.marketplus.storage.category;

import br.ifsp.marketplus.model.Category;
import br.ifsp.marketplus.storage.Dao;
import br.ifsp.marketplus.storage.product.ProductAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CategoryDao implements Dao<UUID, Category> {

    private final Connection connection;
    private final CategoryAdapter adapter;
    private final ProductAdapter productAdapter;

    public CategoryDao(Connection connection) {
        this.connection = connection;
        this.adapter = new CategoryAdapter();
        this.productAdapter = new ProductAdapter();
    }

    public void createTable() {
        try (PreparedStatement statement = connection.prepareStatement(
          "CREATE TABLE IF NOT EXISTS `categories` (" +
            "id   CHAR(36) NOT NULL PRIMARY KEY, " +
            "name VARCHAR(36) NOT NULL);"
        )) {
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrUpdate(UUID key, Category model) {
        try (PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO `categories` VALUES (?, ?);"
        )) {
            adapter.insert(statement, model);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromId(UUID key, Category category) {
        try (PreparedStatement statement = connection.prepareStatement(
              "DELETE FROM `categories` WHERE id = ?;"
        )) {
            adapter.delete(statement, category);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection<Category> getAll() {
        try (PreparedStatement statement = connection.prepareStatement(
              "SELECT " +
                    "categories.id AS c_id, " +
                    "categories.name AS c_name, " +
                    "products.id AS p_id, " +
                    "products.name AS p_name, " +
                    "products.price AS p_price, " +
                    "products.discount AS p_discount " +
                    "FROM `categories` " +
                    "INNER JOIN products ON categories.id = products.category_id;"
        )) {
            ResultSet resultSet = statement.executeQuery();
            Map<UUID, Category> categoryMap = new HashMap<>();

            while (resultSet.next()) {
                final UUID id = UUID.fromString(resultSet.getString("c_id"));
                final String name = resultSet.getString("c_name");

                categoryMap.computeIfAbsent(
                      id,
                      $ -> new Category(id, name, new HashSet<>())
                ).getProducts().add(productAdapter.read(resultSet));
            }

            return new HashSet<>(categoryMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptySet();
    }

}
