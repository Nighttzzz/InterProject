package br.ifsp.marketplus.dao.impl;

import br.ifsp.marketplus.Main;
import br.ifsp.marketplus.dao.DaoInterface;
import br.ifsp.marketplus.model.CategoryModel;
import br.ifsp.marketplus.model.ProductModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CategoryDao implements DaoInterface<CategoryModel> {

    private final Connection connection;

    public CategoryDao(Connection connection) {
        this.connection = connection;

        this.createTable();
    }

    public void createTable() {
        try (PreparedStatement statement = connection.prepareStatement(
              "CREATE TABLE IF NOT EXISTS `categories` (" +
                    "category_id  INTEGER NOT NULL, " +
                    "name         CHAR(36) NOT NULL, " +
                    "products     TEXT, " +
                    "PRIMARY KEY  (category_id));"
        )) {
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection<CategoryModel> getAll() {
        Set<CategoryModel> models = new HashSet<>();

        try (PreparedStatement statement = connection.prepareStatement(
              "SELECT * FROM `categories`;"
        )) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CategoryModel category = CategoryModel.builder()
                      .id(resultSet.getInt("category_id"))
                      .name(resultSet.getString("name"))
                      .build();

                String[] products = resultSet.getString("products").split(";");
                for (String product : products) {
                    if (product.isEmpty()) continue;

                    ProductModel productModel =
                          Main.getInitializer().getProductManager().getProduct(Integer.parseInt(product));

                    if (productModel == null) continue;

                    category.getProductModels().add(productModel);
                }

                models.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return models;
    }

    public void insertOrUpdate(CategoryModel model) {
        try(PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO `categories` VALUES (?, ?, ?) "
        )) {
            statement.setString(1, String.valueOf(model.getId()));
            statement.setString(2, model.getName());

            StringBuilder ids = new StringBuilder();
            for (ProductModel productModel : model.getProductModels()) {
                ids.append(productModel.getId()).append(";");
            }

            statement.setString(3, ids.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
