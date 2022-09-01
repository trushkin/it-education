package by.bsuir.coursework.sto.schedule;

import by.bsuir.coursework.sto.car.Car;
import by.bsuir.coursework.sto.client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewAllSchedulesController implements Initializable {
    @FXML
    private TableColumn<SchedulePrint, String> colBrand;

    @FXML
    private TableColumn<SchedulePrint, Integer> colDuration;

    @FXML
    private TableColumn<SchedulePrint, String> colFIO;

    @FXML
    private TableColumn<SchedulePrint, Integer> colMileage;

    @FXML
    private TableColumn<SchedulePrint, String> colModel;

    @FXML
    private TableColumn<SchedulePrint, LocalDateTime> colStartDate;

    @FXML
    private TableColumn<SchedulePrint, String> colStateNum;

    @FXML
    private TableColumn<SchedulePrint, String> colLift;

    @FXML
    private TableView<SchedulePrint> tvSchedules;

    @FXML
    private ComboBox<Car> cbCarFilter;

    @FXML
    private ComboBox<Client> cbClientsFilter;

    @FXML
    private DatePicker dtDateFilter;

    @FXML
    private TextArea taComment;


    ObservableList<SchedulePrint> schedulesObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            schedulesObservableList = Schedule.loadScheduleToPrint();
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
            return;
        }
        displaySchedules(schedulesObservableList);
        loadClientCB();

    }

    public void displaySchedules(ObservableList<SchedulePrint> schedulesObservableList) {

        colBrand.setSortable(false);
        colBrand.setResizable(false);
        colBrand.setCellValueFactory(new PropertyValueFactory<SchedulePrint, String>("brand"));
        colBrand.setStyle("-fx-alignment: center");

        colStateNum.setSortable(false);
        colStateNum.setResizable(false);
        colStateNum.setCellValueFactory(new PropertyValueFactory<SchedulePrint, String>("stateNum"));
        colStateNum.setStyle("-fx-alignment: center");

        colDuration.setSortable(false);
        colDuration.setResizable(false);
        colDuration.setCellValueFactory(new PropertyValueFactory<SchedulePrint, Integer>("duration"));
        colDuration.setStyle("-fx-alignment: center");

        colModel.setSortable(false);
        colModel.setResizable(false);
        colModel.setCellValueFactory(new PropertyValueFactory<SchedulePrint, String>("model"));
        colModel.setStyle("-fx-alignment: center");

        colFIO.setSortable(false);
        colFIO.setResizable(false);
        colFIO.setCellValueFactory(new PropertyValueFactory<SchedulePrint, String>("FIO"));
        colFIO.setStyle("-fx-alignment: center");


        colStateNum.setSortable(false);
        colStateNum.setResizable(false);
        colStateNum.setCellValueFactory(new PropertyValueFactory<SchedulePrint, String>("stateNum"));
        colStateNum.setStyle("-fx-alignment: center");

        colMileage.setSortable(false);
        colMileage.setResizable(false);
        colMileage.setCellValueFactory(new PropertyValueFactory<SchedulePrint, Integer>("mileage"));
        colMileage.setStyle("-fx-alignment: center");

        colStartDate.setSortable(false);
        colStartDate.setSortType(TableColumn.SortType.DESCENDING);
        colStartDate.setResizable(false);
        colStartDate.setCellValueFactory(new PropertyValueFactory<SchedulePrint, LocalDateTime>("startDate"));
        colStartDate.setStyle("-fx-alignment: center");

        colLift.setSortable(true);
        colLift.setResizable(false);
        colLift.setCellValueFactory(new PropertyValueFactory<SchedulePrint, String>("liftName"));
        colLift.setStyle("-fx-alignment: center");

        tvSchedules.setItems(schedulesObservableList);
    }

    public ObservableList<SchedulePrint> clientFilter(ObservableList<SchedulePrint> schedulesObservableList, String FIO, String stateNum, LocalDate date) {
        ObservableList<SchedulePrint> filteredSchedulesObservableList = FXCollections.observableArrayList();

        for (SchedulePrint l : schedulesObservableList) {
            if (Objects.equals(l.getFIO(), FIO) && stateNum == null && date == null) // 1 0 0
                filteredSchedulesObservableList.add(l);

            else if (Objects.equals(l.getFIO(), FIO) && Objects.equals(l.getStateNum(), stateNum) && date == null) //1 1 0
                filteredSchedulesObservableList.add(l);

            else if (Objects.equals(l.getFIO(), FIO) && Objects.equals(l.getStateNum(), stateNum) && (l.getStartDate().getYear() == date.getYear() && l.getStartDate().getMonth() == date.getMonth() && l.getStartDate().getDayOfMonth() == date.getDayOfMonth())) // 1 1 1
                filteredSchedulesObservableList.add(l);

            else if (FIO == null && stateNum == null && (l.getStartDate().getYear() == date.getYear() && l.getStartDate().getMonth() == date.getMonth() && l.getStartDate().getDayOfMonth() == date.getDayOfMonth())) // 0 0 1
                filteredSchedulesObservableList.add(l);

            else if (FIO == null && Objects.equals(l.getStateNum(), stateNum) && date == null) // 0 1 0
                filteredSchedulesObservableList.add(l);

            else if (Objects.equals(l.getFIO(), FIO) && stateNum == null && (l.getStartDate().getYear() == date.getYear() && l.getStartDate().getMonth() == date.getMonth() && l.getStartDate().getDayOfMonth() == date.getDayOfMonth())) // 1 0 1
                filteredSchedulesObservableList.add(l);

            else if (FIO == null && Objects.equals(l.getStateNum(), stateNum) && (l.getStartDate().getYear() == date.getYear() && l.getStartDate().getMonth() == date.getMonth() && l.getStartDate().getDayOfMonth() == date.getDayOfMonth())) // 0 1 1
                filteredSchedulesObservableList.add(l);
        }


        return filteredSchedulesObservableList;
    }

    public void onCbClick() {
        if (cbClientsFilter.getValue() != null && cbCarFilter.getValue() == null && dtDateFilter.getValue() == null) // 1 0 0
            displaySchedules(clientFilter(schedulesObservableList, cbClientsFilter.getValue().getFIO(), null, null));

        else if (cbClientsFilter.getValue() != null && cbCarFilter.getValue() != null && dtDateFilter.getValue() == null) // 1 1 0
            displaySchedules(clientFilter(schedulesObservableList, cbClientsFilter.getValue().getFIO(), cbCarFilter.getValue().getStateNum(), null));

        else if (cbClientsFilter.getValue() != null && cbCarFilter.getValue() != null && dtDateFilter.getValue() != null) //1 1 1
            displaySchedules(clientFilter(schedulesObservableList, cbClientsFilter.getValue().getFIO(), cbCarFilter.getValue().getStateNum(), dtDateFilter.getValue()));

        else if (cbClientsFilter.getValue() == null && cbCarFilter.getValue() != null && dtDateFilter.getValue() != null) // 0 1 1
            displaySchedules(clientFilter(schedulesObservableList, null, cbCarFilter.getValue().getStateNum(), dtDateFilter.getValue()));

        else if (cbClientsFilter.getValue() == null && cbCarFilter.getValue() == null && dtDateFilter.getValue() != null) // 0 0 1
            displaySchedules(clientFilter(schedulesObservableList, null, null, dtDateFilter.getValue()));

        else if (cbClientsFilter.getValue() == null && cbCarFilter.getValue() != null && dtDateFilter.getValue() == null) // 0 1 0
            displaySchedules(clientFilter(schedulesObservableList, null, cbCarFilter.getValue().getStateNum(), null));

        else if (cbClientsFilter.getValue() != null && cbCarFilter.getValue() == null && dtDateFilter.getValue() != null) // 1 0 1
            displaySchedules(clientFilter(schedulesObservableList, cbClientsFilter.getValue().getFIO(), null, dtDateFilter.getValue()));
    }

    public void onCbClientsFilterClick(ActionEvent actionEvent) {
        onCbClick();
        loadCarsCB();
    }

    public void onCarFilterClick(ActionEvent actionEvent) {
        onCbClick();
    }

    public void onDateFilterClick(ActionEvent actionEvent) {
        onCbClick();
    }

    public void loadClientCB() {
        try {
            cbClientsFilter.setItems(Client.loadClientsInForm());
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
            return;
        }
    }

    public void loadCarsCB() {
        try {
            if ((cbClientsFilter.getValue()) != null)
                cbCarFilter.setItems(Car.loadCarsInCb(cbClientsFilter.getValue().getClientID()));
            else
                cbCarFilter.setItems(Car.loadCarsInCb(-1));
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
            return;
        }
    }

    public void onClearFiltersClick(ActionEvent actionEvent) {
        cbClientsFilter.setValue(null);
        cbCarFilter.setValue(null);
        dtDateFilter.setValue(null);
        displaySchedules(schedulesObservableList);
        taComment.setText("");

    }

    public void displaySelectedScheduleComment(MouseEvent mouseEvent) {
        if (tvSchedules.getSelectionModel().getSelectedItem() != null)
            taComment.setText(tvSchedules.getSelectionModel().getSelectedItem().getComment());
    }

    public void deleteSelectedScheduleClick(ActionEvent actionEvent) throws SQLException {
        if (tvSchedules.getSelectionModel().getSelectedItem() != null) {
            Schedule.deleteSchedule(tvSchedules.getSelectionModel().getSelectedItem().getScheduleID());
            try {
                schedulesObservableList = Schedule.loadScheduleToPrint();
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                System.out.println("Ошибка SQL !");
                return;
            }
            cbClientsFilter.setValue(null);
            cbCarFilter.setValue(null);
            dtDateFilter.setValue(null);
            displaySchedules(schedulesObservableList);
            taComment.setText("");
        }
    }
}
