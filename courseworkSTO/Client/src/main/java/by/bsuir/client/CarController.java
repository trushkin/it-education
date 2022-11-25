package by.bsuir.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class CarController {
    @FXML
    private Button btnCarDelete;

    @FXML
    private Button btnCarInsert;

    @FXML
    private Button btnCarUpdate;

    @FXML
    private ComboBox<?> cbClientName;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colCarID;

    @FXML
    private TableColumn<?, ?> colClientName;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colStateNum;

    @FXML
    private TableColumn<?, ?> colVIN;

    @FXML
    private TextField tfBrand;

    @FXML
    private TextField tfModel;

    @FXML
    private TextField tfStateNum;

    @FXML
    private TextField tfVIN;

    @FXML
    private TableView<?> tvCars;

    public void initialize(){

    }

    public void displaySelectedCarFromTable(MouseEvent mouseEvent) {

    }

    public void insertCarBtnClick(ActionEvent actionEvent) {
    }

    public void updateCarBtnClick(ActionEvent actionEvent) {
    }

    public void deleteCarBtnClick(ActionEvent actionEvent) {

    }
}
