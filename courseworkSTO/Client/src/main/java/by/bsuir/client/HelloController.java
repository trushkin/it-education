package by.bsuir.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    public static final int PORT = 1022;
    public static final String IP = "localhost";
    ClientConnectionModule connection = new ClientConnectionModule(IP, PORT);
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        try {
            connection.connectToServer();
            welcomeText.setText(connection.test());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}