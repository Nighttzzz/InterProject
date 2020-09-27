package br.ifsp.marketplus.manager;

import br.ifsp.marketplus.initializer.Initializer;
import br.ifsp.marketplus.model.Category;
import br.ifsp.marketplus.model.Client;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ClientManager implements Manager<Client> {

    private final Initializer main;

    @Getter
    private final List<Client> clients = new ArrayList<>();

    @Override
    public Client findById(UUID id) {
        if (clients.isEmpty()) return null;

        for (Client client : clients) {
            if (client.getId() == id) return client;
        }

        return null;
    }

    @Override
    public Client findByName(String name) {
        if (clients.isEmpty()) return null;

        for (Client client : clients) {
            if (client.getName().equalsIgnoreCase(name)) return client;
        }

        return null;
    }

    public Client findByCpf(int cpf) {
        if (clients.isEmpty()) return null;

        for (Client client : clients) {
            if (client.getCpf() == cpf) return client;
        }

        return null;
    }

    @Override
    public void insert(Client object) {
        main.getClientDao().insertOrUpdate(object.getId(), object);
        clients.add(object);
    }

}
