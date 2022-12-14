package by.bsuir.user;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUserCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public DeleteUserCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        Integer userId = null;
        try {
            userId = clientConnector.receiveObject();
            String sqlQuery = "DELETE FROM Users WHERE UserID =  " + userId + "";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);
            int code = statement.executeUpdate();
            if (code >= 1) {
                logger.debug("User with ID: {} deleted successfully", userId);
                clientConnector.sendObject(true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
