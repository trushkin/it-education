package by.bsuir.client;

import by.bsuir.client.service.ClientConnectionModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;



public class ClientApp extends Application {
    private static final int PORT = 1022;
    private static final String IP = "localhost";
    public static final ClientConnectionModule connection;
    public static Logger logger = LogManager.getLogger();
    static
    {
        connection = new ClientConnectionModule(IP, PORT);
        try {
            connection.connectToServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 320);
        stage.setTitle("АСУ Автосервис");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}