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

public class CreateOperationCommand implements ManageCommand {
    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public CreateOperationCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        String selectSql = "INSERT INTO Operations" + " (OperationCost, OperationName) " + ("VALUES(?, ?)");
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            Operation operation = clientConnector.receiveObject();
            statement.setInt(1, operation.getCost());
            statement.setString(2, operation.getName());
            int code = statement.executeUpdate();
            if (code == 1) {
                logger.debug("Operation with ID: {} created successfully", operation.getOperationId());
                clientConnector.sendObject(true);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
