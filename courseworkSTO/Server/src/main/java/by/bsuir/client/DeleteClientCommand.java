package by.bsuir.client;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteClientCommand implements ManageCommand {
    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;
    public DeleteClientCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute()  {
        Integer clientId = null;
        try {
            clientId = clientConnector.receiveObject();
            String sqlQuery = "DELETE FROM Clients WHERE ClientID =  " + clientId + "";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);
            int code = statement.executeUpdate();
            if (code >= 1) {
                logger.debug("Client with ID: {} deleted successfully", clientId);
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
