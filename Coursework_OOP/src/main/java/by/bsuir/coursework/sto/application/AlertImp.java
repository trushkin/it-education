package by.bsuir.coursework.sto.application;

import javafx.scene.control.Alert;

public class AlertImp {
    public static void showAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
        });
    }
}
