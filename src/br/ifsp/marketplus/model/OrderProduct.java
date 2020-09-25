package br.ifsp.marketplus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class OrderProduct {

    private final UUID productId;
    private final UUID orderId;
    private int amount;
}
