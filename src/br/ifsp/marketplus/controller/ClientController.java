package br.ifsp.marketplus.controller;

import br.ifsp.marketplus.Main;
import br.ifsp.marketplus.manager.CategoryManager;
import br.ifsp.marketplus.manager.ClientManager;
import br.ifsp.marketplus.manager.ProductManager;
import br.ifsp.marketplus.model.Category;
import br.ifsp.marketplus.model.Client;
import br.ifsp.marketplus.model.Product;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.UUID;

public class ClientController {

    @FXML
    private JFXListView<String> clientList;

    @FXML
    private JFXTextField clientName;

    @FXML
    private JFXTextField clientCpf;

    @FXML
    private JFXTextField clientEmail;

    @FXML
    void createClient(MouseEvent event) {
        ClientManager clientManager = Main.getInitializer().getClientManager();
        List<Client> clients = clientManager.getClients();

        String name = clientName.getCharacters().toString();
        long cpf = Long.parseLong(clientCpf.getCharacters().toString());
        String email = clientEmail.getCharacters().toString();

        if (name.length() == 0 || clientManager.findByCpf(cpf) != null
              || email.length() == 0) return;

        Client client = new Client(UUID.randomUUID(), name, email, cpf, System.currentTimeMillis());

        clients.add(client);

        Main.getInitializer().getClientDao().insertOrUpdate(client.getId(), client);

        refreshClientListView();
    }

    @FXML
    void refresh(MouseEvent event) {
        refreshClientListView();
    }

    private void refreshClientListView() {
        ClientManager clientManager = Main.getInitializer().getClientManager();
        List<Client> clients = clientManager.getClients();

        ObservableList<String> items = FXCollections.observableArrayList();

        for (Client client : clients) {
            items.add(client.getName() + " - " + client.getCpf());
        }

        clientList.setItems(items);
    }

}
