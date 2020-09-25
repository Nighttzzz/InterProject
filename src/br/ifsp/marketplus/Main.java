package br.ifsp.marketplus;

import br.ifsp.marketplus.initializer.Initializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

public class Main extends Application {

    @Getter
    private static Initializer initializer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/br/ifsp/marketplus/views/main.fxml"));
        Scene scene = new Scene(root);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        initializer = new Initializer();
        initializer.onEnable();

        launch(args);
    }

}
