package by.bsuir.schedule;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Operation;
import by.pojo.Parts;
import by.pojo.Schedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;

public class CreateScheduleCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public CreateScheduleCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        try {
            Schedule schedule = clientConnector.receiveObject();
            String sqlQuery = "INSERT INTO Schedule(ClientID, CarID, LiftID, StartDate, Duration, Comment, Mileage) VALUES ('" + schedule.getClientID()+ "','" + schedule.getCarID() + "','" + schedule.getLiftID() + "','" + Timestamp.valueOf(schedule.getStartDate()) + "','" + schedule.getDuration() + "','" + schedule.getComment() + "','" + schedule.getMileage() + "')";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            int code = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next())
            {
                schedule.setScheduleID(rs.getInt(1));
            }
            if (code == 1) {
                logger.debug("Schedule with ID: {} created successfully", schedule.getScheduleID());
            }
            sqlQuery = "INSERT INTO ScheduleOperation(ScheduleID, OperationID) Values(?, ?)";
            statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);
            for (Operation temp: schedule.getOperationArrayList()) {
                statement.setInt(1, schedule.getScheduleID());
                statement.setInt(2, temp.getOperationId());
                statement.executeUpdate();
            }
            sqlQuery = "INSERT INTO SchedulePart(ScheduleID, PartID) Values(?, ?)";
            statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);
            for (Parts temp: schedule.getPartsArrayList()) {
                statement.setInt(1, schedule.getScheduleID());
                statement.setInt(2, temp.getPartId());
                statement.executeUpdate();
            }
            clientConnector.sendObject(true);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    }

