package no.jan.rocket.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by jasand on 10.02.2017.
 */

public class HistoryTabController {

    @FXML private Button chooseFlightButton;

    @FXML
    private void onChooseFlightButton(ActionEvent event) {
        Stage window = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Flight Data File");
        fileChooser.setInitialDirectory(new File(FlightControlTabController.getLogdirName()));
        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            System.out.println("Filåpning Suksess");
        } else {
            System.out.println("Feilet filåpning");
        }
    }

}
