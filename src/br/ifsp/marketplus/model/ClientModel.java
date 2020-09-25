package br.ifsp.marketplus.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClientModel {

    private String name;
    private String email;

    private int cpf;
    private int rg;

    private long createdAt;

}
