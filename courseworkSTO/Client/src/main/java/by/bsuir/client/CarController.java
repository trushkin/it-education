package by.bsuir.client;

import by.pojo.Car;
import by.pojo.CarRow;
import by.pojo.Client;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class CarController {
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
    private TableColumn<CarRow, Integer> colCarID;

    @FXML
    private TableColumn<CarRow, String> colClientName;

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
    public static final int PORT = 1022;
    public static final String IP = "localhost";
    ClientConnectionModule connection = new ClientConnectionModule(IP, PORT);

    {
        try {
            connection.connectToServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
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
        refreshTable();
        loadClientCB();
    }

    public void refreshTable() {
        ArrayList<CarRow> carRowArrayList = connection.getAllCars();
        tvCars.setItems(FXCollections.observableList(carRowArrayList));
    }

    public void loadClientCB() {
        cbClientName.setItems(FXCollections.observableArrayList(connection.getAllClients()));
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

    private void showAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
        });
    }

    public void insertCarBtnClick(ActionEvent actionEvent) {
        if (tfStateNum.getText() != "" && tfModel.getText() != "" && tfBrand.getText() != "" && tfVIN.getText() != "" &&
                cbClientName.getValue() != null) {
            System.out.println(cbClientName.getValue().getClientID());
            Car newCar = new Car(tfStateNum.getText(), tfVIN.getText(), tfBrand.getText(), tfModel.getText(), cbClientName.getValue().getClientID());
            connection.createCar(newCar);
            tfStateNum.clear();
            tfVIN.clear();
            tfBrand.clear();
            tfModel.clear();
            cbClientName.setValue(null);
            refreshTable();
        } else showAlert("Invalid input", "Fill all fields at first");
    }

    public void updateCarBtnClick(ActionEvent actionEvent) {
        if ((tvCars.getSelectionModel().getSelectedItem() != null) && (tfStateNum.getText() != "" && tfModel.getText() != "" &&
                tfBrand.getText() != "" && tfVIN.getText() != "" && cbClientName.getValue() != null)) {
            Car car = new Car(tfStateNum.getText(), tfVIN.getText(), tfBrand.getText(), tfModel.getText(), tvCars.getSelectionModel().getSelectedItem().getCarID(), cbClientName.getValue().getClientID());
            connection.updateCar(car);
            tfStateNum.clear();
            tfVIN.clear();
            tfBrand.clear();
            tfModel.clear();
            cbClientName.setValue(null);
            refreshTable();
        } else showAlert("Invalid input", "Fill all fields at first");
    }

    public void deleteCarBtnClick(ActionEvent actionEvent) {
        if ((tvCars.getSelectionModel().getSelectedItem() != null) && (tfStateNum.getText() != "" && tfModel.getText() != "" &&
                tfBrand.getText() != "" && tfVIN.getText() != "" && cbClientName.getValue() != null)) {
            CarRow selectedCar = tvCars.getSelectionModel().getSelectedItem();
            connection.deleteCar(selectedCar.getCarID());
            tfStateNum.clear();
            tfVIN.clear();
            tfBrand.clear();
            tfModel.clear();
            cbClientName.setValue(null);
            refreshTable();
        } else showAlert("Invalid input", "Fill all fields at first");
    }
}
