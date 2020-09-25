package br.ifsp.marketplus.initializer;

import br.ifsp.marketplus.dao.impl.ProductDao;
import br.ifsp.marketplus.manager.ProductManager;
import br.ifsp.marketplus.model.EarningsModel;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class Initializer {

    private Connection connection;

    private ProductDao productDao;

    private EarningsModel earningsModel;
    private ProductManager productManager;

    public void onEnable() {
        initConnection();

        productDao = new ProductDao(connection);
        productDao.createTable();

        earningsModel = EarningsModel.builder()
              .daily(0)
              .weekly(0)
              .monthly(0)
              .build();

        productManager = new ProductManager();
        productManager.getProductModels().addAll(productDao.getAll());

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
