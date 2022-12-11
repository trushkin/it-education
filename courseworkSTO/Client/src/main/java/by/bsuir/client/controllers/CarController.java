package by.bsuir.client.controllers;

import by.pojo.Car;
import by.pojo.CarRow;
import by.pojo.Client;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.SearchableComboBox;

import java.util.ArrayList;

import static by.bsuir.client.service.AlertImp.showAlert;
import static by.bsuir.client.ClientApp.connection;
import static by.bsuir.client.ClientApp.logger;

public class CarController {
    @FXML
    private Button btnCarDelete;

    @FXML
    private Button btnCarInsert;

    @FXML
    private Button btnCarUpdate;

    @FXML
    private SearchableComboBox<Client> cbClientName;

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


    public void insertCarBtnClick(ActionEvent actionEvent) {
        if (!tfStateNum.getText().equals("") && !tfModel.getText().equals("") && !tfBrand.getText().equals("") && !tfVIN.getText().equals("") &&
                cbClientName.getValue() != null) {
            Car newCar = new Car(tfStateNum.getText(), tfVIN.getText(), tfBrand.getText(), tfModel.getText(), cbClientName.getValue().getClientID());
            boolean flag = connection.createCar(newCar);
            logger.debug(flag);
            if(flag == true) {
               showAlert("", "Автомобиль добавлен успешно",Alert.AlertType.INFORMATION);
            }
            tfStateNum.clear();
            tfVIN.clear();
            tfBrand.clear();
            tfModel.clear();
            cbClientName.setValue(null);
            refreshTable();
        } else showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);

    }

    public void updateCarBtnClick(ActionEvent actionEvent) {
        if ((tvCars.getSelectionModel().getSelectedItem() != null) && (!tfStateNum.getText().equals("") && !tfModel.getText().equals("") && !tfBrand.getText().equals("") && !tfVIN.getText().equals("") &&
                cbClientName.getValue() != null)) {
            Car car = new Car(tfStateNum.getText(), tfVIN.getText(), tfBrand.getText(), tfModel.getText(), tvCars.getSelectionModel().getSelectedItem().getCarID(), cbClientName.getValue().getClientID());
            boolean flag = connection.updateCar(car);
            logger.debug(flag);
            if(flag == true) {
               showAlert("", "Запись о выбранном автомобиле отредактирована успешно",Alert.AlertType.INFORMATION);
            }
            tfStateNum.clear();
            tfVIN.clear();
            tfBrand.clear();
            tfModel.clear();
            cbClientName.setValue(null);
            refreshTable();
        } else showAlert("Ошибка ввода", "Пожалуйста, зааолните все поля", Alert.AlertType.WARNING);
    }

    public void deleteCarBtnClick(ActionEvent actionEvent) {
        if ((tvCars.getSelectionModel().getSelectedItem() != null) && (!tfStateNum.getText().equals("") && !tfModel.getText().equals("") && !tfBrand.getText().equals("") && !tfVIN.getText().equals("") &&
                cbClientName.getValue() != null)) {
            CarRow selectedCar = tvCars.getSelectionModel().getSelectedItem();
            boolean flag = connection.deleteCar(selectedCar.getCarID());
            if(flag == true) {
                showAlert("", "Запись об автомобиле удалена успешно", Alert.AlertType.INFORMATION);
            }
            tfStateNum.clear();
            tfVIN.clear();
            tfBrand.clear();
            tfModel.clear();
            cbClientName.setValue(null);
            refreshTable();
        } else showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
    }
}
