package br.ifsp.marketplus.controller;

import br.ifsp.marketplus.Main;
import br.ifsp.marketplus.model.EarningsModel;
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
        Parent root = FXMLLoader.load(getClass().getResource("/br/ifsp/marketplus/views/products.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setResizable(false);
        stage.setTitle("Produtos");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void clickSet(MouseEvent event) {
        EarningsModel earningsModel = Main.getInitializer().getEarningsModel();

        earningsModel.addDaily(10.1);
        earningsModel.addWeekly(100.12);
        earningsModel.addMonthly(1000.19);

        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

        dailyEarning.setText("R$ " + decimalFormat.format(earningsModel.getDaily()));
        weeklyEarning.setText("R$ " + decimalFormat.format(earningsModel.getWeekly()));
        monthlyEarning.setText("R$ " + decimalFormat.format(earningsModel.getMonthly()));

    }



}
