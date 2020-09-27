package br.ifsp.marketplus.initializer;

import br.ifsp.marketplus.manager.CategoryManager;
import br.ifsp.marketplus.manager.ClientManager;
import br.ifsp.marketplus.manager.OrderManager;
import br.ifsp.marketplus.model.Category;
import br.ifsp.marketplus.model.Product;
import br.ifsp.marketplus.storage.category.CategoryDao;
import br.ifsp.marketplus.storage.client.ClientDao;
import br.ifsp.marketplus.storage.order.OrderDao;
import br.ifsp.marketplus.storage.product.ProductDao;
import br.ifsp.marketplus.manager.ProductManager;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class Initializer {

    private Connection connection;

    private ProductDao productDao;
    private CategoryDao categoryDao;
    private OrderDao orderDao;
    private ClientDao clientDao;

    private ProductManager productManager;
    private OrderManager orderManager;
    private CategoryManager categoryManager;
    private ClientManager clientManager;

    public void onEnable() {
        initConnection();

        productDao = new ProductDao(connection);
        productDao.createTable();

        categoryDao = new CategoryDao(connection);
        categoryDao.createTable();

        clientDao = new ClientDao(connection);

        orderDao = new OrderDao(connection);
        orderManager = new OrderManager();

        productManager = new ProductManager(this);
        categoryManager = new CategoryManager(this);
        clientManager = new ClientManager(this);

        categoryManager.getCategories().addAll(getCategoryDao().getAll());
        for (Category category : categoryDao.getAll()) {
            productManager.getProducts().addAll(category.getProducts());
        }
        clientManager.getClients().addAll(clientDao.getAll());

    }

    private void initConnection() {
        try {
            this.connection = DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/tests?" +
                        "characterEncoding=latin1&" +
                        "useTimezone=true&" +
                        "serverTimezone=America/Sao_Paulo",
                  "root",
                  "admin"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
