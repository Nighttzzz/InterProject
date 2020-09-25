package br.ifsp.marketplus.manager;

import br.ifsp.marketplus.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OrderManager {

    private final Map<UUID, Order> orderMap = new HashMap<>();

    public void putOrder(UUID id, Order order) {
        orderMap.put(id, order);
    }

    public Order getOrder(UUID id) {
        return orderMap.get(id);
    }

    public double sumLastOrders(TimeUnit unit, long value) {
        long l = System.currentTimeMillis() + unit.toMillis(value);
        double totalPrice = 0;

        for (Order order : orderMap.values()) {
            if(order.getEmittedDate() < l) continue;

            totalPrice += order.getTotalPrice();
        }

        return totalPrice;
    }
}
