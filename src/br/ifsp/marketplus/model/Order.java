package br.ifsp.marketplus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Order {

    private final UUID id;
    private final UUID clientId;

    private final double totalPrice;
    private final long emittedDate;

    private final Set<OrderProduct> products;

    public OrderProduct getProduct(UUID productId) {
        for (OrderProduct product : products) {
            if(product.getProductId().equals(productId)) {
                return product;
            }
        }

        return null;
    }

}
