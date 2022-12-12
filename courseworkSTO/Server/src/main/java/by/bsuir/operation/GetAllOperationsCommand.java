package by.bsuir.operation;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllOperationsCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public GetAllOperationsCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute()  {
        ArrayList<Operation> operationArrayList = new ArrayList<>();
        String selectSql = "SELECT OperationId, OperationCost, OperationName from Operations";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                operationArrayList.add(new Operation(resultSet.getInt("OperationId"), resultSet.getString("OperationName"), resultSet.getInt("OperationCost")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(operationArrayList);
            logger.debug("Operation list sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
