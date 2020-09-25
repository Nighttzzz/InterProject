package br.ifsp.marketplus.dao.impl;

import br.ifsp.marketplus.dao.DaoInterface;
import br.ifsp.marketplus.model.CategoryModel;
import br.ifsp.marketplus.model.ProductModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductDao implements DaoInterface<ProductModel> {

    private final Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;

        this.createTable();
    }

    public void createTable() {
        try (PreparedStatement statement = connection.prepareStatement(
              "CREATE TABLE IF NOT EXISTS `products` (" +
                    "product_id   INTEGER NOT NULL, " +
                    "name         CHAR(36) NOT NULL, " +
                    "price        DOUBLE NOT NULL, " +
                    "discount     DOUBLE NOT NULL, " +
                    "PRIMARY KEY  (product_id));"
        )) {
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection<ProductModel> getAll() {
        Set<ProductModel> models = new HashSet<>();

        try (PreparedStatement statement = connection.prepareStatement(
              "SELECT * FROM `products`;"
        )) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ProductModel product = ProductModel.builder()
                      .id(resultSet.getInt("product_id"))
                      .name(resultSet.getString("name"))
                      .price(resultSet.getDouble("price"))
                      .discount(resultSet.getDouble("discount"))
                      .build();
                models.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return models;
    }

    public void insertOrUpdate(ProductModel model) {
        try(PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO `products` VALUES (?, ?, ?, ?) "
        )) {
            statement.setString(1, String.valueOf(model.getId()));
            statement.setString(2, model.getName());
            statement.setString(3, String.valueOf(model.getPrice()));
            statement.setString(4, String.valueOf(model.getDiscount()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
