package by.bsuir.client.controllers;

import by.bsuir.client.service.ScheduleRow;
import by.pojo.Car;
import by.pojo.Lift;
import by.pojo.Schedule;
import by.pojo.SchedulePrint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static by.bsuir.client.service.AlertImp.showAlert;
import static by.bsuir.client.ClientApp.connection;

public class MainScreenController {

    @FXML
    private Button btnAddCar;

    @FXML
    private Button btnAddClient;

    @FXML
    private Button btnAddSchedule;

    @FXML
    private Button btnViewAllSchedules;

    @FXML
    private DatePicker dtCurrentDate;

    @FXML
    private TableView<ScheduleRow> tvSchedule;
    public static Logger logger = LogManager.getLogger();
    private static ArrayList<Lift> liftArrayList;

    public void initialize() {
        dtCurrentDate.setValue(LocalDate.now());
        refreshTable();
    }

    class ColoredTableCell extends TableCell<ScheduleRow, String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || getTableRow() == null) {
                setText(null);
                setGraphic(null);
            } else {
                //ScheduleRow row = (ScheduleRow) getTableRow().getItem();
                setText(item);
                if (item != null && item.equals("<свободно>")) {
                    setStyle("-fx-background-color: green; -fx-alignment: center; -fx-border-width: 1px;" +
                            " -fx-border-color: #AAAAAA");
                } else {
                    setStyle("-fx-background-color: lightgray; -fx-alignment: center");
                }

            }
        }
    }

    public void onCarBtnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/CarForm.fxml");
        loader.setLocation(temp);
        Parent root = loader.load();
        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnAddCar.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
//        tvSchedule.getColumns().clear();
//        refreshTable();
    }

    public void onAddScheduleBtnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/AddScheduleForm.fxml");

        loader.setLocation(temp);
        Parent root = loader.load();

        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnAddClient.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
//        tvSchedule.getColumns().clear();
//        refreshTable();
    }

    public void onClientBtnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/ClientForm.fxml");
        loader.setLocation(temp);
        Parent root = loader.load();
        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnAddClient.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
//        tvSchedule.getColumns().clear();
//        refreshTable();
    }

    public void onViewAllSchedulesBtnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/ViewAllSchedulesForm.fxml");

        loader.setLocation(temp);
        Parent root = loader.load();

        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnAddClient.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
//        tvSchedule.getColumns().clear();
//        refreshTable();
    }

    public void refreshTable() {
        ObservableList<ScheduleRow> scheduleRowObservableList = FXCollections.observableArrayList();
        tvSchedule.setItems(scheduleRowObservableList);

//        try {
//            scheduleRowObservableList.addAll(getScheduleRows());
//        } catch (SQLException e) {
//            //System.out.println("Ошибка SQL !");
//            logger.error("Inserting all schedules in a list error");
//            e.printStackTrace();
//
//        }

        TableColumn<ScheduleRow, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<ScheduleRow, String>("time"));

        tvSchedule.getColumns().add(timeColumn);
        timeColumn.prefWidthProperty().bind(tvSchedule.widthProperty().multiply(0.10));
        timeColumn.setSortable(false);
        timeColumn.setResizable(false);
        timeColumn.setStyle("-fx-alignment: center");
        tvSchedule.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        liftArrayList = connection.getAllLifts();
        //lift loop
        for (Lift curLift : liftArrayList) {
            TableColumn<ScheduleRow, String> liftColumn = new TableColumn<>(curLift.getLiftType());
            liftColumn.setCellValueFactory(p -> p.getValue().getSchedulePerLift(curLift.getLiftID()));
            liftColumn.setCellFactory(p -> new ColoredTableCell());
            tvSchedule.getColumns().add(liftColumn);
            liftColumn.prefWidthProperty().bind(tvSchedule.widthProperty().multiply(0.45));
            liftColumn.setResizable(false);
            liftColumn.setSortable(false);
        }

    }

    private List<ScheduleRow> getScheduleRows() throws SQLException {
        //load all schedules from DB
        List<Schedule> scheduleList = Schedule.loadSchedule();
        List<Car> carList = Car.loadAllCars();
        List<ScheduleRow> scheduleRowList = new ArrayList();
        ObservableList<LocalTime> timeline = getTimeline();
        for (LocalTime t : timeline) {
            ScheduleRow curRow = new ScheduleRow(t.getHour() + ":" + (t.getMinute() == 0 ? "00" : "30"));
            for (Lift curLift : liftArrayList) {
                Schedule s = Schedule.findSchedule(LocalDateTime.of(dtCurrentDate.getValue().getYear(),
                        dtCurrentDate.getValue().getMonthValue(), dtCurrentDate.getValue().getDayOfMonth(), t.getHour(),
                        t.getMinute()), 0.5, scheduleList, curLift.getLiftID());
                if (s != null) {
                    Car c = Car.getCarByID(s.getCarID(), carList);
                    SchedulePrint cell = new SchedulePrint(c.getStateNum(), c.getBrand(), c.getModel(), s.getScheduleID());
                    if (scheduleRowList.size() > 0) { //выводить информацию о записи только в первой ячейке, а не во всех
                        //загружаем из timeline расписание из последней строки и сравниваем с новым
                        SchedulePrint previousSchedule = scheduleRowList.get(scheduleRowList.size() - 1).getSchedulePerLift().get(curLift.getLiftID());
                        if (previousSchedule == null || s.getScheduleID() != previousSchedule.getScheduleID()) {
                            curRow.getSchedulePerLift().put(curLift.getLiftID(), cell);
                        } else {
                            curRow.getSchedulePerLift().put(curLift.getLiftID(), new SchedulePrint("", "", "", s.getScheduleID()));
                        }
                    } else {
                        curRow.getSchedulePerLift().put(curLift.getLiftID(), cell);
                    }
                }
            }
            scheduleRowList.add(curRow);
        }
        return scheduleRowList;
    }

    public static ObservableList<LocalTime> getTimeline()
    {
        by.pojo.Properties properties = connection.getWorkingHours();
        ObservableList<LocalTime> timeline = FXCollections.observableArrayList();
        for (int i = properties.getStartWorkTime().getHour(); i < properties.getEndWorkTime().getHour(); i++)
        {
            timeline.add(LocalTime.of(i, 0));
            timeline.add(LocalTime.of(i, 30));
        }
        return timeline;
    }

    public void OnDtClick(ActionEvent actionEvent) {
    }
}
