package br.ifsp.marketplus.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CategoryModel {

    private int id;

    private String name;

    private List<ProductModel> productModels;

    public boolean isEmpty() {
        return productModels.isEmpty();
    }

}
