package br.ifsp.marketplus.initializer;

import br.ifsp.marketplus.model.EarningsModel;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class Initializer {

    private Connection connection;

    private EarningsModel earningsModel;

    public void onEnable() {
        initConnection();

        earningsModel = EarningsModel.builder()
              .daily(0)
              .weekly(0)
              .monthly(0)
              .build();

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
