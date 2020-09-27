package br.ifsp.marketplus.manager;

import br.ifsp.marketplus.initializer.Initializer;
import br.ifsp.marketplus.model.Category;
import br.ifsp.marketplus.model.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CategoryManager implements Manager<Category> {

    private final Initializer main;

    @Getter
    private final List<Category> categories = new ArrayList<>();

    public Category findById(UUID id) {
        if (categories.isEmpty()) return null;

        for (Category category : categories) {
            if (category.getId() == id) return category;
        }

        return null;
    }

    public Category findByName(String name) {
        if (categories.isEmpty()) return null;

        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) return category;
        }
        return null;
    }

    @Override
    public void insert(Category object) {
        main.getCategoryDao().insertOrUpdate(object.getId(), object);
        categories.add(object);
    }


}
