package no.jan.rocket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import no.jan.rocket.comm.AltimeterData;
import no.jan.rocket.comm.IMUData;
import no.jan.rocket.comm.RocketCommandReply;
import no.jan.rocket.flight.AltimeterListener;
import no.jan.rocket.flight.CommandResponseListener;
import no.jan.rocket.flight.FlightController;
import no.jan.rocket.flight.IMUListener;
import no.jan.rocket.modal.AlertBox;
import no.jan.rocket.modal.FlightNameModal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;

/**
 * Created by jasand on 10.02.2017.
 */
public class FlightControlTabController {

    private FlightController flightController;
    PrintStream logfilePrintStream;

    Long timestampStart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    ObservableList<XYChart.Data<Long, Double>> altitudeData = FXCollections.observableArrayList();
    XYChart.Series<Long, Double> altitudeSeries = new LineChart.Series<>("Altitude", altitudeData);
    AltimeterData prevAltimeterData;

    @FXML
    private Button newFlightButton;
    @FXML
    private Button connectButton;
    @FXML
    private Button heartbeatButton;
    @FXML
    private Button initializeButton;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button closeButton;

    // sensor values
    @FXML Label accX;
    @FXML Label accY;
    @FXML Label accZ;
    @FXML Label gyroX;
    @FXML Label gyroY;
    @FXML Label gyroZ;
    @FXML Label magnetX;
    @FXML Label magnetY;
    @FXML Label magnetZ;
    @FXML Label pitchVal;
    @FXML Label rollVal;
    @FXML Label azimuthVal;

    @FXML
    private LineChart<Long, Double> flightProfileChart;

    @FXML
    private TextArea consoleText;

    @FXML
    private void initialize() {

        ObservableList<XYChart.Series<Long, Double>> lineChartData = FXCollections.observableArrayList(
                altitudeSeries
        );
        flightProfileChart.setData(lineChartData);

        System.out.println("Initialized...");
    }

    @FXML
    private void onNewFlightButtonClick(ActionEvent event) {
        System.out.println("Button clicked!!! This: " + this);
        String flightName = FlightNameModal.getFlightName();
        System.out.println("Filename: " + flightName);
        if (newFlight(flightName)) {
            connectButton.setDisable(false);
            heartbeatButton.setDisable(true);
            closeButton.setDisable(false);
            newFlightButton.setDisable(true);
            altitudeData.clear();
            prevAltimeterData = null;
            timestampStart = null;
        } else {
            connectButton.setDisable(true);
            closeButton.setDisable(true);
            AlertBox.show("Flight Name", "Could not create flight.");
        }
    }


    @FXML
    private void onConnectButton(ActionEvent event) {
        System.out.println("onConnectButton");
        String portName = "cu.usbserial-DA013LW1";
        heartbeatButton.setDisable(false);
        startButton.setDisable(false);
        stopButton.setDisable(true);
        closeButton.setDisable(false);
        connectButton.setDisable(true);
        flightController = new FlightController(portName);
        flightController.setCommandResponseListener(new CommandResponseListener() {
            @Override
            public void receiveCommandResponse(RocketCommandReply rocketCommandReply) {
                try {
                    String response = new ObjectMapper().writeValueAsString(rocketCommandReply);
                    log(response);
                    if (rocketCommandReply.getReplyFor().equals("STRT")) {
                        // TODO
                    } else if (rocketCommandReply.getReplyFor().equals("INIT")) {
                        // TODO ???
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
        flightController.setImuListener(new IMUListener() {
            @Override
            public void receiveIMUData(final IMUData imuData) {
                try {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            accX.setText(Double.toString(imuData.getAx()));
                            accY.setText(Double.toString(imuData.getAy()));
                            accZ.setText(Double.toString(imuData.getAz()));
                            gyroX.setText(Double.toString(imuData.getGx()));
                            gyroY.setText(Double.toString(imuData.getGy()));
                            gyroZ.setText(Double.toString(imuData.getGz()));
                            magnetX.setText(Double.toString(imuData.getMx()));
                            magnetY.setText(Double.toString(imuData.getMy()));
                            magnetZ.setText(Double.toString(imuData.getMz()));

                            Double roll = Math.atan2(imuData.getAy(), imuData.getAz());
                            Double pitch = Math.atan2(imuData.getAx(), Math.sqrt(imuData.getAy() * imuData.getAy() + imuData.getAz() * imuData.getAz()));
                            roll *= 180.0 / Math.PI;
                            pitch *= 180.0 / Math.PI;
                            DecimalFormat df = new DecimalFormat("##0.00");
                            rollVal.setText(df.format(roll));
                            pitchVal.setText(df.format(pitch));
                        }
                    });
                    String response = new ObjectMapper().writeValueAsString(imuData);
                    log(response);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
        flightController.setAltimeterListener(new AltimeterListener() {
            @Override
            public void receiveAltimeterData(AltimeterData altimeterData) {
                try {

                    if (timestampStart == null) {
                        timestampStart = altimeterData.getTs();
                    }
                    XYChart.Data<Long, Double> altitude = new XYChart.Data<Long, Double>(
                            altimeterData.getTs() - timestampStart,
                            altimeterData.getAlt());
                    altitudeData.add(altitude);
                    if (prevAltimeterData != null) {
                        long timediff = altimeterData.getTs() - prevAltimeterData.getTs();
                        Double altDiff = altimeterData.getAlt() - prevAltimeterData.getAlt();
                        Double verticalSpeed = altDiff / ((double) timediff / 1000.0d);
                        // TODO: Set in GUI
                    }
                    prevAltimeterData = altimeterData;

                    String response = new ObjectMapper().writeValueAsString(altimeterData);
                    log(response);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void onHeartbeatButton(ActionEvent event) {
        System.out.println("onHeartbeatButton");
        flightController.heartbeat();
    }

    @FXML
    private void onInitializeButton(ActionEvent event) {
        System.out.println("onInitializeButton");
    }

    @FXML
    private void onStartButton(ActionEvent event) {
        System.out.println("onStartButton");
        flightController.start();
        startButton.setDisable(true);
        stopButton.setDisable(false);
    }

    @FXML
    private void onStopButton(ActionEvent event) {
        System.out.println("onStopButton");
        flightController.stop();
        startButton.setDisable(false);
        stopButton.setDisable(true);
    }

    @FXML
    private void onCloseButton(ActionEvent event) {
        System.out.println("onCloseButton");
        heartbeatButton.setDisable(true);
        startButton.setDisable(true);
        stopButton.setDisable(true);
        closeButton.setDisable(true);
        connectButton.setDisable(true);
        flightController.close();
        logfilePrintStream.close();
        logfilePrintStream = null;
        newFlightButton.setDisable(false);
    }


    private boolean newFlight(String flightName) {
        try {
            File logdir = new File(getLogdirName());
            if (!logdir.exists()) {
                logdir.mkdir();
            }
            File logfile = new File(getLogdirName() + flightName + ".log");
            logfile.createNewFile();
            logfilePrintStream = new PrintStream(logfile);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getLogdirName() {
        String logdir = System.getProperty("user.home");
        if (!logdir.endsWith("/")) {
            logdir = logdir + "/";
        }
        return logdir + "mission-control/";
    }

    private void log(String logEntry) {
        logfilePrintStream.println(logEntry);
        consoleText.appendText(logEntry + "\n");
    }
}
