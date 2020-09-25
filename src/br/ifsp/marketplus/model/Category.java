package br.ifsp.marketplus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Category {

    private final UUID id;
    private final String name;

    private final Set<Product> products;

}
