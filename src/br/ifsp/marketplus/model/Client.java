package br.ifsp.marketplus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Client {

    private final UUID id;

    private final String name;
    private final String email;

    private final long cpf;

    private final long createdAt;
}
