package by.bsuir.client.controllers;

import by.bsuir.client.service.BuildReport;
import by.bsuir.client.service.ScheduleCheck;
import by.bsuir.client.service.ScheduleRow;
import by.pojo.*;
import com.itextpdf.text.DocumentException;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static by.bsuir.client.ClientApp.connection;
import static by.bsuir.client.controllers.LoginController.role;
public class MainScreenController {
    @FXML
    private MenuButton adminMenu;
    @FXML
    private Button btnAddCar;

    @FXML
    private Button btnAddClient;

    @FXML
    private Button btnAddSchedule;

    @FXML
    private Button btnViewAllSchedules;
    @FXML
    private Button btnLogout;

    @FXML
    private DatePicker dtCurrentDate;

    @FXML
    private TableView<ScheduleRow> tvSchedule;
    public static Logger logger = LogManager.getLogger();
    private static ArrayList<Lift> liftArrayList = connection.getAllLifts();

    public void initialize() {
        role = "test";
        if(role.equals("Receptionist"))
        {
            adminMenu.setVisible(false);
        }
        dtCurrentDate.setValue(LocalDate.now());
        refreshTable();
        tvSchedule.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tvSchedule.getSelectionModel().setCellSelectionEnabled(true);
    }

    public void onMenuPartClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/PartForm.fxml");
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

    public void onMenuOperationClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/OperationForm.fxml");
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

    public void onMenuWorkloadClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/WorkLoadForm.fxml");
        loader.setLocation(temp);
        Parent root = loader.load();
        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Загруженность подъемников за неделю");
        //блокировка главного окна
        stage.initOwner(this.btnAddCar.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
        tvSchedule.getColumns().clear();
        refreshTable();
    }

    public void onBtnLogoutClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/LoginForm.fxml");
        loader.setLocation(temp);
        Parent root = loader.load();
        Stage stage = (Stage) btnAddCar.getScene().getWindow();
        stage.close();
        //создание нового окна
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        newStage.initOwner(this.btnAddCar.getScene().getWindow());
        //ожидание закрытия окна
        newStage.setTitle("АСУ Автосервис");
        newStage.show();
    }

    public void onMenuReportClicked(ActionEvent actionEvent) {
        try {
            BuildReport.createReport(connection.getSchedulesToPrint(), dtCurrentDate.getValue());
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onMenuRevenueloadClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/RevenueForm.fxml");
        loader.setLocation(temp);
        Parent root = loader.load();
        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Выручка по подъемникам за неделю");
        //блокировка главного окна
        stage.initOwner(this.btnAddCar.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
        tvSchedule.getColumns().clear();
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
        tvSchedule.getColumns().clear();
        refreshTable();
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
        tvSchedule.getColumns().clear();
        refreshTable();
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
        tvSchedule.getColumns().clear();
        refreshTable();
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
        tvSchedule.getColumns().clear();
        refreshTable();
    }

    public void refreshTable() {
        ObservableList<ScheduleRow> scheduleRowObservableList = FXCollections.observableArrayList();
        tvSchedule.setItems(scheduleRowObservableList);
        scheduleRowObservableList.addAll(getScheduleRows());
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
            TableColumn<ScheduleRow, String> liftColumn = new TableColumn<>(curLift.getLiftName());
            liftColumn.setCellValueFactory(p -> p.getValue().getSchedulePerLift(curLift.getLiftID()));
            liftColumn.setCellFactory(p -> new ColoredTableCell());
            tvSchedule.getColumns().add(liftColumn);
            liftColumn.prefWidthProperty().bind(tvSchedule.widthProperty().multiply(0.30));
            liftColumn.setResizable(false);
            liftColumn.setSortable(false);
        }


    }

    private List<ScheduleRow> getScheduleRows() {
        //load all schedules from DB
        List<Schedule> scheduleList = connection.getAllSchedules();
        List<Car> carList = connection.getAllCars();
        List<ScheduleRow> scheduleRowList = new ArrayList();
        ObservableList<LocalTime> timeline = getTimeline();
        for (LocalTime t : timeline) {
            ScheduleRow curRow = new ScheduleRow(t.getHour() + ":" + (t.getMinute() == 0 ? "00" : "30"));
            for (Lift curLift : liftArrayList) {
                Schedule s = ScheduleCheck.findSchedule(LocalDateTime.of(dtCurrentDate.getValue().getYear(),
                        dtCurrentDate.getValue().getMonthValue(), dtCurrentDate.getValue().getDayOfMonth(), t.getHour(),
                        t.getMinute()), 0.5, scheduleList, curLift.getLiftID());
                if (s != null) {
                    Car c = getCarByID(s.getCarID(), carList);
                    SchedulePrint cell = new SchedulePrint(c.getStateNum(), c.getBrand(), c.getModel(), s.getScheduleID());
                    if (!scheduleRowList.isEmpty()) { //выводить информацию о записи только в первой ячейке, а не во всех
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

    public static ObservableList<LocalTime> getTimeline() {
        Properties properties = connection.getWorkingHours();
        ObservableList<LocalTime> timeline = FXCollections.observableArrayList();
        for (int i = properties.getStartWorkTime().getHour(); i < properties.getEndWorkTime().getHour(); i++) {
            timeline.add(LocalTime.of(i, 0));
            timeline.add(LocalTime.of(i, 30));
        }
        return timeline;
    }

    public Car getCarByID(int carID, List<Car> carList) {
        for (Car temp : carList) {
            if (temp.getCarID() == carID)
                return temp;
        }
        return null;
    }

    public void OnDtClick(ActionEvent actionEvent) {
        ObservableList<ScheduleRow> scheduleRowObservableList = FXCollections.observableArrayList();
        tvSchedule.setItems(scheduleRowObservableList);
        scheduleRowObservableList.addAll(getScheduleRows());
    }
}
