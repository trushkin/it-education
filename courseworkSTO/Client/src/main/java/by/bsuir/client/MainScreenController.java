package by.bsuir.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainScreenController {

    @FXML
    private Button btnAddCar;

    @FXML
    private Button btnAddClient;

    @FXML
    private Button btnAddSchedule;

    @FXML
    private Button btnViewAllSchedules;

    @FXML
    private DatePicker dtCurrentDate;

    @FXML
    private TableView<?> tvSchedule;

    public void initialize(){

    }

    public void onCarBtnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/CarForm.fxml");
        loader.setLocation(temp);
        Parent root = loader.load();
        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnAddCar.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
//        tvSchedule.getColumns().clear();
//        refreshTable();
    }

    public void onAddScheduleBtnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/AddScheduleForm.fxml");

        loader.setLocation(temp);
        Parent root = loader.load();

        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnAddClient.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
//        tvSchedule.getColumns().clear();
//        refreshTable();
    }

    public void onClientBtnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/ClientForm.fxml");
        loader.setLocation(temp);
        Parent root = loader.load();
        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnAddClient.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
//        tvSchedule.getColumns().clear();
//        refreshTable();
    }

    public void onViewAllSchedulesBtnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/ViewAllSchedulesForm.fxml");

        loader.setLocation(temp);
        Parent root = loader.load();

        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnAddClient.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
//        tvSchedule.getColumns().clear();
//        refreshTable();
    }

    public void OnDtClick(ActionEvent actionEvent) {
    }
}
