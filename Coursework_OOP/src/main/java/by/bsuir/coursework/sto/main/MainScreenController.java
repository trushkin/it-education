package by.bsuir.coursework.sto.main;

import by.bsuir.coursework.sto.car.Car;
import by.bsuir.coursework.sto.lift.Lift;
import by.bsuir.coursework.sto.schedule.Properties;
import by.bsuir.coursework.sto.schedule.Schedule;
import by.bsuir.coursework.sto.schedule.SchedulePrint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.*;

public class MainScreenController implements Initializable {
    public TableView<ScheduleRow> tvSchedule;
    @FXML
    private DatePicker dtCurrentDate;
    @FXML
    private Button btnAddCar;

    @FXML
    private Button btnAddClient;

    @FXML
    private Button btnAddSchedule;

    @FXML
    private Button btnViewAllSchedules;

    public static Logger logger = LogManager.getLogger();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void refreshTable() {
        ObservableList<ScheduleRow> scheduleRowObservableList = FXCollections.observableArrayList();
        tvSchedule.setItems(scheduleRowObservableList);

        try {
            scheduleRowObservableList.addAll(getScheduleRows());
        } catch (SQLException e) {
            //System.out.println("Ошибка SQL !");
            logger.error("Inserting all schedules in a list error");
            e.printStackTrace();

        }

        TableColumn<ScheduleRow, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<ScheduleRow, String>("time"));

        tvSchedule.getColumns().add(timeColumn);
        timeColumn.prefWidthProperty().bind(tvSchedule.widthProperty().multiply(0.10));
        timeColumn.setSortable(false);
        timeColumn.setResizable(false);
        timeColumn.setStyle("-fx-alignment: center");
        tvSchedule.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //lift loop
        try {
            for (Lift curLift : Lift.loadLifts()) {
                TableColumn<ScheduleRow, String> liftColumn = new TableColumn<>(curLift.getLiftType());
                liftColumn.setCellValueFactory(p -> p.getValue().getSchedulePerLift(curLift.getLiftID()));
                liftColumn.setCellFactory(p -> new ColoredTableCell());
                tvSchedule.getColumns().add(liftColumn);
                liftColumn.prefWidthProperty().bind(tvSchedule.widthProperty().multiply(0.45));
                liftColumn.setResizable(false);
                liftColumn.setSortable(false);
            }
        } catch (SQLException e) {
            //System.out.println("Ошибка SQL !");
            logger.error("Lift columns filling error");
            e.printStackTrace();
        }
    }


    public void onAddClientClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/coursework/sto/AddClientForm.fxml");

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
        tvSchedule.getColumns().clear();
        refreshTable();

    }

    public void onAddCarClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/coursework/sto/AddCarForm.fxml");

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
        tvSchedule.getColumns().clear();
        refreshTable();
    }

    public void onAddScheduleClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/coursework/sto/AddScheduleForm.fxml");

        loader.setLocation(temp);
        Parent root = loader.load();

        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnAddSchedule.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
        tvSchedule.getColumns().clear();
        refreshTable();


    }

    public void onViewAllSchedulesClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/coursework/sto/ViewAllSchedulesForm.fxml");

        loader.setLocation(temp);
        Parent root = loader.load();

        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnViewAllSchedules.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
        tvSchedule.getColumns().clear();
        refreshTable();
    }

    private List<ScheduleRow> getScheduleRows() throws SQLException {
        //load all schedules from DB
        List<Schedule> scheduleList = Schedule.loadSchedule();
        List<Lift> lifts = Lift.loadLifts();
        List<Car> carList = Car.loadAllCars();
        List<ScheduleRow> timeline = new ArrayList();
        for (LocalTime t : Properties.getTimeline()) {
            ScheduleRow curRow = new ScheduleRow(t.getHour() + ":" + (t.getMinute() == 0 ? "00" : "30"));
            for (Lift curLift : lifts) {
                Schedule s = Schedule.findSchedule(LocalDateTime.of(dtCurrentDate.getValue().getYear(),
                        dtCurrentDate.getValue().getMonthValue(), dtCurrentDate.getValue().getDayOfMonth(), t.getHour(),
                        t.getMinute()), 0.5, scheduleList, curLift.getLiftID());
                if (s != null) {
                    Car c = Car.getCarByID(s.getCarID(), carList);
                    SchedulePrint cell = new SchedulePrint(c.getStateNum(), c.getBrand(), c.getModel(), s.getScheduleID());
                    if (timeline.size() > 0) { //выводить информацию о записи только в первой ячейке, а не во всех
                        //загружаем из timeline расписание из последней строки и сравниваем с новым
                        SchedulePrint previousSchedule = timeline.get(timeline.size() - 1).getSchedulePerLift().get(curLift.getLiftID());
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
            timeline.add(curRow);
        }
        return timeline;
    }

    public void OnDtClick(ActionEvent actionEvent) {
        try {
            ObservableList<ScheduleRow> scheduleRowObservableList = FXCollections.observableArrayList();
            tvSchedule.setItems(scheduleRowObservableList);
            scheduleRowObservableList.addAll(getScheduleRows());

        } catch (SQLException e) {
            //System.out.println("Ошибка SQL !");
            logger.error("Updating main screen after choosing date error");
            e.printStackTrace();

        }

    }
}




