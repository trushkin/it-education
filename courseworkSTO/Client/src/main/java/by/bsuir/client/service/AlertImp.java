package by.bsuir.client.service;

import javafx.scene.control.Alert;

public class AlertImp {
    public static void showAlert(String header, String message,  Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
        });
    }
}
