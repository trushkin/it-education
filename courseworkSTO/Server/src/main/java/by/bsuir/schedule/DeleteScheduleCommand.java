package by.bsuir.schedule;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteScheduleCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public DeleteScheduleCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute(){
        Integer scheduleID = null;
        try {
            scheduleID = clientConnector.receiveObject();
            String sqlQuery = "DELETE FROM Schedule WHERE ScheduleID =  '" + scheduleID + "'";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);
            int code = statement.executeUpdate();
            if (code >= 1) {
                logger.debug("Schedule with ID: {} deleted successfully", scheduleID);
                clientConnector.sendObject(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
