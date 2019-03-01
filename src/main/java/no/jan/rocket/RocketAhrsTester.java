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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Affine;
import javafx.scene.transform.MatrixType;
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

public class RocketAhrsTester extends Application {


    private PerspectiveCamera camera;
    private Rotate cameraRotateX, cameraRotateY, cameraRotateZ;
    private Translate cameraTranslate;
    private Label gX, gY, gZ, aX, aY, aZ, mX, mY, mZ, q0, q1, q2, q3, yaw, pitch, roll;
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

//    Box box = new Box(300, 200, 100);
    MeshView box = createMeshView();

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
                if (logfilePrintStream != null) {
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

        Label yprLabel = new Label("Yaw/pitch/roll:");
        yprLabel.translateXProperty().setValue(10);
        yaw = new Label("3.1");
        yaw.translateXProperty().setValue(20);
        pitch = new Label("3.2");
        pitch.translateXProperty().setValue(20);
        roll = new Label("3.3");
        roll.translateXProperty().setValue(20);
        displayBox.getChildren().addAll(yprLabel, yaw, pitch, roll);

        return displayBox;
    }

    private SubScene buildSubScene3d() {
        // Create Camera
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

        group.getChildren().addAll(box, xAxis, yAxis, zAxis);

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
                                gxBias += imuData.getGx();
                                gyBias += imuData.getGy();
                                gyBias += imuData.getGy();
                                if (biasCounter == BIAS_SAMPLE_SIZE) {
                                    gxBias = gxBias / BIAS_SAMPLE_SIZE;
                                    gyBias = gyBias / BIAS_SAMPLE_SIZE;
                                    gzBias = gzBias / BIAS_SAMPLE_SIZE;
                                    System.out.println("Bias counter: " + biasCounter);
                                }
                                return;
                            }

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

                            aX.setText(Double.toString(imuData.getAx()));
                            aY.setText(Double.toString(imuData.getAy()));
                            aZ.setText(Double.toString(imuData.getAz()));
                            gX.setText(Double.toString(imuData.getGx()));
                            gY.setText(Double.toString(imuData.getGy()));
                            gZ.setText(Double.toString(imuData.getGz()));
                            mX.setText(Double.toString(imuData.getMx()));
                            mY.setText(Double.toString(imuData.getMy()));
                            mZ.setText(Double.toString(imuData.getMz()));

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

                            // Fungerer noenlunde som forventet, men med drift...
//                            madgwickAHRS.updateIMU(
//                                    imuData.getGx().floatValue(),
//                                    imuData.getGy().floatValue(),
//                                    -imuData.getGz().floatValue(),
//                                    -imuData.getAx().floatValue(),
//                                    -imuData.getAy().floatValue(),
//                                    imuData.getAz().floatValue()
//                            );

                            double q[] = madgwickAHRS.getQuatArray();
                            q0.setText(Double.toString(q[0]));
                            q1.setText(Double.toString(q[1]));
                            q2.setText(Double.toString(q[2]));
                            q3.setText(Double.toString(q[3]));

                            // --- Quaternion directly ----
                            Quaternion quat = madgwickAHRS.getQuaternion();
//                            double quatArray[] = madgwickAHRS.getQuatArray();
//                            double angleRad = Math.acos(quatArray[0]) * 2;
//                            double angle = angleRad * 360 / (2 * Math.PI) - 180;
//                            double x = -quatArray[1] * 10;
//                            double y = -quatArray[2] * 10;
//                            double z = -quatArray[3] * 10;
//                            System.out.println("Angle: " + angle + "   " + x + "  " + y + "  " + z);
//                            box.getTransforms().setAll(new Rotate(angle, new Point3D(x, y, z)));
//                            north.getTransforms().setAll(new Rotate(angle, -12.5, -5.5, 2.5, new Point3D(x, y, z)));
//                            east.getTransforms().setAll(new Rotate(angle, -9.5, -8.5, 2.5, new Point3D(x, y, z)));
//                            up.getTransforms().setAll(new Rotate(angle, -9.5, -5.5, 5.5, new Point3D(x, y, z)));


