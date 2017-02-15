package no.jan.rocket.modal;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jasand on 11.02.2017.
 */
public class FlightNameModal {

    public static String getFlightName() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Flight Name");
        Label label = new Label("Flight name:");
        TextField textField = new TextField(getDefaultNewFlightFileName());
        textField.setEditable(true);
        textField.setPrefWidth(200);
        HBox hBox = new HBox(10);
        hBox.setPrefWidth(Double.MAX_VALUE);
        hBox.getChildren().addAll(label, textField);
        hBox.setAlignment(Pos.CENTER);
        Button button = new Button("OK");
        button.setOnAction(e -> window.close());
        VBox layout = new VBox(10);
        layout.setPrefWidth(Double.MAX_VALUE);
        layout.getChildren().addAll(hBox, button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 350, 150);
        window.setScene(scene);
        window.showAndWait();
        return textField.getText();
    }

    private static String getDefaultNewFlightFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String filename = "flight-" + now.format(formatter);
        return filename;
    }

}
