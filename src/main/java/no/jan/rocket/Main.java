package no.jan.rocket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by jasand on 10.02.2017.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MissionControlMain.fxml"));
        primaryStage.setTitle("Mission Control");
        primaryStage.setScene(new Scene(root, 1250, 820));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
