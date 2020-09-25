package br.ifsp.marketplus.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class OrderModel {

    private int id;

    private ClientModel clientModel;

    private List<ProductModel> productModels;

    private double totalPrice;

}
