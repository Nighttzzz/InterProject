package br.ifsp.marketplus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Product {

    private final UUID id;
    private final UUID categoryId;

    private final String name;

    private final double price;
    private final double discount;
}
