package br.ifsp.marketplus.storage.order;

import br.ifsp.marketplus.model.OrderProduct;
import br.ifsp.marketplus.storage.SQLAdapter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OrderProductAdapter implements SQLAdapter<OrderProduct> {
    
    @Override
    public OrderProduct read(ResultSet set) throws SQLException {
        UUID productId = UUID.fromString(set.getString("product_id"));
        UUID orderId = UUID.fromString(set.getString("order_id"));
        int amount = set.getInt("amount");

        return new OrderProduct(productId, orderId, amount);
    }

    @Override
    public void insert(PreparedStatement statement, OrderProduct object) throws SQLException {
        statement.setString(1, object.getProductId().toString());
        statement.setString(2, object.getOrderId().toString());
        statement.setInt(3, object.getAmount());
    }

    @Override
    public void delete(PreparedStatement statement, OrderProduct object) throws SQLException {
        statement.setString(1, object.getOrderId().toString()
    }
}
