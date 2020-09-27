package br.ifsp.marketplus.controller;

import br.ifsp.marketplus.Main;
import br.ifsp.marketplus.initializer.Initializer;
import br.ifsp.marketplus.manager.OrderManager;
import br.ifsp.marketplus.model.Order;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class MainController {

    @FXML
    private JFXButton newOrderButton;
    @FXML
    private JFXButton orderButton;

    @FXML
    private Text dailyEarning;
    @FXML
    private Text weeklyEarning;
    @FXML
    private Text monthlyEarning;

    @FXML
    void closeClick(MouseEvent event) {
        Stage stage = (Stage) dailyEarning.getScene().getWindow();
        stage.close();
    }

    @FXML
    void productClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/br/ifsp/marketplus/views/products.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setResizable(false);
        stage.setTitle("Produtos");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickSet(MouseEvent event) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

        OrderManager orderManager = Main.getInitializer().getOrderManager();

        orderManager.putOrder(UUID.randomUUID(), new Order(null, null, 111, System.currentTimeMillis(), null));
        orderManager.putOrder(UUID.randomUUID(), new Order(null, null, 9999999999999999999D, 0, null));


        double daily = orderManager.sumLastOrders(TimeUnit.DAYS, 1);
        double weekly = orderManager.sumLastOrders(TimeUnit.DAYS, 7);
        double monthly = orderManager.sumLastOrders(TimeUnit.DAYS, 30);

        dailyEarning.setText("R$ " + decimalFormat.format(daily));
        weeklyEarning.setText("R$ " + decimalFormat.format(weekly));
        monthlyEarning.setText("R$ " + decimalFormat.format(monthly));

    }



}
