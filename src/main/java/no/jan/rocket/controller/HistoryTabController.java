package no.jan.rocket.controller;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.jan.rocket.controller.history.FlightDataCalculator;
import no.jan.rocket.controller.history.FlightDataFileLoader;
import no.jan.rocket.controller.history.FlightDataWrapper;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by jasand on 10.02.2017.
 */

public class HistoryTabController {

    @FXML private Button chooseFlightButton;
    @FXML private ProgressIndicator progressIndicator;

    @FXML private Label maxAltLabel;
    @FXML private Label maxSpeedLabel;
    @FXML private Label minSpeedLabel;
    @FXML private Label maxAccel;
    @FXML private Label maxAccelX;
    @FXML private Label maxAccelY;
    @FXML private Label maxAccelZ;
    @FXML private Label minAccel;
    @FXML private Label minAccelX;
    @FXML private Label minAccelY;
    @FXML private Label minAccelZ;


    private FlightDataWrapper flightDataWrapper;

    @FXML
    private void onChooseFlightButton(ActionEvent event) {
        Stage window = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Flight Data File");
        fileChooser.setInitialDirectory(new File(FlightControlTabController.getLogdirName()));
        File file = fileChooser.showOpenDialog(window);
        progressIndicator.setVisible(true);
        loadFileInBackground(file);
        if (file != null) {
            System.out.println("Filåpning Suksess");
        } else {
            System.out.println("Feilet filåpning");
        }
    }


    private void loadFileInBackground(final File file) {

        Task<FlightDataWrapper> loadFileTask = new Task<FlightDataWrapper>() {

            private FlightDataWrapper flightDataWrapper = new FlightDataWrapper();

            {
                setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        FlightDataWrapper flightDataWrapper = getValue();
                        DecimalFormat df = new DecimalFormat("##0.00");
                        maxAltLabel.setText(df.format(flightDataWrapper.getMaxAlt()));
                        maxSpeedLabel.setText(df.format(flightDataWrapper.getMaxSpeed()));
                        minSpeedLabel.setText(df.format(flightDataWrapper.getMinSpeed()));
                        maxAccel.setText(df.format(flightDataWrapper.getMaxAcc()));
                        maxAccelX.setText(df.format(flightDataWrapper.getMaxAccX()));
                        maxAccelY.setText(df.format(flightDataWrapper.getMaxAccY()));
                        maxAccelZ.setText(df.format(flightDataWrapper.getMaxAccZ()));
                        minAccel.setText(df.format(flightDataWrapper.getMinAcc()));
                        minAccelX.setText(df.format(flightDataWrapper.getMinAccX()));
                        minAccelY.setText(df.format(flightDataWrapper.getMinAccY()));
                        minAccelZ.setText(df.format(flightDataWrapper.getMinAccZ()));
                        progressIndicator.setVisible(false);
                    }
                });
                setOnFailed(wse -> {
//                    label.setText(wse.getSource().getException().getMessage());
                    Throwable thr = wse.getSource().getException();
                    thr.printStackTrace();
                    progressIndicator.setVisible(false);
                });
            }

            @Override
            protected FlightDataWrapper call() throws Exception {
                flightDataWrapper = FlightDataFileLoader.load(file);
                flightDataWrapper = FlightDataCalculator.calculate(flightDataWrapper);
                return flightDataWrapper;
            }
        };
        progressIndicator.setVisible(true);
        Thread bgThread = new Thread(loadFileTask);
        bgThread.setDaemon(true);
        bgThread.start();
    }
}
