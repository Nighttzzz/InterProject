package br.ifsp.marketplus.controller;

import br.ifsp.marketplus.Main;
import br.ifsp.marketplus.initializer.Initializer;
import br.ifsp.marketplus.manager.ClientManager;
import br.ifsp.marketplus.manager.OrderManager;
import br.ifsp.marketplus.manager.ProductManager;
import br.ifsp.marketplus.model.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.*;

public class OrderController {

    private List<Product> productArrayList = new ArrayList<>();

    @FXML
    private JFXListView<String> orderList;
    @FXML
    private JFXComboBox<String> categoriesBox;
    @FXML
    private JFXComboBox<String> productBox;
    @FXML
    private JFXTextField clientName;

    @FXML
    void clickCategory(ActionEvent event) {
        String categoryList = categoriesBox.getValue();

        Initializer initializer = Main.getInitializer();

        Category category = initializer.getCategoryManager().findByName(categoryList);
        if (category == null) return;

        ObservableList<String> items = FXCollections.observableArrayList();
        for (Product product : category.getProducts()) {
            items.add(product.getName());
        }

        productBox.setItems(items);
    }

    @FXML
    void clickProduct(MouseEvent event) {
        if (productBox.getValue() == null) return;

        Product product = Main.getInitializer().getProductManager().findByName(productBox.getValue());
        if (product == null) return;

        productArrayList.add(product);

        ObservableList<String> items = FXCollections.observableArrayList();
        for (Product products : productArrayList) {
            items.add(products.getName());
        }

        orderList.setItems(items);
    }

    Set<OrderProduct> createOrderProducts(UUID id) {
        Set<OrderProduct> orderProducts = new HashSet<>();

        for (Product product : productArrayList) {
            orderProducts.add(new OrderProduct(product.getId(), id, 1));
        }
        return orderProducts;
    }

    double getTotal() {
        double price = 0;

        for (Product product : productArrayList) {
            price += product.getPrice();
        }

        return price;
    }

    @FXML
    void createOrder(MouseEvent event) {
        Initializer initializer = Main.getInitializer();

        OrderManager orderManager = initializer.getOrderManager();
        Map<UUID, Order> orders = orderManager.getOrderMap();

        String name = clientName.getCharacters().toString();
        Client client = initializer.getClientManager().findByCpf(Long.parseLong(name));

        String categoryName = categoriesBox.getValue();
        Category category = initializer.getCategoryManager().findByName(categoryName);

        if (name.length() == 0 || category != null) return;

        UUID uuid = UUID.randomUUID();
        Set<OrderProduct> orderProducts = createOrderProducts(uuid);
        if (orderProducts == null) return;

        Order order = new Order(uuid, client.getId(),
              getTotal(), System.currentTimeMillis(), orderProducts);

        orders.put(uuid, order);

        initializer.getOrderDao().insertOrUpdate(order.getId(), order);

        Stage stage = (Stage) clientName.getScene().getWindow();
        stage.close();

    }

    @FXML
    void refresh(MouseEvent event) {
        refreshCategories();
    }

    void refreshCategories() {
        List<Category> categories = Main.getInitializer().getCategoryManager().getCategories();

        ObservableList<String> items = FXCollections.observableArrayList();
        for (Category category : categories) {
            items.add(category.getName());
        }

        categoriesBox.setItems(items);
    }

    void refreshOrder() {
        List<Category> categories = Main.getInitializer().getCategoryManager().getCategories();

        ObservableList<String> items = FXCollections.observableArrayList();
        for (Category category : categories) {
            items.add(category.getName());
        }

        categoriesBox.setItems(items);
    }

}
