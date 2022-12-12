package by.bsuir.parts;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Operation;
import by.pojo.Parts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatePartCommand implements ManageCommand {
    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public CreatePartCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        String selectSql = "INSERT INTO Parts" + " (PartCost, PartName, ProducingCountry) " + ("VALUES(?, ?, ?)");
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            Parts part = clientConnector.receiveObject();
            statement.setInt(1, part.getCost());
            statement.setString(2, part.getName());
            statement.setString(3, part.getProducingCountry());
            int code = statement.executeUpdate();
            if (code == 1) {
                logger.debug("Operation with ID: {} created successfully", part.getPartId());
                clientConnector.sendObject(true);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
