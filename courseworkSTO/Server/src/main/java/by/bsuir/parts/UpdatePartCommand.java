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

public class UpdatePartCommand implements ManageCommand {
    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public UpdatePartCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        String selectSql = "UPDATE Parts SET" + " PartName = ?, PartCost = ?, ProducingCountry = ? WHERE PartId = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            Parts part = clientConnector.receiveObject();
            statement.setString(1, part.getName());
            statement.setInt(2, part.getCost());
            statement.setString(3, part.getProducingCountry());
            statement.setInt(4, part.getPartId());
            int code = statement.executeUpdate();
            if (code >= 1) {
                logger.debug("Part with ID: {} updated successfully", part.getPartId());
                clientConnector.sendObject(true);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
