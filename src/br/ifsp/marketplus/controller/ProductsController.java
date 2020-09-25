package br.ifsp.marketplus.controller;


import br.ifsp.marketplus.Main;
import br.ifsp.marketplus.manager.ProductManager;
import br.ifsp.marketplus.model.ProductModel;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import javax.swing.text.html.ListView;
import java.util.List;

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
        List<ProductModel> productModels = productManager.getProductModels();

        String name = productName.getCharacters().toString();
        String price = productPrice.getCharacters().toString();

        if (name.length() == 0) return;
        if (productManager.getProduct(name) != null) return;
        if (price.length() == 0) return;

        ProductModel productModel = ProductModel.builder()
              .id(productModels.size() + 1)
              .name(name)
              .discount(0)
              .price(Double.parseDouble(price))
              .build();

        productModels.add(productModel);
        Main.getInitializer().getProductDao().insertOrUpdate(productModel);

        ObservableList<String> items = FXCollections.observableArrayList();

        for (ProductModel model : productModels) {
            items.add(model.getName());
        }

        productsList.setItems(items);
    }

}
