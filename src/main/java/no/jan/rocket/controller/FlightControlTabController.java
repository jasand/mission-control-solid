package no.jan.rocket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Affine;
import javafx.scene.transform.MatrixType;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import no.jan.rocket.ahrs.MadgwickAHRS;
import no.jan.rocket.ahrs.Quaternion;
import no.jan.rocket.comm.AltimeterData;
import no.jan.rocket.comm.IMUData;
import no.jan.rocket.comm.RocketCommand;
import no.jan.rocket.comm.RocketCommandReply;
import no.jan.rocket.flight.AltimeterListener;
import no.jan.rocket.flight.CommandResponseListener;
import no.jan.rocket.flight.FlightController;
import no.jan.rocket.flight.IMUListener;
import no.jan.rocket.graphics.GraphicsBuilder;
import no.jan.rocket.modal.AlertBox;
import no.jan.rocket.modal.CommPortSelectModal;
import no.jan.rocket.modal.FlightNameModal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;

import static no.jan.rocket.controller.history.FlightDataCalculator.calculateAzimuth;
import static no.jan.rocket.controller.history.FlightDataCalculator.calculatePitch;
import static no.jan.rocket.controller.history.FlightDataCalculator.calculateRoll;

/**
 * Created by jasand on 10.02.2017.
 */
public class FlightControlTabController {

    private FlightController flightController;
    PrintStream logfilePrintStream;
    private long imuCounter = 0;
    private boolean initializing = false;
    private boolean started = false;
    private long imuSamplesToInitialize = 100; // assuming 20 Hz, 5 sec
    private Double altitudeBaseline = 0.0;
    private long altCounter = 0;


    Long timestampStart;
    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;
    ObservableList<XYChart.Data<Long, Double>> altitudeData = FXCollections.observableArrayList();
    XYChart.Series<Long, Double> altitudeSeries = new LineChart.Series<>("Altitude", altitudeData);
    AltimeterData prevAltimeterData;

    @FXML private Button newFlightButton;
    @FXML private Button connectButton;
    @FXML private Button heartbeatButton;
    @FXML private Button initializeButton;
    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private Button closeButton;

    @FXML private ProgressIndicator progressIndicator;

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

    @FXML Label baseAlt;
    @FXML Label temp;

    // Calculated
    @FXML Label pitchVal;
    @FXML Label rollVal;
    @FXML Label azimuthVal;
    @FXML Label vertSpeed;
    @FXML Label minVertSpeed;
    @FXML Label maxVertSpeed;
    @FXML Label calcVertSpeed;  // Calculated from IMU
    @FXML Label minCalcVertSpeed;
    @FXML Label maxCalcVertSpeed;
    private Double minSpeed = 0.0;
    private Double maxSpeed = 0.0;


    @FXML
    private LineChart<Long, Double> flightProfileChart;

    @FXML
    private TextArea consoleText;

    @FXML
    private SubScene subScene3d;
    private PerspectiveCamera camera;
    private Rotate cameraRotateX, cameraRotateY, cameraRotateZ;
    private Translate cameraTranslate;

    MeshView rocket3d = GraphicsBuilder.createRocketMeshView();
    private MadgwickAHRS madgwickAHRS = new MadgwickAHRS(20, 0.2);
    double gxBias = 0.0;
    double gyBias = 0.0;
    double gzBias = 0.0;

    @FXML
    private void initialize() {

        ObservableList<XYChart.Series<Long, Double>> lineChartData = FXCollections.observableArrayList(
                altitudeSeries
        );
        flightProfileChart.setData(lineChartData);

        initialize3d();

        System.out.println("Initialized...");
    }

    @FXML
    private void onNewFlightButtonClick(ActionEvent event) {
        System.out.println("Button clicked!!! This: " + this);
        String flightName = FlightNameModal.getFlightName();
        System.out.println("Filename: " + flightName);
        if (newFlight(flightName)) {
            setReadyForFlightState();
            altitudeData.clear();
            prevAltimeterData = null;
            timestampStart = null;
        } else {
            setClosedState();
            AlertBox.show("Flight Name", "Could not create flight.");
        }
    }


