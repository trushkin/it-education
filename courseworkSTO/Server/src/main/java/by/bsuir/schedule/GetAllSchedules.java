package by.bsuir.schedule;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Car;
import by.pojo.Schedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllSchedules implements ManageCommand {
    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public GetAllSchedules(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
        String sqlQuery = "SELECT ScheduleID, ClientID, CarID, LiftID, StartDate, Duration, Comment, Mileage from Schedule";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                scheduleArrayList .add(new Schedule(resultSet.getInt("ScheduleID"), resultSet.getInt("ClientID"), resultSet.getInt("CarID"), resultSet.getInt("LiftID"), resultSet.getTimestamp("StartDate").toLocalDateTime(), resultSet.getInt("Duration"), resultSet.getString("Comment"), resultSet.getInt("Mileage")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(scheduleArrayList);
            logger.debug("Car list sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
