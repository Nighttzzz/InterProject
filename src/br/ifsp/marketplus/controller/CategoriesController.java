package br.ifsp.marketplus.controller;

import br.ifsp.marketplus.Main;
import br.ifsp.marketplus.manager.CategoryManager;
import br.ifsp.marketplus.manager.ProductManager;
import br.ifsp.marketplus.model.Category;
import br.ifsp.marketplus.model.Product;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class CategoriesController {

    @FXML
    private JFXListView<String> categoriesList;
    @FXML
    private JFXTextField categoryName;

    @FXML
    void refresh(MouseEvent event) {
        refreshCategoriesListView();
    }

    @FXML
    void createCategory(MouseEvent event) {
        CategoryManager categoryManager = Main.getInitializer().getCategoryManager();
        List<Category> categories = categoryManager.getCategories();

        String name = categoryName.getCharacters().toString();

        if (name.length() == 0 || categoryManager.findByName(name) != null) return;

        Category category = new Category(UUID.randomUUID(), name, new HashSet<>());

        categories.add(category);

        Main.getInitializer().getCategoryDao().insertOrUpdate(category.getId(), category);

        refreshCategoriesListView();
    }

    private void refreshCategoriesListView() {
        CategoryManager categoryManager = Main.getInitializer().getCategoryManager();
        List<Category> categories = categoryManager.getCategories();

        ObservableList<String> items = FXCollections.observableArrayList();

        for (Category category : categories) {
            items.add(category.getName());
        }

        categoriesList.setItems(items);
    }

}
