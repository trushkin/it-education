package by.bsuir.car;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteCarCommand implements ManageCommand {
    private ClientConnector clientConnector;
    public static Logger logger = LogManager.getLogger();

    public DeleteCarCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        Integer carId = null;
        try {
            carId = clientConnector.receiveObject();
            String sqlQuery = "DELETE FROM Cars WHERE CarID =  '" + carId + "'";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);
            int code = statement.executeUpdate();
            if (code >= 1) {
                logger.debug("Car {} deleted successfully", carId);
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
