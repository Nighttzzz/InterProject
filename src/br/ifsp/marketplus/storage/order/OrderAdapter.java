package br.ifsp.marketplus.storage.order;

import br.ifsp.marketplus.model.Order;
import br.ifsp.marketplus.model.OrderProduct;
import br.ifsp.marketplus.storage.SQLAdapter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class OrderAdapter implements SQLAdapter<Order> {

    private static final OrderProductAdapter PRODUCT_ADAPTER = new OrderProductAdapter();

    @Override
    public Order read(ResultSet set) throws SQLException {
        UUID id = UUID.fromString(set.getString("id"));
        UUID clientId = UUID.fromString(set.getString("client_id"));

        double totalPrice = set.getDouble("total_price");
        long emittedDate = set.getLong("emitted_date");

        Set<OrderProduct> products = new HashSet<>();

        do {
            products.add(PRODUCT_ADAPTER.read(set));
            set.next();
        } while (!set.isAfterLast());

        return new Order(id, clientId, totalPrice, emittedDate, products);
    }

    @Override
    public void insert(PreparedStatement statement, Order order) throws SQLException {
        statement.setString(1, order.getId().toString());
        statement.setString(2, order.getClientId().toString());

        statement.setDouble(3, order.getTotalPrice());
        statement.setLong(4, order.getEmittedDate());
    }

    @Override
    public void delete(PreparedStatement statement, Order order) throws SQLException {
        statement.setString(1, order.getId().toString());
    }
}
