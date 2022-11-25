package by.bsuir.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;

public class ViewAllSchedulesController {
    @FXML
    private TableColumn<?, String> colBrand;

    @FXML
    private TableColumn<?, Integer> colDuration;

    @FXML
    private TableColumn<?, String> colFIO;

    @FXML
    private TableColumn<?, Integer> colMileage;

    @FXML
    private TableColumn<?, String> colModel;

    @FXML
    private TableColumn<?, LocalDateTime> colStartDate;

    @FXML
    private TableColumn<?, String> colStateNum;

    @FXML
    private TableColumn<?, String> colLift;

    @FXML
    private TableView<?> tvSchedules;

    @FXML
    private ComboBox<?> cbCarFilter;

    @FXML
    private ComboBox<?> cbClientsFilter;

    @FXML
    private DatePicker dtDateFilter;

    @FXML
    private TextArea taComment;

    public void initialize(){

    }

    public void onCbClientsFilterClick(ActionEvent actionEvent) {
    }

    public void onCarFilterClick(ActionEvent actionEvent) {
    }

    public void onDateFilterClick(ActionEvent actionEvent) {
    }

    public void deleteSelectedScheduleClick(ActionEvent actionEvent) {
    }

    public void onClearFiltersClick(ActionEvent actionEvent) {
    }

    public void displaySelectedScheduleComment(MouseEvent mouseEvent) {
    }
}
