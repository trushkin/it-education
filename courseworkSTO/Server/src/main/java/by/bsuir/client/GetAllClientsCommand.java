package by.bsuir.client;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllClientsCommand implements ManageCommand {
    private ClientConnector clientConnector;

    public GetAllClientsCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }
    private static Logger logger = LogManager.getLogger();
    @Override
    public void execute() {
        ArrayList<Client> clientList = new ArrayList<>();
        String selectSql = "SELECT Name, Surname, Patronymic, MobNum, ClientID from Clients";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clientList.add(new Client(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(clientList);
            logger.debug("Client list sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
