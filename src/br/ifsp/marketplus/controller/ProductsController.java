package br.ifsp.marketplus.controller;


import br.ifsp.marketplus.Main;
import br.ifsp.marketplus.manager.ProductManager;
import br.ifsp.marketplus.model.Category;
import br.ifsp.marketplus.model.Product;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

public class ProductsController {

    @FXML
    private JFXTextField productName;
    @FXML
    private JFXTextField productPrice;
    @FXML
    private JFXComboBox<String> categoryBox;
    @FXML
    private JFXListView<String> productsList;

    @FXML
    void refreshCategories(MouseEvent event) {
        List<Category> categories = Main.getInitializer().getCategoryManager().getCategories();

        ObservableList<String> items = FXCollections.observableArrayList();
        for (Category category : categories) {
            items.add(category.getName());
        }

        categoryBox.setItems(items);

    }

    @FXML
    void refresh(MouseEvent event) {
        refreshProductListView();
    }

    @FXML
    void createProduct(MouseEvent event) {
        ProductManager productManager = Main.getInitializer().getProductManager();
        List<Product> products = productManager.getProducts();

        String name = productName.getCharacters().toString();
        String rawPrice = productPrice.getCharacters().toString();
        String category = categoryBox.getValue();

        if (name.length() == 0 || productManager.findByName(name) != null
          || rawPrice.length() == 0 || category == null) return;

        double price = Double.parseDouble(rawPrice);

        Category categoryByName = Main.getInitializer().getCategoryManager().findByName(category);
        if (categoryByName == null) return;

        Product product = new Product(UUID.randomUUID(), categoryByName.getId(), name, price, 0);
        products.add(product);

        Main.getInitializer().getProductDao().insertOrUpdate(product.getId(), product);

        refreshProductListView();
    }

    private void refreshProductListView() {
        ProductManager productManager = Main.getInitializer().getProductManager();
        List<Product> products = productManager.getProducts();

        ObservableList<String> items = FXCollections.observableArrayList();

        for (Product product : products) {
            items.add(product.getName() + " - R$" + new DecimalFormat("#,###.#").format(product.getPrice()));
        }

        productsList.setItems(items);
    }

}
