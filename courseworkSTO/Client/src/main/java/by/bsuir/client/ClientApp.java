package by.bsuir.client;

import by.bsuir.client.service.ClientConnectionModule;
import by.bsuir.client.service.SpringConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


public class ClientApp extends Application {
    private static final int PORT = 1022;
    private static final String IP = "localhost";
    public static final ClientConnectionModule connection;
    public static Logger logger = LogManager.getLogger();

    static {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );
        connection = context.getBean(ClientConnectionModule.class, IP, PORT);
        //new ClientConnectionModule(IP, PORT);
        try {
            connection.connectToServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 320);
        stage.setTitle("АСУ Автосервис");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}