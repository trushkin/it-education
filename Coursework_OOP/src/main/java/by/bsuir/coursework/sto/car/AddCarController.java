package by.bsuir.coursework.sto.car;

import by.bsuir.coursework.sto.client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCarController implements Initializable {

    @FXML
    private Button btnCarDelete;

    @FXML
    private Button btnCarInsert;

    @FXML
    private Button btnCarUpdate;

    @FXML
    private ComboBox<Client> cbClientName;

    @FXML
    private TableColumn<CarRow, String> colBrand;

    @FXML
    private TableColumn<CarRow, String> colClientName;

    @FXML
    private TableColumn<CarRow, Integer> colCarID;

    @FXML
    private TableColumn<CarRow, String> colModel;

    @FXML
    private TableColumn<CarRow, String> colStateNum;

    @FXML
    private TableColumn<CarRow, String> colVIN;

    @FXML
    private TextField tfBrand;

    @FXML
    private TextField tfModel;

    @FXML
    private TextField tfStateNum;

    @FXML
    private TextField tfVIN;

    @FXML
    private TableView<CarRow> tvCars;

    @FXML
    private Label lblErrorCar;
    public static Logger logger = LogManager.getLogger();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadClientCB();
        displayCarRows();
    }

    public void displayCarRows() {
        ObservableList<CarRow> carRowObservableList = FXCollections.observableArrayList();
        try {
            carRowObservableList = Car.loadCarDetails();
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            //System.out.println("Ошибка SQL !");
            logger.error("Display cars error");
            return;
        }
        colCarID.setSortable(false);
        colCarID.setResizable(false);
        colCarID.setCellValueFactory(new PropertyValueFactory<CarRow, Integer>("carID"));
        colCarID.setStyle("-fx-alignment: center");

        colStateNum.setSortable(false);
        colStateNum.setResizable(false);
        colStateNum.setCellValueFactory(new PropertyValueFactory<CarRow, String>("stateNum"));
        colStateNum.setStyle("-fx-alignment: center");

        colVIN.setSortable(false);
        colVIN.setResizable(false);
        colVIN.setCellValueFactory(new PropertyValueFactory<CarRow, String>("VIN"));
        colVIN.setStyle("-fx-alignment: center");

        colBrand.setSortable(false);
        colBrand.setResizable(false);
        colBrand.setCellValueFactory(new PropertyValueFactory<CarRow, String>("brand"));
        colBrand.setStyle("-fx-alignment: center");

        colModel.setSortable(false);
        colModel.setResizable(false);
        colModel.setCellValueFactory(new PropertyValueFactory<CarRow, String>("model"));
        colModel.setStyle("-fx-alignment: center");

        colClientName.setSortable(false);
        colClientName.setResizable(false);
        colClientName.setCellValueFactory(new PropertyValueFactory<CarRow, String>("FIO"));
        colClientName.setStyle("-fx-alignment: center");

        tvCars.setItems(carRowObservableList);
    }

    public void loadClientCB() {
        try {
            cbClientName.setItems(Client.loadClientsInForm());
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
           // System.out.println("Ошибка SQL !");
            logger.error("Loading clients error");
            return;
        }
    }


    public void insertCarBtnClick(ActionEvent actionEvent) {
        if (tfStateNum.getText() != "" && tfModel.getText() != "" && tfBrand.getText() != "" && tfVIN.getText() != "" &&
                cbClientName.getValue() != null) {
            try {
                Car.addCar(tfStateNum.getText(), tfVIN.getText(), tfBrand.getText(), tfModel.getText(),
                        cbClientName.getValue().getClientID());
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                //System.out.println("Ошибка SQL !");
                logger.error("Inserting car error");
            }
            tfStateNum.clear();
            tfVIN.clear();
            tfBrand.clear();
            tfModel.clear();
            cbClientName.setValue(null);
            displayCarRows();
            lblErrorCar.setText("");
        } else lblErrorCar.setText("Не все поля заполнены!");
    }

    public void updateCarBtnClick(ActionEvent actionEvent) {
        if ((tvCars.getSelectionModel().getSelectedItem() != null) && (tfStateNum.getText() != "" && tfModel.getText() != "" &&
                tfBrand.getText() != "" && tfVIN.getText() != "" && cbClientName.getValue() != null)) {
            CarRow selectedCar = tvCars.getSelectionModel().getSelectedItem();
            try {
                Car.updateCar(selectedCar.getCarID(), tfStateNum.getText(), tfVIN.getText(), tfBrand.getText(), tfModel.getText(),
                        cbClientName.getValue().getClientID());
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                //System.out.println("Ошибка SQL !");
                logger.error("Updating car error");
            }
            tfStateNum.clear();
            tfVIN.clear();
            tfBrand.clear();
            tfModel.clear();
            cbClientName.setValue(null);
            displayCarRows();
            lblErrorCar.setText("");
        } else lblErrorCar.setText("Не все поля заполнены!");
    }

    public void deleteCarBtnClick(ActionEvent actionEvent) {
        if ((tvCars.getSelectionModel().getSelectedItem() != null) && (tfStateNum.getText() != "" && tfModel.getText() != "" &&
                tfBrand.getText() != "" && tfVIN.getText() != "" && cbClientName.getValue() != null)) {
            CarRow selectedCar = tvCars.getSelectionModel().getSelectedItem();
            try {
                Car.deleteCar(selectedCar.getCarID());
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
               // System.out.println("Ошибка SQL !");
                logger.error("Deleting car error");
            }
            tfStateNum.clear();
            tfVIN.clear();
            tfBrand.clear();
            tfModel.clear();
            cbClientName.setValue(null);
            displayCarRows();
            lblErrorCar.setText("");
        } else lblErrorCar.setText("Не все поля заполнены!");
    }

    public void displaySelectedCarFromTable(MouseEvent mouseEvent) {
        if (tvCars.getSelectionModel().getSelectedItem() != null) {
            CarRow selectedCar = tvCars.getSelectionModel().getSelectedItem();
            tfStateNum.setText(selectedCar.getStateNum());
            tfVIN.setText(selectedCar.getVIN());
            tfBrand.setText(selectedCar.getBrand());
            tfModel.setText(selectedCar.getModel());
            for (Client temp : cbClientName.getItems()) {
                if (temp.getClientID() == selectedCar.getClientID()) {
                    cbClientName.setValue(temp);
                }
            }
        }
    }
}
