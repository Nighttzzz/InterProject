package br.ifsp.marketplus.manager;

import br.ifsp.marketplus.model.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductManager {

    @Getter
    private final List<Product> products = new ArrayList<>();

    public Product getProduct(UUID id) {
        if (products.isEmpty()) return null;

        for (Product productModel : products) {
            if (productModel.getId() == id) return productModel;
        }

        return null;
    }

    public Product getProduct(String name) {
        if (products.isEmpty()) return null;

        for (Product productModel : products) {
            if (productModel.getName().equalsIgnoreCase(name)) return productModel;
        }
        return null;
    }


}
