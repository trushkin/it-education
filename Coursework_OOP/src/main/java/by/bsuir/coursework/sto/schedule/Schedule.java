package by.bsuir.coursework.sto.schedule;

import by.bsuir.coursework.sto.application.DatabaseConnectionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private int scheduleID;
    private int clientID;
    private int carID;
    private int liftID;
    private LocalDateTime startDate;
    private int duration;
    private String comment;
    private int mileage;

    public static Logger logger = LogManager.getLogger();

    public Schedule(int scheduleID, int clientID, int carID, int liftID, LocalDateTime startDate, int duration, String comment, int mileage) {
        this.scheduleID = scheduleID;
        this.clientID = clientID;
        this.carID = carID;
        this.liftID = liftID;
        this.startDate = startDate;
        this.duration = duration;
        this.comment = comment;
        this.mileage = mileage;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getLiftID() {
        return liftID;
    }

    public void setLiftID(int liftID) {
        this.liftID = liftID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public static List<Schedule> loadSchedule() throws SQLException {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        String selectSql = "SELECT ScheduleID, ClientID, CarID, LiftID, StartDate, Duration, Comment, Mileage from Schedule";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            scheduleList.add(new Schedule(resultSet.getInt("ScheduleID"), resultSet.getInt("ClientID"), resultSet.getInt("CarID"), resultSet.getInt("LiftID"), resultSet.getTimestamp("StartDate").toLocalDateTime(), resultSet.getInt("Duration"), resultSet.getString("Comment"), resultSet.getInt("Mileage")));

        }
        return scheduleList;
    }

    public static ArrayList<SchedulePrint> loadScheduleToPrint() throws SQLException {
        ArrayList<SchedulePrint> scheduleList = new ArrayList<SchedulePrint>();
        String selectSql = "select Lifts.LiftType, Cars.StateNum, Cars.Brand, Cars.Model, Clients.Name, Clients.Surname, Clients.Patronymic, Schedule.Mileage, Schedule.StartDate, Schedule.Duration, Schedule.ScheduleID, Schedule.Comment from Schedule\n" +
                "\tinner join Cars on Schedule.CarID = Cars.CarID\n" +
                "\tinner join Clients on Schedule.ClientID = Clients.ClientID\n" +
                "\tinner join Lifts on Schedule.LiftID = Lifts.LiftID \n";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            scheduleList.add(new SchedulePrint(resultSet.getString("StateNum"), resultSet.getString("Brand"), resultSet.getString("Model"), resultSet.getString("Name") + " " + resultSet.getString("Surname") + " " + resultSet.getString("Patronymic"), resultSet.getInt("Mileage"), resultSet.getTimestamp("StartDate").toLocalDateTime(), resultSet.getInt("Duration"), resultSet.getInt("ScheduleID"), resultSet.getString("LiftType"), resultSet.getString("Comment")));
        }
        return scheduleList;
    }

    public static void deleteSchedule(int scheduleID) throws SQLException {
        String selectSql = "DELETE FROM Schedule WHERE ScheduleID =  '" + scheduleID + "'";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        int code = statement.executeUpdate();
        if (code >= 1) {
            logger.info("Schedule deleted successfully");
            return;
        }
        throw null;
    }

    public static void addSchedule(int clientID, int carID, int liftID, LocalDateTime startDate, int duration, String comment, int mileage) throws SQLException {
        String selectSql = "INSERT INTO Schedule(ClientID, CarID, LiftID, StartDate, Duration, Comment, Mileage) VALUES ('" + clientID + "','" + carID + "','" + liftID + "','" + Timestamp.valueOf(startDate) + "','" + duration + "','" + comment + "','" + mileage + "')";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        int code = statement.executeUpdate();
        if (code == 1) {
//            System.out.println("Запрос успешно выполнен!");
            logger.info("Schedule added successfully");
            return;
        }
        throw null;

    }

    public static LocalDateTime calculateFinalWorkingTime(LocalDateTime startDate, double duration) {
        //LocalDateTime finalWorkingTime = startDate;
        LocalDateTime endWorkTime = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), Properties.getEndWorkTime(), 0);

        if (!startDate.plusMinutes((int) (duration * 60)).isAfter(endWorkTime)) {
            return startDate.plusMinutes((int) (duration * 60));
        }
        if ((int) duration == 0) {
            return LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), Properties.getStartWorkTime(), (int) (duration * 60));
        }
        duration -= (Properties.getEndWorkTime() - startDate.getHour()); // остаток от длительности работы после вычета свободных часов в первый день записи
        startDate = startDate.plusDays(1);

        while (Properties.getStartWorkTime() + duration > Properties.getEndWorkTime()) {
            duration -= (Properties.getEndWorkTime() - Properties.getStartWorkTime());// отнимаем целый рабочий день в часах
            startDate = startDate.plusDays(1);

        }
        return LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), Properties.getStartWorkTime() + (int) duration, startDate.getMinute());
    }

    public static Schedule findSchedule(LocalDateTime startDate, double duration, List<Schedule> scheduleList, int liftID) throws SQLException {
        LocalDateTime finalWorkingTime = calculateFinalWorkingTime(startDate, duration);
        for (Schedule l : scheduleList) {
            LocalDateTime finalWorkingTime2 = calculateFinalWorkingTime(l.getStartDate(), l.getDuration());
            if (!(finalWorkingTime.minusNanos(1).isBefore(l.getStartDate()) || startDate.plusNanos(1).isAfter(finalWorkingTime2)) && l.getLiftID() == liftID) //проверка на пересечение временного интервала
            {
                return l;
            }
        }
        return null;
    }

    public static boolean checkTimeInterval(LocalDateTime startDate, double duration, int liftID) throws SQLException {
        return checkTimeInterval(startDate, duration, loadSchedule(), liftID);
    }

    public static boolean checkTimeInterval(LocalDateTime startDate, double duration, List<Schedule> scheduleList, int liftID) throws SQLException {
        return (findSchedule(startDate, duration, scheduleList, liftID) == null ? true : false);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleID=" + scheduleID +
                ", clientID=" + clientID +
                ", carID=" + carID +
                ", liftID=" + liftID +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", comment='" + comment +
                ", mileage=" + mileage +
                '}';
    }


}
