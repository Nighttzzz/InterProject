package br.ifsp.marketplus.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductModel {

    private int id;

    private String name;

    private double price;
    private double discount;

    public boolean hasDiscount() {
        return discount > 0;
    }

}
