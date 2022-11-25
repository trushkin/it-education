package by.bsuir.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddScheduleController {
    @FXML
    private Button btnOpenAddClientForm;

    @FXML
    private Button btnOpenAddCarForm;

    @FXML
    private Button btnScheduleInsert;

    @FXML
    private ComboBox<?> cbClientCar;

    @FXML
    private ComboBox<?> cbClientName;

    @FXML
    private ComboBox<?> cbLift;

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

    public void initialize(){

    }

    public void cbClientClick(ActionEvent actionEvent) {
    }

    public void openClientForm(ActionEvent actionEvent) {
    }

    public void openCarForm(ActionEvent actionEvent) {
    }

    public void insertScheduleButtonClick(ActionEvent actionEvent) {
    }
}
