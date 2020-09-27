package br.ifsp.marketplus.manager;

import br.ifsp.marketplus.initializer.Initializer;
import br.ifsp.marketplus.model.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductManager implements Manager<Product>{

    private final Initializer main;

    @Getter
    private final List<Product> products = new ArrayList<>();

    @Override
    public Product findById(UUID id) {
        if (products.isEmpty()) return null;

        for (Product productModel : products) {
            if (productModel.getId() == id) return productModel;
        }

        return null;
    }

    @Override
    public Product findByName(String name) {
        if (products.isEmpty()) return null;

        for (Product productModel : products) {
            if (productModel.getName().equalsIgnoreCase(name)) return productModel;
        }
        return null;
    }

    @Override
    public void insert(Product object) {
        main.getProductDao().insertOrUpdate(object.getId(), object);
        products.add(object);
    }


}
