package no.jan.rocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.jan.rocket.comm.AltimeterData;
import no.jan.rocket.comm.IMUData;
import no.jan.rocket.comm.RocketCommandReply;
import no.jan.rocket.flight.AltimeterListener;
import no.jan.rocket.flight.CommandResponseListener;
import no.jan.rocket.flight.FlightController;
import no.jan.rocket.flight.IMUListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jasand on 15.01.2017.
 */
public class MissionControlCenter extends Application {

    // Menu button
    Button newFlightButton;
    Button historyButton;

    PrintStream logfilePrintStream;

    // CTRL buttons
    Button connectButton;
    Button heartbeatButton;
    Button startButton;
    Button stopButton;
    Button closeButton;

    ProgressIndicator progressIndicator;

    FlightController flightController;

    // sensor values
    Text accX;
    Text accY;
    Text accZ;
    Text gyroX;
    Text gyroY;
    Text gyroZ;
    Text magnetX;
    Text magnetY;
    Text magnetZ;

    TextArea consoleText;

    Long timestampStart;
    private LineChart chart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    ObservableList<XYChart.Data<Long, Double>> altitudeData = FXCollections.observableArrayList();
    XYChart.Series<Long,Double> altitudeSeries = new LineChart.Series<>("Altitude", altitudeData);
    ObservableList<XYChart.Data<Long, Double>> verticalSpeedData = FXCollections.observableArrayList();
    XYChart.Series<Long,Double> verticalSpeedSeries = new LineChart.Series<>("Speed", verticalSpeedData);
    AltimeterData prevAltimeterData;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mission Control Center");
        primaryStage.setScene(createPrimaryScene());
        primaryStage.show();
    }

    public Scene createPrimaryScene() {
        BorderPane primaryLayout = new BorderPane();

        //Top content
        ToolBar toolbar = new ToolBar();
        newFlightButton = new Button("New Flight");
        newFlightButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (newFlight()) {
                    connectButton.setDisable(false);
                    closeButton.setDisable(false);
                    newFlightButton.setDisable(true);
                    historyButton.setDisable(true);
                    altitudeData.clear();
                    verticalSpeedData.clear();
                    prevAltimeterData = null;
                    timestampStart = null;
                } else {
                    connectButton.setDisable(true);
                    closeButton.setDisable(true);
                }
            }
        });
        historyButton = new Button("History");
        toolbar.getItems().addAll(newFlightButton, historyButton);
        toolbar.setStyle("-fx-background-color: #444444;");
        primaryLayout.setTop(toolbar);

        //Left content
        Label label1 = new Label("Vessel orientation:");
        label1.setFont(new Font(18));
        label1.setTextFill(Color.web("#EEEEEE"));
        Button leftButton = new Button("left");
        VBox leftVbox = new VBox();
        leftVbox.setPrefWidth(205);
        leftVbox.setStyle("-fx-background-color: #336699;");
        leftVbox.setPadding(new Insets(15, 12, 15, 12));
        leftVbox.setSpacing(20);
        leftVbox.getChildren().addAll(label1, leftButton);
        primaryLayout.setLeft(leftVbox);

        //Right content
        Label rightlabel1 = new Label("Controls");
        rightlabel1.setFont(new Font(18));
        rightlabel1.setTextFill(Color.web("#EEEEEE"));
        progressIndicator = new ProgressIndicator();
