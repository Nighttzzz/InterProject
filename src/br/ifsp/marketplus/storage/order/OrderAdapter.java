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


    @Override
    public Order read(ResultSet set) throws SQLException {
        UUID id = UUID.fromString(set.getString("o_id"));
        UUID clientId = UUID.fromString(set.getString("o_client_id"));

        double totalPrice = set.getDouble("o_total_price");
        long emittedDate = set.getLong("o_emitted_date");

        return new Order(id, clientId, totalPrice, emittedDate, new HashSet<>());
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
