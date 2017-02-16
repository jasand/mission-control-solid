package no.jan.rocket.modal;

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.jan.rocket.flight.FlightController;

import java.util.List;

/**
 * Created by jasand on 15.02.2017.
 */
public class CommPortSelectModal {

    private Stage window;
    private Label label = new Label("Select comm port:");
    private Label label2;
    private ComboBox<String> portsCombo;
    private Button okButton = new Button("OK");
    private Button cancelButton = new Button("Cancel");
    private VBox layout;
    private ProgressIndicator progressIndicator;

    public String getSelectedCommPort() {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Comm port");
        progressIndicator = new ProgressIndicator(-1.0);
        progressIndicator.setVisible(true);
        label2 = new Label("Fetching system comm ports...");
        layout = new VBox(10);
        layout.setPrefWidth(Double.MAX_VALUE);
        layout.getChildren().addAll(progressIndicator, label2);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 350, 150);
        Task<ObservableList<String>> fetchPortsTask = new Task<ObservableList<String>>() {
            {
                setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        portsCombo = new ComboBox<String>(getValue());
                        okButton.setOnAction(e -> window.close());
                        cancelButton.setOnAction(e -> {
                            portsCombo.setValue(null);
                            window.close();
                        });
                        layout.getChildren().clear();
                        layout.getChildren().addAll(label, portsCombo, okButton);
                        progressIndicator.setVisible(false);
                    }
                });
                setOnFailed(wse -> {
                    label2.setText("Exception getting ports:");
                    label.setText(wse.getSource().getException().getMessage());
                    okButton.setOnAction(e -> window.close());
                    layout.getChildren().clear();
                    layout.getChildren().addAll(label2, label, okButton);
                    progressIndicator.setVisible(false);
                });
            }
            @Override
            protected ObservableList<String> call() throws Exception {
                List<String> ports = FlightController.getAvailableCommPortNames();
                return new ImmutableObservableList<>(ports.toArray(new String[ports.size()]));
            }
        };
        Thread bgThread = new Thread(fetchPortsTask);
        bgThread.setDaemon(true);
        bgThread.start();
        window.setScene(scene);
        window.showAndWait();
        return portsCombo.getValue();
    }


}
