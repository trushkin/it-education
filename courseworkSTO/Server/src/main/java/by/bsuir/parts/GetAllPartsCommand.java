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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllPartsCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public GetAllPartsCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        ArrayList<Parts> partsArrayList = new ArrayList<>();
        String selectSql = "SELECT PartId, PartCost, PartName, ProducingCountry from Parts";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                partsArrayList.add(new Parts(resultSet.getInt("PartId"), resultSet.getString("PartName"), resultSet.getInt("PartCost"), resultSet.getString("ProducingCountry")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(partsArrayList);
            logger.debug("Parts list sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
