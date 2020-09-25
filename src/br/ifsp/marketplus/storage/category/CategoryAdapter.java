package br.ifsp.marketplus.storage.category;

import br.ifsp.marketplus.model.Category;
import br.ifsp.marketplus.model.Product;
import br.ifsp.marketplus.storage.SQLAdapter;
import br.ifsp.marketplus.storage.product.ProductAdapter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CategoryAdapter implements SQLAdapter<Category> {

    private static final ProductAdapter PRODUCT_ADAPTER = new ProductAdapter();

    @Override
    public Category read(ResultSet set) throws SQLException {
        UUID id = UUID.fromString(set.getString("c_id"));
        String name = set.getString("c_name");
        Set<Product> products = new HashSet<>();

        do {
            products.add(PRODUCT_ADAPTER.read(set));
            set.next();
        } while (!set.isAfterLast());

        return new Category(id, name, products);
    }

    @Override
    public void insert(PreparedStatement statement, Category category) throws SQLException {
        statement.setString(1, category.getId().toString());
        statement.setString(2, category.getName());
    }
}