    @FXML
    private void onConnectButton(ActionEvent event) {
        CommPortSelectModal commPortSelectModal = new CommPortSelectModal();
        String portName = commPortSelectModal.getSelectedCommPort();
        progressIndicator.setVisible(true);
        flightController = createFlightController(portName);
        setConnectedState();
        progressIndicator.setVisible(false);
    }

    @FXML
    private void onHeartbeatButton(ActionEvent event) {
        consoleLog("CMD: " + RocketCommand.HRTB.name());
        flightController.heartbeat();
    }

    @FXML
    private void onInitializeButton(ActionEvent event) {
        consoleLog("CMD: " + RocketCommand.INIT.name());
        progressIndicator.setVisible(true);
        flightController.initialize();
    }

    @FXML
    private void onStartButton(ActionEvent event) {
        consoleLog("CMD: " + RocketCommand.STRT.name());
        progressIndicator.setVisible(true);
        flightController.start();
    }

    @FXML
    private void onStopButton(ActionEvent event) {
        consoleLog("CMD: " + RocketCommand.STOP.name());
        progressIndicator.setVisible(true);
        flightController.stop();
    }

    @FXML
    private void onCloseButton(ActionEvent event) {
        progressIndicator.setVisible(true);
        flightController.close();
        logfilePrintStream.close();
        logfilePrintStream = null;
        setClosedState();
        progressIndicator.setVisible(false);
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

    public static String getLogdirName() {
        String logdir = System.getProperty("user.home");
        if (!logdir.endsWith("/")) {
            logdir = logdir + "/";
        }
        return logdir + "mission-control/";
    }

    private void setReadyForFlightState() {
        heartbeatButton.setDisable(true);
        initializeButton.setDisable(true);
        connectButton.setDisable(false);
        closeButton.setDisable(false);
        newFlightButton.setDisable(true);
    }

    private void setConnectedState() {
        heartbeatButton.setDisable(false);
        initializeButton.setDisable(false);
        startButton.setDisable(true);
        stopButton.setDisable(true);
        closeButton.setDisable(false);
        connectButton.setDisable(true);
    }

    private void setInitializingState() {
        heartbeatButton.setDisable(true);
        initializeButton.setDisable(true);
        startButton.setDisable(true);
        stopButton.setDisable(true);
        closeButton.setDisable(false);
        connectButton.setDisable(true);
        initializing = true;
    }

    private void setInitializedState() {
        // Calculate altitude baseline here...
        altitudeBaseline = altitudeBaseline / altCounter;
        Platform.runLater(() -> baseAlt.setText(String.format("%.1f", altitudeBaseline)));

        heartbeatButton.setDisable(true);
        initializeButton.setDisable(true);
        startButton.setDisable(false);
        stopButton.setDisable(true);
        closeButton.setDisable(false);
        connectButton.setDisable(true);
        initializing = false;
    }

    private void setStartedState() {
        started = true;
        heartbeatButton.setDisable(false);
        startButton.setDisable(true);
        stopButton.setDisable(false);
        closeButton.setDisable(false);
        connectButton.setDisable(true);
    }

    private void setStoppedState() {
        started = false;
        heartbeatButton.setDisable(false);
        startButton.setDisable(true);
        stopButton.setDisable(true);
        closeButton.setDisable(false);
        connectButton.setDisable(true);
    }

    private void setClosedState() {
        heartbeatButton.setDisable(true);
        initializeButton.setDisable(true);
        startButton.setDisable(true);
        stopButton.setDisable(true);
        closeButton.setDisable(true);
        connectButton.setDisable(true);
        newFlightButton.setDisable(false);
    }

    private void log(String logEntry) {
        logfilePrintStream.println(logEntry);

    }

    private void consoleLog(String logEntry) {
        consoleText.appendText(logEntry + "\n");
    }

    private FlightController createFlightController(String portName) {
        FlightController fc = new FlightController(portName);
        fc.setCommandResponseListener(new CommandResponseListener() {
            @Override
            public void receiveCommandResponse(RocketCommandReply rocketCommandReply) {
                try {
                    String response = new ObjectMapper().writeValueAsString(rocketCommandReply);
                    log(response);
                    consoleLog(response);
                    if (rocketCommandReply.getReplyFor().equals("STRT")) {
                        Platform.runLater( () -> {
                            setStartedState();
                            progressIndicator.setVisible(false);
                        });
                    } else if (rocketCommandReply.getReplyFor().equals("INIT")) {
                        Platform.runLater( () -> {
                            setInitializingState();

                        });
                    } else if (rocketCommandReply.getReplyFor().equals("INTD")) {
                        Platform.runLater( () -> {
                            setInitializedState();
                            progressIndicator.setVisible(false);
                        });
                    } else if (rocketCommandReply.getReplyFor().equals("STOP")) {
                        Platform.runLater( () -> {
                            setStoppedState();
                            progressIndicator.setVisible(false);
                        });
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
        fc.setImuListener(new IMUListener() {
            @Override
            public void receiveIMUData(final IMUData imuData) {
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            accX.setText(String.format("%5.2f", imuData.getAx()));
                            accY.setText(String.format("%5.2f", imuData.getAy()));
                            accZ.setText(String.format("%5.2f", imuData.getAz()));
                            gyroX.setText(Double.toString(imuData.getGx()));
                            gyroY.setText(Double.toString(imuData.getGy()));
                            gyroZ.setText(Double.toString(imuData.getGz()));
                            magnetX.setText(Double.toString(imuData.getMx()));
                            magnetY.setText(Double.toString(imuData.getMy()));
                            magnetZ.setText(Double.toString(imuData.getMz()));

                            // Regner om fra dps til rps
                            imuData.setGx((imuData.getGx() - gxBias) * Math.PI / 180);
                            imuData.setGy((imuData.getGy() - gyBias) * Math.PI / 180);
                            imuData.setGz((imuData.getGz() - gzBias) * Math.PI / 180);

                            // Reverser Mx, siden den er motsatt fra IMU
                            imuData.setMx(imuData.getMx() * -1.0);

                            // Justerer magnetmålinger
                            imuData.setMx(imuData.getMx() + 0.18);
                            imuData.setMy(imuData.getMy() - 0.20);
                            imuData.setMz(imuData.getMz() + 0.05);

                            // Fungerer sånn passe. Ikke helt korrekt etter himmelretning.
                            madgwickAHRS.update(
                                    imuData.getGx().floatValue(),
                                    imuData.getGy().floatValue(),
                                    -imuData.getGz().floatValue(),
                                    -imuData.getAx().floatValue(),
                                    -imuData.getAy().floatValue(),
                                    imuData.getAz().floatValue(),
                                    imuData.getMx().floatValue(),
                                    imuData.getMy().floatValue(),
                                    imuData.getMz().floatValue()
                            );

                            // --- Quaternion directly ----
                            Quaternion quat = madgwickAHRS.getQuaternion();

                            // Angles from quaternion
                            double angles[] = quat.getAngles();
                            azimuthVal.setText(String.format("%.2f", angles[0] * 180 / Math.PI));
                            pitchVal.setText(String.format("%.2f", angles[1] * 180 / (Math.PI)));
                            rollVal.setText(String.format("%.2f", angles[2] * 180 / (Math.PI)));

                            double[] rotationMatrix = quat.get4x4RotationEuclid();
                            rocket3d.getTransforms().setAll(new Affine(rotationMatrix, MatrixType.MT_3D_4x4,0));

                        }
                    });
                    String response = new ObjectMapper().writeValueAsString(imuData);
                    log(response);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                if (initializing) {
                    imuCounter++;
                    if (imuCounter >= imuSamplesToInitialize) {
                        setInitializedState();
                    }
                }
            }
        });
        fc.setAltimeterListener(new AltimeterListener() {
            @Override
            public void receiveAltimeterData( final AltimeterData altimeterData) {
                if (initializing) {
                    altitudeBaseline += altimeterData.getAlt();
                    altCounter++;
                } else if (started) {
                    try {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (timestampStart == null) {
                                    timestampStart = altimeterData.getTs();
                                }
                                XYChart.Data<Long, Double> altitude = new XYChart.Data<Long, Double>(
                                        altimeterData.getTs() - timestampStart,
                                        altimeterData.getAlt() - altitudeBaseline);
                                altitudeData.add(altitude);
                                if (prevAltimeterData != null) {
                                    long timediff = altimeterData.getTs() - prevAltimeterData.getTs();
                                    Double altDiff = altimeterData.getAlt() - prevAltimeterData.getAlt();
                                    Double verticalSpeed = altDiff / ((double) timediff / 1000.0d);
                                    DecimalFormat df = new DecimalFormat("##0.00");
                                    vertSpeed.setText(df.format(verticalSpeed));
                                    if (verticalSpeed > maxSpeed) {
                                        maxSpeed = verticalSpeed;
                                        maxVertSpeed.setText(df.format(maxSpeed));
                                    } else if (verticalSpeed < minSpeed) {
                                        minSpeed = verticalSpeed;
                                        minVertSpeed.setText(df.format(minSpeed));
                                    }
                                }
                                prevAltimeterData = altimeterData;
                                temp.setText(String.format("%.1f", altimeterData.getTemp()));
                            }
                        });

                        String response = new ObjectMapper().writeValueAsString(altimeterData);
                        log(response);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return fc;
    }

    private void initialize3d() {
        camera = new PerspectiveCamera(true);
        camera.fieldOfViewProperty().setValue(60);
        cameraRotateZ = new Rotate(0, Rotate.Z_AXIS);
        cameraRotateY = new Rotate(0, Rotate.Y_AXIS);
        cameraRotateX = new Rotate(90, Rotate.X_AXIS);


        cameraTranslate = new Translate(0, 0, -60);
        camera.getTransforms().addAll(
                cameraRotateX,
                cameraRotateY,
                cameraRotateZ,
                cameraTranslate);

        Group group = buildGroup();
        subScene3d.setFill(Color.rgb(33, 66, 99));
        subScene3d.setCamera(camera);
        subScene3d.setRoot(group);
    }


    private Group buildGroup() {
        Group group = new Group();
        // Create Material
        PhongMaterial phongMaterialRed = new PhongMaterial();
        phongMaterialRed.setDiffuseColor(Color.DARKRED);
        phongMaterialRed.setSpecularColor(Color.RED);
        PhongMaterial phongMaterialBlue = new PhongMaterial();
        phongMaterialBlue.setDiffuseColor(Color.DARKBLUE);
        phongMaterialBlue.setSpecularColor(Color.BLUE);
        PhongMaterial phongMaterialGreen = new PhongMaterial();
        phongMaterialGreen.setDiffuseColor(Color.DARKGREEN);
        phongMaterialGreen.setSpecularColor(Color.GREEN);
        PhongMaterial phongMaterial = new PhongMaterial();
        phongMaterial.setDiffuseColor(Color.CORNSILK);
        phongMaterial.setSpecularColor(Color.BEIGE);

        Box xAxis = new Box(40, 1, 1);
        xAxis.translateXProperty().setValue(0);
        xAxis.translateYProperty().setValue(-20);
        xAxis.translateZProperty().setValue(0);
        xAxis.setMaterial(phongMaterialRed);

        Box yAxis = new Box(1, 40, 1);
        yAxis.translateXProperty().setValue(-20);
        yAxis.translateYProperty().setValue(0);
        yAxis.translateZProperty().setValue(0);
        yAxis.setMaterial(phongMaterialGreen);

        Box zAxis = new Box(1, 1, 40);
        zAxis.translateXProperty().setValue(-20);
        zAxis.translateYProperty().setValue(-20);
        zAxis.translateZProperty().setValue(-20);
        zAxis.setMaterial(phongMaterialBlue);

        group.getChildren().addAll(xAxis, yAxis, zAxis, rocket3d);

        return group;
    }
}
