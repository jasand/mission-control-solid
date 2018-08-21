package no.jan.rocket.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private Button chooseFlightButton;
    @FXML
    private Label fileName;
    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Label maxAltLabel;
    @FXML
    private Label maxSpeedLabel;
    @FXML
    private Label minSpeedLabel;
    @FXML
    private Label maxAccel;
    @FXML
    private Label maxAccelX;
    @FXML
    private Label maxAccelY;
    @FXML
    private Label maxAccelZ;
    @FXML
    private Label minAccel;
    @FXML
    private Label minAccelX;
    @FXML
    private Label minAccelY;
    @FXML
    private Label minAccelZ;
    @FXML
    private LineChart<Long, Double> historyChart;
    @FXML
    private Slider sliderLow;
    @FXML
    private Slider sliderHigh;

    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    ObservableList<XYChart.Data<Long, Double>> altitudeData = FXCollections.observableArrayList();
    XYChart.Series<Long, Double> altitudeSeries = new LineChart.Series<>("Altitude", altitudeData);

    private FlightDataWrapper loadedFlight;

    @FXML
    private void initialize() {

        ObservableList<XYChart.Series<Long, Double>> lineChartData = FXCollections.observableArrayList(
                altitudeSeries
        );
        historyChart.setData(lineChartData);

        sliderLow.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                double newVal = new_val.doubleValue();
                double otherVal = sliderHigh.getValue();
                if (newVal >= otherVal) {
                    sliderHigh.adjustValue(newVal + 1000);
                }
            }
        });

        sliderHigh.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                double newVal = new_val.doubleValue();
                double otherVal = sliderLow.getValue();
                if (newVal <= otherVal) {
                    sliderLow.adjustValue(newVal - 1000);
                }
            }
        });

        System.out.println("Initialized...");
    }

    @FXML
    private void onSliderMouseReleased(MouseEvent e) {
        System.out.println("Mouse released: " + sliderLow.getValue() + " - " + sliderHigh.getValue());
        if (loadedFlight != null) {
            displayAltitudeData((long) sliderLow.getValue(), (long) sliderHigh.getValue());
        }
    }

    private void displayAltitudeData(long start, long stop) {
        altitudeData.clear();
        Long timestampStart = loadedFlight.getStartTs();
        Double altitudeBaseline = loadedFlight.getBaseAlt();
        loadedFlight.getAltimeterDataList().forEach(altimeterData -> {
            if (altimeterData.getTs() >= start && altimeterData.getTs() <= stop) {
                XYChart.Data<Long, Double> altitude = new XYChart.Data<Long, Double>(
                        altimeterData.getTs() - start,
                        altimeterData.getAlt() - altitudeBaseline);
                altitudeData.add(altitude);
            }
        });
    }

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
                        fileName.setText(file.getName());

                        loadedFlight = flightDataWrapper;

                        sliderLow.setMin(loadedFlight.getStartTs());
                        sliderLow.setMax(loadedFlight.getStopTs());
                        sliderLow.setMajorTickUnit((loadedFlight.getStopTs() - loadedFlight.getStartTs()) / 4);
                        sliderLow.setValue(loadedFlight.getStartTs());

                        sliderHigh.setMin(loadedFlight.getStartTs());
                        sliderHigh.setMax(loadedFlight.getStopTs());
                        sliderHigh.setMajorTickUnit((loadedFlight.getStopTs() - loadedFlight.getStartTs()) / 4);
                        sliderHigh.setValue(loadedFlight.getStopTs());

                        System.out.println("MIN/MAX: " + loadedFlight.getStartTs() + "/" + loadedFlight.getStopTs());

                        displayAltitudeData(loadedFlight.getStartTs(), loadedFlight.getStopTs());

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
