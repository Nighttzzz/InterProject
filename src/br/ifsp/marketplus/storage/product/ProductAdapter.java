package br.ifsp.marketplus.storage.product;

import br.ifsp.marketplus.model.Product;
import br.ifsp.marketplus.storage.SQLAdapter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProductAdapter implements SQLAdapter<Product> {

    @Override
    public Product read(ResultSet set) throws SQLException {
        UUID id = UUID.fromString(set.getString("p_id"));
        UUID categoryId = UUID.fromString(set.getString("c_id"));

        String name = set.getString("p_name");

        double price = set.getDouble("p_price");
        double discount = set.getDouble("p_discount");

        return new Product(id, categoryId, name, price, discount);
    }

    @Override
    public void insert(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getId().toString());
        statement.setString(2, product.getCategoryId().toString());
        statement.setString(3, product.getName());

        statement.setDouble(4, product.getPrice());
        statement.setDouble(5, product.getDiscount());
    }

    @Override
    public void delete(PreparedStatement statement, Product object) throws SQLException {
        statement.setString(1, object.getId().toString());
    }

}
