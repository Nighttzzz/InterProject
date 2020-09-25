package br.ifsp.marketplus.manager;

import br.ifsp.marketplus.model.ProductModel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    @Getter
    private List<ProductModel> productModels = new ArrayList<>();

    public ProductModel getProduct(int id) {
        if (productModels.isEmpty()) return null;

        for (ProductModel productModel : productModels) {
            if (productModel.getId() == id) return productModel;
        }
        return null;
    }

    public ProductModel getProduct(String name) {
        if (productModels.isEmpty()) return null;

        for (ProductModel productModel : productModels) {
            if (productModel.getName().equalsIgnoreCase(name)) return productModel;
        }
        return null;
    }


}