                            // Angles from quaternion
                            double angles[] = quat.getAngles();
                            yaw.setText(Double.toString(angles[0] * 180 / (Math.PI)));
                            pitch.setText(Double.toString(angles[1] * 180 / (Math.PI)));
                            roll.setText(Double.toString(angles[2] * 180 / (Math.PI)));
//
//                            cameraRotateX.setAngle(angles[0] * 360 / (2 * Math.PI)); // yaw
//                            cameraRotateY.setAngle(angles[1] * 360 / (2 * Math.PI)); // pitch
//                            cameraRotateZ.setAngle(angles[2] * 360 / (2 * Math.PI)); // roll


                            // Rotation matrix from quaternion
//                            double[] rotationMatrix = quat.get4x4RotationMadg();
                            double[] rotationMatrix = quat.get4x4RotationEuclid();
                            box.getTransforms().setAll(new Affine(rotationMatrix, MatrixType.MT_3D_4x4,0));


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

    public MeshView createMeshView() {
        Image texImage = new Image(getClass().getResourceAsStream("/images/triangle_texmap.png"));
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(texImage);
        material.setSelfIlluminationMap(texImage);
//        material.setSpecularColor(Color.WHITE);

        float points[] =
                {
                        // Nosecone
                         0,  0, -25, // 0
                        -3,  1,  -17,
                        -1,  3,  -17,
                         1,  3,  -17,
                         3,  1,  -17,
                         3, -1,  -17, // 5
                         1, -3,  -17,
                        -1, -3,  -17,
                        -3, -1,  -17,

                        // Øverste seksjon
                        -3,  1,  -9,
                        -1,  3,  -9, // 10
                        1,  3,  -9,
                        3,  1,  -9,
                        3, -1,  -9,
                        1, -3,  -9,
                        -1, -3,  -9, // 15
                        -3, -1,  -9,

                        // Seksjon med noe sort...
                        -3,  1,  0,
                        -1,  3,  0,
                        1,  3,  0,
                        3,  1,  0,   // 20
                        3, -1,  0,
                        1, -3,  0,
                        -1, -3, 0,
                        -3, -1, 0,

                        // Bunnseksjon
                        -3,  1,  25,  // 25
                        -1,  3,  25,
                        1,  3,  25,
                        3,  1,  25,
                        3, -1,  25,
                        1, -3,  25,   // 30
                        -1, -3, 25,
                        -3, -1, 25,

                        // Halefinner
                        // 1
                        -2, 2, 17,
                        -5, 5, 25,
                        -2, 2, 25,    // 35
                        // 2
                        2, 2, 17,
                        5, 5, 25,
                        2, 2, 25,
                        // 3
                        2, -2, 17,
                        5, -5, 25,   // 40
                        2, -2, 25,
                        // 4
                        -2, -2, 17,
                        -5, -5, 25,
                        -2, -2, 25,

                        // Bottom
                         0,  0, 25, // 45
                        -3,  1, 25,
                        -1,  3, 25,
                         1,  3, 25,
                         3,  1, 25,
                         3, -1, 25, // 50
                         1, -3, 25,
                        -1, -3, 25,
                        -3, -1, 25

                };

        float tex[] =
                {
                        // hvit nosecone
                        0.5f, 0.35f,
                        0.48f, 0.45f,
                        0.52f, 0.45f,

                        // Hvit
                        0.1f,  0.1f,  // 3
                        0.1f, 0.3f,
                        0.2f, 0.3f,
                        0.2f, 0.1f,

                        // Sort triangel
                        0.45f,  0.5f,  // 7
                        0.45f, 0.7f,
                        0.55f, 0.7f,
                        0.55f, 0.5f


                };

        int faces[] =
                {
                        // Nosecone
                        0, 0, 1, 1, 2, 2,
                        0, 0, 2, 1, 3, 2,
                        0, 0, 3, 1, 4, 2,
                        0, 0, 4, 1, 5, 2,
                        0, 0, 5, 1, 6, 2,
                        0, 0, 6, 1, 7, 2,
                        0, 0, 7, 1, 8, 2,
                        0, 0, 8, 1, 1, 2,

                        // Section 1
                        1, 3,  9, 4, 10, 5,
                        1, 4, 10, 5,  2, 6,

                        2, 3, 10, 4, 11, 5,
                        2, 4, 11, 5,  3, 6,

                        3, 3, 11, 4, 12, 5,
                        3, 4, 12, 5,  4, 6,

                        4, 3, 12, 4, 13, 5,
                        4, 4, 13, 5,  5, 6,

                        5, 3, 13, 4, 14, 5,
                        5, 4, 14, 5,  6, 6,

                        6, 3, 14, 4, 15, 5,
                        6, 4, 15, 5,  7, 6,

                        7, 3, 15, 4, 16, 5,
                        7, 4, 16, 5,  8, 6,

                        8, 3, 16, 4, 9, 5,
                        8, 4,  9, 5, 1, 6,

                        // Section 2 - Med litt sort foran...
                        9, 7, 17, 8, 18, 9,
                        9, 7, 18, 9, 10, 10,

                        10, 7, 18, 8, 19, 9,
                        10, 7, 19, 9, 11, 10,

                        11, 7, 19, 8, 20, 9,
                        11, 7, 20, 9, 12, 10,

                        12, 3, 20, 4, 21, 5,
                        12, 3, 21, 4, 13, 5,

                        13, 3, 21, 4, 22, 5,
                        13, 3, 22, 4, 14, 5,

                        14, 3, 22, 4, 23, 5,
                        14, 3, 23, 4, 15, 5,

                        15, 3, 23, 4, 24, 5,
                        15, 3, 24, 4, 16, 5,

                        16, 3, 24, 4, 17, 5,
                        16, 3, 17, 4,  9, 5,

                        // Section 3 - Bunnseksjon...
                        17, 3, 25, 4, 26, 5,
                        17, 3, 26, 4, 18, 5,

                        18, 3, 26, 4, 27, 5,
                        18, 3, 27, 4, 19, 5,

                        19, 3, 27, 4, 28, 5,
                        19, 3, 28, 4, 20, 5,

                        20, 3, 28, 4, 29, 5,
                        20, 3, 29, 4, 21, 5,

                        21, 3, 29, 4, 30, 5,
                        21, 3, 30, 4, 22, 5,

                        22, 3, 30, 4, 31, 5,
                        22, 3, 31, 4, 23, 5,

                        23, 3, 31, 4, 32, 5,
                        23, 3, 32, 4, 24, 5,

                        24, 3, 32, 4, 25, 5,
                        24, 3, 25, 4, 17, 5,

                        // Tailfins
                        33, 10, 34, 8, 35, 9,
                        33,  6, 35, 4, 34, 5,

                        36, 7, 38, 9, 37, 8,
                        36, 6, 37, 4, 38, 5,

                        39, 10, 41, 8, 40, 9,
                        39,  6, 40, 4, 41, 5,

                        42, 10, 43, 8, 44, 9,
                        42,  6, 44, 4, 43, 5,

                        // Nosecone
                        45, 3, 47, 5, 46, 4,
                        45, 3, 48, 5, 47, 4,
                        45, 3, 49, 5, 48, 4,
                        45, 3, 50, 5, 49, 4,
                        45, 3, 51, 5, 50, 4,
                        45, 3, 52, 5, 51, 4,
                        45, 3, 53, 5, 52, 4,
                        45, 3, 46, 5, 53, 4
                };

        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(tex);
        mesh.getFaces().addAll(faces);

        MeshView box = new MeshView(mesh);
        //box.setDrawMode(DrawMode.FILL);
        box.setMaterial(material);
        return box;
    }

    public static void main(String[] args) {
        launch();
    }
}
