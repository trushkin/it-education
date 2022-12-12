package by.bsuir.client.controllers;

import by.pojo.Car;
import by.pojo.Client;
import by.pojo.SchedulePrint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.SearchableComboBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


import static by.bsuir.client.service.AlertImp.showAlert;
import static by.bsuir.client.ClientApp.connection;

public class ViewAllSchedulesController {
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
    private SearchableComboBox<Car> cbCarFilter;

    @FXML
    private SearchableComboBox<Client> cbClientsFilter;

    @FXML
    private DatePicker dtDateFilter;

    @FXML
    private TextArea taComment;

    ObservableList<SchedulePrint> schedulesObservableList = FXCollections.observableArrayList();

    public void initialize() {
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
        refreshTable();
        loadClientCB();
        loadCarsCB();
    }

    public void refreshTable() {
        schedulesObservableList = FXCollections.observableArrayList(connection.getSchedulesToPrint());
        tvSchedules.setItems(FXCollections.observableList(schedulesObservableList));
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

    public void deleteSelectedScheduleClick(ActionEvent actionEvent) {
        if (tvSchedules.getSelectionModel().getSelectedItem() != null) {
            SchedulePrint selectedSchedule = tvSchedules.getSelectionModel().getSelectedItem();
            boolean flag = connection.deleteSchedule(selectedSchedule.getScheduleID());
            if(flag == true){
                showAlert("", "Запись на тех. обслуживание удалена успешно", Alert.AlertType.INFORMATION);
                cbClientsFilter.setValue(null);
                cbCarFilter.setValue(null);
                dtDateFilter.setValue(null);
                tvSchedules.setItems(schedulesObservableList);
                taComment.setText("");
            }
            refreshTable();
        }
        else {
            showAlert("Ошибка!", "Выберите запись для удаления", Alert.AlertType.WARNING);
        }
    }

    public void onClearFiltersClick(ActionEvent actionEvent) {
        cbClientsFilter.setValue(null);
        cbCarFilter.setValue(null);
        dtDateFilter.setValue(null);
        tvSchedules.setItems(schedulesObservableList);
        taComment.setText("");

    }

    public void displaySelectedScheduleComment(MouseEvent mouseEvent) {
        if (tvSchedules.getSelectionModel().getSelectedItem() != null)
            taComment.setText(tvSchedules.getSelectionModel().getSelectedItem().getComment());
    }

    public void loadClientCB() {
        cbClientsFilter.setItems(FXCollections.observableArrayList(connection.getAllClients()));
    }

    public void loadCarsCB() {
        if ((cbClientsFilter.getValue()) != null)
            cbCarFilter.setItems(FXCollections.observableArrayList(connection.loadClientCarsInCB(cbClientsFilter.getValue().getClientID())));
        else
            cbCarFilter.setItems(FXCollections.observableArrayList(connection.loadClientCarsInCB(-1)));

    }

    public void onCbClick() {
        if (cbClientsFilter.getValue() != null && cbCarFilter.getValue() == null && dtDateFilter.getValue() == null) // 1 0 0
            tvSchedules.setItems(clientFilter(schedulesObservableList, cbClientsFilter.getValue().getFIO(), null, null));

        else if (cbClientsFilter.getValue() != null && cbCarFilter.getValue() != null && dtDateFilter.getValue() == null) // 1 1 0
            tvSchedules.setItems(clientFilter(schedulesObservableList, cbClientsFilter.getValue().getFIO(), cbCarFilter.getValue().getStateNum(), null));

        else if (cbClientsFilter.getValue() != null && cbCarFilter.getValue() != null && dtDateFilter.getValue() != null) //1 1 1
            tvSchedules.setItems(clientFilter(schedulesObservableList, cbClientsFilter.getValue().getFIO(), cbCarFilter.getValue().getStateNum(), dtDateFilter.getValue()));

        else if (cbClientsFilter.getValue() == null && cbCarFilter.getValue() != null && dtDateFilter.getValue() != null) // 0 1 1
            tvSchedules.setItems(clientFilter(schedulesObservableList, null, cbCarFilter.getValue().getStateNum(), dtDateFilter.getValue()));

        else if (cbClientsFilter.getValue() == null && cbCarFilter.getValue() == null && dtDateFilter.getValue() != null) // 0 0 1
            tvSchedules.setItems(clientFilter(schedulesObservableList, null, null, dtDateFilter.getValue()));

        else if (cbClientsFilter.getValue() == null && cbCarFilter.getValue() != null && dtDateFilter.getValue() == null) // 0 1 0
            tvSchedules.setItems(clientFilter(schedulesObservableList, null, cbCarFilter.getValue().getStateNum(), null));

        else if (cbClientsFilter.getValue() != null && cbCarFilter.getValue() == null && dtDateFilter.getValue() != null) // 1 0 1
            tvSchedules.setItems(clientFilter(schedulesObservableList, cbClientsFilter.getValue().getFIO(), null, dtDateFilter.getValue()));
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
//public ObservableList<SchedulePrint> filterByClient(){
//    ObservableList<SchedulePrint> filteredByClient= FXCollections.observableArrayList();
//        if(cbClientsFilter.getValue() != null){
//            for (SchedulePrint l : schedulesObservableList) {
//                if(cbClientsFilter.getValue().getFIO().equals(l.getFIO())){
//                    filteredByClient.add(l);
//                return сортировка по машине(filteredByClient)
//                    }
//            }
//        }else return сортировка по машине(изначальный список записей)
//}
}
