package by.bsuir.client.controllers;

import by.pojo.Car;
import by.pojo.Client;
import by.pojo.Lift;
import by.pojo.Properties;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.SearchableComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static by.bsuir.client.ClientApp.connection;

import static by.bsuir.client.service.AlertImp.showAlert;

public class AddScheduleController {
    @FXML
    private Button btnOpenAddClientForm;

    @FXML
    private Button btnOpenAddCarForm;

    @FXML
    private Button btnScheduleInsert;

    @FXML
    private SearchableComboBox<Car> cbClientCar;

    @FXML
    private SearchableComboBox<Client> cbClientName;

    @FXML
    private ComboBox<Lift> cbLift;

    @FXML
    private Spinner<Integer> spDuration;

    @FXML
    private Spinner<Integer> spStartHour;

    @FXML
    private Spinner<Integer> spStartMin;

    @FXML
    private DatePicker dtStartDate;

    @FXML
    private TextArea taComment;

    @FXML
    private TextField tfMileage;
    private static Logger logger = LogManager.getLogger();

    public void initialize() {
        Properties properties = connection.getWorkingHours();
            loadClientCB();
            spStartHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(properties.getStartWorkTime().getHour(), properties.getEndWorkTime().getHour() - 1));
            spStartMin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0, 30));
            spDuration.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24));
            tfMileage.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        tfMileage.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
        }


    public void loadClientCB() {
        cbClientName.setItems(FXCollections.observableArrayList(connection.getAllClients()));
    }

    public void loadClientCarCB() {
        if ((cbClientName.getValue()) != null) {
            cbClientCar.setItems(FXCollections.observableArrayList(connection.loadClientCarsInCB(cbClientName.getValue().getClientID())));
        }
    }

    public void cbClientClick(ActionEvent actionEvent) {
        loadClientCarCB();
    }

    public void openClientForm(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/ClientForm.fxml");
        loader.setLocation(temp);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnOpenAddCarForm.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
        // loadClientCB();
    }

    public void openCarForm(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/CarForm.fxml");
        loader.setLocation(temp);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnOpenAddCarForm.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
    }

    public void insertScheduleButtonClick(ActionEvent actionEvent) {
//        if ((cbClientName.getValue() != null && cbClientCar.getValue() != null && cbLift.getValue() != null && dtStartDate.getValue() != null && //проверка на заполнение всех полей
//                spStartHour.getValue() != null && spStartMin.getValue() != null && spDuration.getValue() != null && taComment.getText() != "" && tfMileage.getText() != "") &&
//                Schedule.checkTimeInterval(LocalDateTime.of(dtStartDate.getValue().getYear(), dtStartDate.getValue().getMonthValue(), // проверка пересечение
//                                dtStartDate.getValue().getDayOfMonth(), spStartHour.getValue(), spStartMin.getValue()), spDuration.getValue(),
//                        cbLift.getValue().getLiftID()))
//        {
//            try {
//                Schedule.addSchedule(cbClientName.getValue().getClientID(), cbClientCar.getValue().getCarID(), cbLift.getValue().getLiftID(),
//                        LocalDateTime.of(dtStartDate.getValue().getYear(), dtStartDate.getValue().getMonthValue(), dtStartDate.getValue().getDayOfMonth(),
//                                spStartHour.getValue(), spStartMin.getValue()), spDuration.getValue(), taComment.getText(), Integer.parseInt(tfMileage.getText()));
//            } catch (SQLException e) {
//                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
//                //System.out.println("Ошибка SQL !");
//                logger.error("Creating schedule error");
//            }
//            Stage stage = (Stage) btnScheduleInsert.getScene().getWindow();
//            stage.close();
//        } else {
//           showAlert("Ошибка", "Желаемое время уже занято или не все поля заполнены!", Alert.AlertType.ERROR);
//        }

    }
}
