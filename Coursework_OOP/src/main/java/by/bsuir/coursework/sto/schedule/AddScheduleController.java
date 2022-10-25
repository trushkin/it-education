package by.bsuir.coursework.sto.schedule;

import by.bsuir.coursework.sto.car.Car;
import by.bsuir.coursework.sto.client.Client;
import by.bsuir.coursework.sto.lift.Lift;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddScheduleController implements Initializable {

    @FXML
    private Button btnOpenAddClientForm;

    @FXML
    private Button btnOpenAddCarForm;

    @FXML
    private Button btnScheduleInsert;

    @FXML
    private ComboBox<Car> cbClientCar;

    @FXML
    private ComboBox<Client> cbClientName;

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

    public static Logger logger = LogManager.getLogger();
    @FXML
    void openCarForm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/coursework/sto/AddCarForm.fxml");

        loader.setLocation(temp);
        Parent root = loader.load();
        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnOpenAddCarForm.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
    }

    @FXML
    void openClientForm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/coursework/sto/AddClientForm.fxml");

        loader.setLocation(temp);
        Parent root = loader.load();

        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnOpenAddClientForm.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
        loadClientCB();
    }

    public void loadClientCB() {
        try {
            cbClientName.setItems(FXCollections.observableArrayList(Client.loadClientsInForm()));
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            //System.out.println("Ошибка SQL !");
            logger.error("Loading clients in comboBox error");
            return;
        }
    }

    public void loadClientCarsCB() {
        try {
            if ((cbClientName.getValue()) != null)
                cbClientCar.setItems(FXCollections.observableArrayList(Car.loadCarsInCb(cbClientName.getValue().getClientID())));
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            //System.out.println("Ошибка SQL !");
            logger.error("Loading client's cars in comboBox error");
            return;
        }
    }

    public void loadLiftsCB() {
        try {
            cbLift.setItems(FXCollections.observableArrayList(Lift.loadLifts()));
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            logger.error("Loading lifts in comboBox error");
            return;
        }
    }


    public void cbClientClick(ActionEvent actionEvent) {
        loadClientCarsCB();
    }

    public void insertScheduleButtonClick(ActionEvent actionEvent) throws SQLException, IOException {
        if ((cbClientName.getValue() != null && cbClientCar.getValue() != null && cbLift.getValue() != null && dtStartDate.getValue() != null && //проверка на заполнение всех полей
                spStartHour.getValue() != null && spStartMin.getValue() != null && spDuration.getValue() != null && taComment.getText() != "" && tfMileage.getText() != "") &&
                        Schedule.checkTimeInterval(LocalDateTime.of(dtStartDate.getValue().getYear(), dtStartDate.getValue().getMonthValue(), // проверка пересечение
                                dtStartDate.getValue().getDayOfMonth(), spStartHour.getValue(), spStartMin.getValue()), spDuration.getValue(),
                                cbLift.getValue().getLiftID()))
        {
            try {
                Schedule.addSchedule(cbClientName.getValue().getClientID(), cbClientCar.getValue().getCarID(), cbLift.getValue().getLiftID(),
                        LocalDateTime.of(dtStartDate.getValue().getYear(), dtStartDate.getValue().getMonthValue(), dtStartDate.getValue().getDayOfMonth(),
                                spStartHour.getValue(), spStartMin.getValue()), spDuration.getValue(), taComment.getText(), Integer.parseInt(tfMileage.getText()));
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                //System.out.println("Ошибка SQL !");
                logger.error("Creating schedule error");
            }
            Stage stage = (Stage) btnScheduleInsert.getScene().getWindow();
            stage.close();
        } else {
            FXMLLoader loader = new FXMLLoader();
            URL temp = getClass().getResource("/by/bsuir/coursework/sto/ErrorScheduleAddingForm.fxml");
            loader.setLocation(temp);
            Parent root = loader.load();
            //создание нового окна
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            //блокировка главного окна
            stage.initOwner(this.btnScheduleInsert.getScene().getWindow());
            //ожидание закрытия окна
            stage.showAndWait();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadClientCB();
        loadLiftsCB();
        spStartHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Properties.getStartWorkTime(), Properties.getEndWorkTime() - 1));
        spStartMin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0, 30));
        spDuration.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24));

        // заполнение textField только цифрами
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


}
