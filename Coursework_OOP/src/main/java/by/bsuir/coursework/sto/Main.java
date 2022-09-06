package by.bsuir.coursework.sto;

import by.bsuir.coursework.sto.application.DatabaseConnectionProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ScheduleForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("STO");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws SQLException {

        launch();
        DatabaseConnectionProvider.getConnection().close();
    }
}