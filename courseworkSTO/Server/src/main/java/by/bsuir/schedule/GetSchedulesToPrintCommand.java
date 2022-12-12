package by.bsuir.schedule;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.SchedulePrint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetSchedulesToPrintCommand implements ManageCommand {

    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public GetSchedulesToPrintCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        ArrayList<SchedulePrint> scheduleList = new ArrayList<SchedulePrint>();
        String selectSql = "select Lifts.LiftName, Cars.StateNum, Cars.Brand, Cars.Model, Clients.Name, Clients.Surname, Clients.Patronymic, Schedule.Mileage, Schedule.StartDate, Schedule.Duration, Schedule.ScheduleID, Schedule.Comment from Schedule\n" +
                "\tinner join Cars on Schedule.CarID = Cars.CarID\n" +
                "\tinner join Clients on Schedule.ClientID = Clients.ClientID\n" +
                "\tinner join Lifts on Schedule.LiftID = Lifts.LiftID \n";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                scheduleList.add(new SchedulePrint(resultSet.getString("StateNum"), resultSet.getString("Brand"), resultSet.getString("Model"), resultSet.getString("Name") + " " + resultSet.getString("Surname") + " " + resultSet.getString("Patronymic"), resultSet.getInt("Mileage"), resultSet.getTimestamp("StartDate").toLocalDateTime(), resultSet.getInt("Duration"), resultSet.getInt("ScheduleID"), resultSet.getString("LiftName"), resultSet.getString("Comment")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(scheduleList);
            logger.debug("Car list to tableView sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
