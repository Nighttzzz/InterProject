package br.ifsp.marketplus.controller;


import br.ifsp.marketplus.Main;
import br.ifsp.marketplus.manager.ProductManager;
import br.ifsp.marketplus.model.Product;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.UUID;

public class ProductsController {

    @FXML
    private JFXTextField productName;
    @FXML
    private JFXTextField productPrice;


    @FXML
    private JFXListView<String> productsList;

    @FXML
    void createProduct(MouseEvent event) {
        ProductManager productManager = Main.getInitializer().getProductManager();
        List<Product> products = productManager.getProducts();

        String name = productName.getCharacters().toString();
        String rawPrice = productPrice.getCharacters().toString();

        if (name.length() == 0 || productManager.getProduct(name) != null
          || rawPrice.length() == 0) return;

        double price = Double.parseDouble(rawPrice);

        Product product = new Product(UUID.randomUUID(), UUID.randomUUID(), name, 0, price);
        products.add(product);

        Main.getInitializer().getProductDao().insertOrUpdate(product.getId(), product);

        refreshProductListView();
    }

    private void refreshProductListView() {
        ProductManager productManager = Main.getInitializer().getProductManager();
        List<Product> products = productManager.getProducts();

        ObservableList<String> items = FXCollections.observableArrayList();

        for (Product product : products) {
            items.add(product.getName());
        }

        productsList.setItems(items);
    }

}
