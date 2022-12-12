package by.bsuir.operation;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Client;
import by.pojo.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateOperationCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public UpdateOperationCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        String selectSql = "UPDATE Operations SET" + " OperationName = ?, OperationCost = ? WHERE OperationId = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            Operation operation = clientConnector.receiveObject();
            statement.setString(1, operation.getName());
            statement.setInt(2, operation.getCost());
            statement.setInt(3, operation.getOperationId());
            int code = statement.executeUpdate();
            if (code >= 1) {
                logger.debug("Operation with ID: {} updated successfully", operation.getOperationId());
                clientConnector.sendObject(true);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
