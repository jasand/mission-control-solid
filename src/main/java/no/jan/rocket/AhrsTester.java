package no.jan.rocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import no.jan.rocket.ahrs.MadgwickAHRS;
import no.jan.rocket.ahrs.Quaternion;
import no.jan.rocket.comm.IMUData;
import no.jan.rocket.flight.FlightController;
import no.jan.rocket.flight.IMUListener;
import no.jan.rocket.modal.CommPortSelectModal;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AhrsTester extends Application {


    private PerspectiveCamera camera;
    private Rotate cameraRotateX, cameraRotateY, cameraRotateZ;
    private Translate cameraTranslate;
    private Label gX, gY, gZ, aX, aY, aZ, mX, mY, mZ, q0, q1, q2, q3;
    private CheckBox checkBox = new CheckBox("Save to file");
    Label fileNameLabel = new Label("");
    PrintStream logfilePrintStream;

    int biasCounter = 0;
    final int BIAS_SAMPLE_SIZE = 50;
    double gxBias = 0.0;
    double gyBias = 0.0;
    double gzBias = 0.0;

    private double mouseOldX, mouseNewX;
    private double mouseOldY, mouseNewY;

    private FlightController flightController;
    private MadgwickAHRS madgwickAHRS = new MadgwickAHRS(20, 0.2);

    @Override
    public void start(Stage stage) throws Exception {


        HBox hBox = new HBox(buildDisplayBox(), buildSubScene3d());

        // Create Scene and Handlers
        Scene scene = new Scene(hBox, 800, 600, true);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                onMousePressed(mouseEvent);
            }

        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                onMouseDragged(mouseEvent);
            }

        });

        // Set the Stage
        stage.setTitle("Test Cube 3D");
        stage.setScene(scene);
        scene.setFill(Color.GRAY);
        stage.show();
    }

    private VBox buildDisplayBox() {
        VBox displayBox = new VBox(5);
        displayBox.prefWidthProperty().setValue(200);
        displayBox.setBackground(new Background(new BackgroundFill(Color.SLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        checkBox.translateXProperty().setValue(10);
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    String fileName = getDefaultNewFileName();
                    fileNameLabel.setText(fileName);
                    fileNameLabel.setDisable(false);
                } else {
                    fileNameLabel.setText("");
                }
            }
        });

        Button connectButton = new Button("Connect serial");
        connectButton.translateXProperty().setValue(10);
        connectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CommPortSelectModal commPortSelectModal = new CommPortSelectModal();
                String portName = commPortSelectModal.getSelectedCommPort();
                flightController = createFlightController(portName);
                connectButton.setDisable(true);
                checkBox.setDisable(true);
                if (checkBox.isSelected()) {
                    try {
                        File logfile = new File(getLogdirName() + fileNameLabel.getText());
                        logfile.createNewFile();
                        logfilePrintStream = new PrintStream(logfile);
                        logfileHeadings();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        fileNameLabel.translateXProperty().setValue(10);

        Button closeButton = new Button("Close");
        closeButton.translateXProperty().setValue(10);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (flightController != null)
                    flightController.close();
                if ( logfilePrintStream != null) {
                    logfilePrintStream.close();
                    logfilePrintStream = null;
                }
                System.exit(0);
            }
        });
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(connectButton, closeButton);

        displayBox.getChildren().addAll(new Label(""), hBox, new Label(""),
                checkBox, fileNameLabel, new Label(""));

        Label gyroLabel = new Label("Gyroscope:");
        gyroLabel.translateXProperty().setValue(10);
        gX = new Label("1.1");
        gX.translateXProperty().setValue(20);
        gY = new Label("1.2");
        gY.translateXProperty().setValue(20);
        gZ = new Label("1.3");
        gZ.translateXProperty().setValue(20);
        displayBox.getChildren().addAll(gyroLabel, gX, gY, gZ);

        Label accLabel = new Label("Accelerometer:");
        accLabel.translateXProperty().setValue(10);
        aX = new Label("2.1");
        aX.translateXProperty().setValue(20);
        aY = new Label("2.2");
        aY.translateXProperty().setValue(20);
        aZ = new Label("2.3");
        aZ.translateXProperty().setValue(20);
        displayBox.getChildren().addAll(accLabel, aX, aY, aZ);

        Label magLabel = new Label("Magnetometer:");
        magLabel.translateXProperty().setValue(10);
        mX = new Label("3.1");
        mX.translateXProperty().setValue(20);
        mY = new Label("3.2");
        mY.translateXProperty().setValue(20);
        mZ = new Label("3.3");
        mZ.translateXProperty().setValue(20);
        displayBox.getChildren().addAll(magLabel, mX, mY, mZ);

        Label quatLabel = new Label("Quaternion:");
        quatLabel.translateXProperty().setValue(10);
        q0 = new Label("0.1");
        q0.translateXProperty().setValue(20);
        q1 = new Label("0.2");
        q1.translateXProperty().setValue(20);
        q2 = new Label("0.3");
        q2.translateXProperty().setValue(20);
        q3 = new Label("0.4");
        q3.translateXProperty().setValue(20);
        displayBox.getChildren().addAll(quatLabel, q0, q1, q2, q3);

        return displayBox;
    }

    private SubScene buildSubScene3d() {
        // Create Camera
        camera = new PerspectiveCamera(true);
        cameraRotateX = new Rotate(-20, Rotate.X_AXIS);
        cameraRotateY = new Rotate(0, Rotate.Y_AXIS);
        cameraRotateZ = new Rotate(0, Rotate.Z_AXIS);
        cameraTranslate = new Translate(0, 0, -40);
        camera.getTransforms().addAll(
                cameraRotateX,
                cameraRotateY,
                cameraRotateZ,
                cameraTranslate);

//        cameraRotateX.setAngle(25); // roll
//        cameraRotateY.setAngle(25); // pitch
//        cameraRotateZ.setAngle(25); // yaw

        Group group = buildGroup();
        SubScene scene3d = new SubScene(group, 600, 600, true, SceneAntialiasing.BALANCED);
        scene3d.setFill(Color.rgb(33, 66, 99));
        scene3d.setCamera(camera);
        return scene3d;
    }

    private Group buildGroup() {
        Group group = new Group();
        // Create Material
        PhongMaterial phongMaterial = new PhongMaterial();
        phongMaterial.setDiffuseColor(Color.DARKRED);
        phongMaterial.setSpecularColor(Color.RED);

        // Create Box
        Box box = new Box(8, 5, 2);
        box.setMaterial(phongMaterial);
        group.getChildren().add(box);

        return group;
    }

    private FlightController createFlightController(String portName) {
        FlightController fc = new FlightController(portName);
        fc.setImuListener(new IMUListener() {
            @Override
            public void receiveIMUData(final IMUData imuData) {
                if (checkBox.isSelected()) {
                    logIMUData(imuData);
                }
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            if (!checkBox.isSelected() && biasCounter < BIAS_SAMPLE_SIZE) {
                                biasCounter++;
                                gxBias = imuData.getGx();
                                gyBias = imuData.getGy();
                                gyBias = imuData.getGy();
                                if (biasCounter == BIAS_SAMPLE_SIZE) {
                                    gxBias = gxBias / BIAS_SAMPLE_SIZE;
                                    gyBias = gyBias / BIAS_SAMPLE_SIZE;
                                    gzBias = gzBias / BIAS_SAMPLE_SIZE;
                                    System.out.println("Bias counter: " + biasCounter);
                                }
                                return;
                            }

                            // Regner om fra dps til rps
                            imuData.setGx((imuData.getGx() * Math.PI / 180) - gxBias);
                            imuData.setGy((imuData.getGy() * Math.PI / 180) - gyBias);
                            imuData.setGz((imuData.getGz() * Math.PI / 180) - gzBias);

                            // Justerer magnetmålinger
                            imuData.setMx(imuData.getMx() - 0.0927);
                            imuData.setMy(imuData.getMy() - 0.25067);
                            imuData.setMz(imuData.getMz() + 0.23625);

                            aX.setText(Double.toString(imuData.getAx()));
                            aY.setText(Double.toString(imuData.getAy()));
                            aZ.setText(Double.toString(imuData.getAz()));
                            gX.setText(Double.toString(imuData.getGx()));
                            gY.setText(Double.toString(imuData.getGy()));
                            gZ.setText(Double.toString(imuData.getGz()));
                            mX.setText(Double.toString(imuData.getMx()));
                            mY.setText(Double.toString(imuData.getMy()));
                            mZ.setText(Double.toString(imuData.getMz()));


                            madgwickAHRS.update(imuData.getGx().floatValue(),
                                    imuData.getGy().floatValue(),
                                    imuData.getGz().floatValue(),
                                    imuData.getAx().floatValue(),
                                    imuData.getAy().floatValue(),
                                    imuData.getAz().floatValue(),
                                    imuData.getMx().floatValue(),
                                    imuData.getMy().floatValue(),
                                    imuData.getMz().floatValue());

//                            madgwickAHRS.updateIMU(imuData.getGx().floatValue(),
//                                    imuData.getGy().floatValue(),
//                                    imuData.getGz().floatValue(),
//                                    imuData.getAx().floatValue(),
//                                    imuData.getAy().floatValue(),
//                                    imuData.getAz().floatValue());

                            double q[] = madgwickAHRS.getQuatArray();
                            q0.setText(Double.toString(q[0]));
                            q1.setText(Double.toString(q[1]));
                            q2.setText(Double.toString(q[2]));
                            q3.setText(Double.toString(q[3]));

                            Quaternion quat = madgwickAHRS.getQuaternion();
                            double angles[] = quat.getAngles();

                            cameraRotateX.setAngle(angles[0] * 360 / (2 * Math.PI)); // yaw
                            cameraRotateY.setAngle(angles[1] * 360 / (2 * Math.PI)); // pitch
                            cameraRotateZ.setAngle(angles[2] * 360 / (2 * Math.PI)); // roll


//                            cameraRotateZ.setAngle(calculateAzimuth(imuData)); // yaw
//                            cameraRotateY.setAngle(calculatePitch(imuData)); // pitch
//                            cameraRotateX.setAngle(calculateRoll(imuData)); // roll

                        }
                    });
                    String response = new ObjectMapper().writeValueAsString(imuData);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });

        return fc;
    }

    public static String getLogdirName() {
        String logdir = System.getProperty("user.home");
        if (!logdir.endsWith("/")) {
            logdir = logdir + "/";
        }
        return logdir + "mission-control/ahrs/";
    }

    private static String getDefaultNewFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String filename = "ahrs-" + now.format(formatter) + ".csv";
        return filename;
    }

    private void logfileHeadings() {
        logfilePrintStream.println("TS, GX, GY, GZ, AX, AY, AZ, MX, MY, MZ");
    }

    private void logIMUData(IMUData imuData) {
        logfilePrintStream.println(
                imuData.getTs() + "," +
                        imuData.getGx() + "," +
                        imuData.getGy() + "," +
                        imuData.getGz() + "," +
                        imuData.getAx() + "," +
                        imuData.getAy() + "," +
                        imuData.getAz() + "," +
                        imuData.getMx() + "," +
                        imuData.getMy() + "," +
                        imuData.getMz()
        );
    }

    public void onMousePressed(MouseEvent mouseEvent) {
        mouseOldX = mouseNewX = mouseEvent.getSceneX();
        mouseOldY = mouseNewY = mouseEvent.getSceneY();
    }

    public void onMouseDragged(MouseEvent mouseEvent) {
        mouseOldX = mouseNewX;
        mouseOldY = mouseNewY;
        mouseNewX = mouseEvent.getSceneX();
        mouseNewY = mouseEvent.getSceneY();

        double mouseDeltaX = (mouseNewX - mouseOldX);
        double mouseDeltaY = (mouseNewY - mouseOldY);

        cameraRotateX.setAngle(cameraRotateX.getAngle() - mouseDeltaY);
        cameraRotateY.setAngle(cameraRotateY.getAngle() + mouseDeltaX);
    }

    public static void main(String[] args) {
        launch();
    }
}