//        progressIndicator.setVisible(false);
        progressIndicator.setPrefWidth(75);
        progressIndicator.setPrefHeight(75);
        connectButton = new Button("Connect");
        connectButton.setMaxWidth(Double.MAX_VALUE);
        connectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String portName = "cu.usbserial-DA013LW1";
                heartbeatButton.setDisable(false);
                startButton.setDisable(false);
                stopButton.setDisable(true);
                closeButton.setDisable(false);
                connectButton.setDisable(true);
                progressIndicator.setVisible(true);
                progressIndicator.setProgress(0.5);
                flightController = new FlightController(portName);
                flightController.setCommandResponseListener(new CommandResponseListener() {
                    @Override
                    public void receiveCommandResponse(RocketCommandReply rocketCommandReply) {
                        try {
                            String response = new ObjectMapper().writeValueAsString(rocketCommandReply);
                            log(response);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                });
                flightController.setImuListener(new IMUListener() {
                    @Override
                    public void receiveIMUData(IMUData imuData) {
                        try {
                            accX.setText(Double.toString(imuData.getAx()));
                            accY.setText(Double.toString(imuData.getAy()));
                            accZ.setText(Double.toString(imuData.getAz()));
                            gyroX.setText(Double.toString(imuData.getGx()));
                            gyroY.setText(Double.toString(imuData.getGy()));
                            gyroZ.setText(Double.toString(imuData.getGz()));
                            magnetX.setText(Double.toString(imuData.getMx()));
                            magnetY.setText(Double.toString(imuData.getMy()));
                            magnetZ.setText(Double.toString(imuData.getMz()));
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
                                long timediff = altimeterData.getTs()-prevAltimeterData.getTs();
                                Double altDiff = altimeterData.getAlt()-prevAltimeterData.getAlt();
                                Double verticalSpeed = altDiff / ((double)timediff/1000.0d);
                                XYChart.Data<Long, Double> speedData = new XYChart.Data<Long, Double>(
                                        altimeterData.getTs() - timestampStart,
                                        verticalSpeed);
                                verticalSpeedData.add(speedData);
                            }
                            prevAltimeterData = altimeterData;

                            String response = new ObjectMapper().writeValueAsString(altimeterData);
                            log(response);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                });
                progressIndicator.setVisible(false);
            }
        });
        connectButton.setDisable(true);

        heartbeatButton = new Button("Heartbeat");
        heartbeatButton.setDisable(true);
        heartbeatButton.setMaxWidth(Double.MAX_VALUE);
        heartbeatButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                flightController.heartbeat();
            }
        });

        startButton = new Button("Start");
        startButton.setDisable(true);
        startButton.setMaxWidth(Double.MAX_VALUE);
        startButton.setOnAction(e -> {
                flightController.start();
                startButton.setDisable(true);
                stopButton.setDisable(false);
        });
        stopButton = new Button("Stop");
        stopButton.setDisable(true);
        stopButton.setMaxWidth(Double.MAX_VALUE);
        stopButton.setOnAction(e -> {
                flightController.stop();
                startButton.setDisable(false);
                stopButton.setDisable(true);
        });
        closeButton = new Button("Close");
        closeButton.setDisable(true);
        closeButton.setMaxWidth(Double.MAX_VALUE);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                heartbeatButton.setDisable(true);
                startButton.setDisable(true);
                stopButton.setDisable(true);
                closeButton.setDisable(true);
                connectButton.setDisable(true);
                flightController.close();
                logfilePrintStream.close();
                logfilePrintStream = null;
                newFlightButton.setDisable(false);
                historyButton.setDisable(false);
            }
        });

        VBox rightVbox = new VBox();
        rightVbox.setPrefWidth(200);
        rightVbox.setPadding(new Insets(15, 12, 15, 12));
        rightVbox.setSpacing(20);
        rightVbox.setStyle("-fx-background-color: #336699;");
        rightVbox.getChildren().addAll(rightlabel1, connectButton, heartbeatButton, startButton, stopButton, closeButton);
        primaryLayout.setRight(rightVbox);

        //Center content

        Label flightProfileLabel = new Label("Flight profile:");
        Label vericalSpeedLabel = new Label("Vertical speed:");
        VBox chartBox = new VBox();
        chartBox.setPadding(new Insets(15, 12, 15, 12));
        chartBox.getChildren().addAll(flightProfileLabel, createAltitudeChart(), vericalSpeedLabel, createSpeedChart());


        Label centerLabelRight = new Label("CurrentValues:");
        VBox flightDataBox = new VBox();
        flightDataBox.setPadding(new Insets(15, 12, 15, 12));

        VBox accelVBox = new VBox();
        accelVBox.setPadding(new Insets(15, 12, 0, 12));
        Label accelLabel = new Label("Accelerometer (g):");
        HBox accelHBox = new HBox();
        accelHBox.setSpacing(10);
        Label accXLabel = new Label("X:");
        accX = new Text("NA");
        Label accYLabel = new Label("Y:");
        accY = new Text("NA");
        Label accZLabel = new Label("Z:");
        accZ = new Text("NA");
        accelHBox.getChildren().addAll(accXLabel, accX, accYLabel, accY, accZLabel, accZ);
        accelVBox.getChildren().addAll(accelLabel, accelHBox);

        VBox gyroVBox = new VBox();
        gyroVBox.setPadding(new Insets(15, 12, 0, 12));
        Label gyroscopeLabel = new Label("Gyroscope (deg/s):");
        HBox gyroHBox = new HBox();
        gyroHBox.setSpacing(10);
        Label gyroXLabel = new Label("X:");
        gyroX = new Text("NA");
        Label gyroYLabel = new Label("Y:");
        gyroY = new Text("NA");
        Label gyroZLabel = new Label("Z:");
        gyroZ = new Text("NA");
        gyroHBox.getChildren().addAll(gyroXLabel, gyroX, gyroYLabel, gyroY, gyroZLabel, gyroZ);
        gyroVBox.getChildren().addAll(gyroscopeLabel, gyroHBox);

        VBox magnetVBox = new VBox();
        magnetVBox.setPadding(new Insets(15, 12, 0, 12));
        Label magnetLabel = new Label("Magnetic field (Gauss):");
        HBox magnetHBox = new HBox();
        magnetHBox.setSpacing(10);
        Label magnetXLabel = new Label("X:");
        magnetX = new Text("NA");
        Label magnetYLabel = new Label("Y:");
        magnetY = new Text("NA");
        Label magnetZLabel = new Label("Z:");
        magnetZ = new Text("NA");
        magnetHBox.getChildren().addAll(magnetXLabel, magnetX, magnetYLabel, magnetY, magnetZLabel, magnetZ);
        magnetVBox.getChildren().addAll(magnetLabel, magnetHBox);


        flightDataBox.getChildren().addAll(centerLabelRight, accelVBox, gyroVBox, magnetVBox);

        HBox centerBox = new HBox();
        centerBox.getChildren().addAll(chartBox, flightDataBox);
        primaryLayout.setCenter(centerBox);

        //Bottom content
        Label bottomLabel = new Label("Flight console:");
        bottomLabel.setStyle("-fx-background-color: #444444;");
        bottomLabel.setTextFill(Color.web("#EEEEEE"));
        bottomLabel.setPrefWidth(Double.MAX_VALUE);
        consoleText = new TextArea();
        consoleText.setPrefHeight(200);
        consoleText.setFont(new Font(12));
        consoleText.setText("Ready...\n");
        VBox bottomVbox = new VBox();
        bottomVbox.getChildren().addAll(bottomLabel, consoleText);
        primaryLayout.setBottom(bottomVbox);

        Scene scene = new Scene(primaryLayout, 1200, 800);
        return scene;

    }

    public Parent createAltitudeChart() {
        xAxis = new NumberAxis();
        xAxis.setLabel("Time (ms)");
        yAxis = new NumberAxis();
        yAxis.setLabel("Altitude (m)");
        ObservableList<XYChart.Series<Long,Double>> lineChartData = FXCollections.observableArrayList(
                altitudeSeries
        );
        chart = new LineChart(xAxis, yAxis, lineChartData);
        return chart;
    }

    public Parent createSpeedChart() {
        xAxis = new NumberAxis();
        xAxis.setLabel("Time (ms)");
        yAxis = new NumberAxis();
        yAxis.setLabel("Speed (m/s)");
        ObservableList<XYChart.Series<Long,Double>> lineChartData = FXCollections.observableArrayList(
                verticalSpeedSeries
        );
        chart = new LineChart(xAxis, yAxis, lineChartData);
        return chart;
    }

    private boolean newFlight() {
        try {
            File logdir = new File(getLogdirName());
            if (!logdir.exists()) {
                logdir.mkdir();
            }
            File logfile = new File(getLogdirName() + getNewFlightFileName());
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

    private String getNewFlightFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String filename = "flight-" + now.format(formatter)  + ".log";
        return filename;
    }

    private void log(String logEntry) {
        logfilePrintStream.println(logEntry);
        consoleText.appendText(logEntry + "\n");
    }

}
