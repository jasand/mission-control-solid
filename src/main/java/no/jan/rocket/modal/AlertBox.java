package no.jan.rocket.modal;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by jasand on 11.02.2017.
 */
public class AlertBox {

    public static void show(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label(message);
        Button button = new Button("OK");
        button.setOnAction(e -> window.close());
        VBox layout = new VBox();
        layout.getChildren().addAll(label, button);
        label.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
