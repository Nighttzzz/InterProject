package br.ifsp.marketplus.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Client {

    private final UUID id;

    private final String name;
    private final String email;

    private final long cpf;

    private final long createdAt;
}
