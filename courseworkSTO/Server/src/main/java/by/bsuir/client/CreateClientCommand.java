package by.bsuir.client;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CreateClientCommand implements ManageCommand {
    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public CreateClientCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        String selectSql = "INSERT INTO Clients" + " (Name, Surname, Patronymic, MobNum) " + ("VALUES(?, ?, ?, ?)");
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            Client client = clientConnector.receiveObject();
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getPatronymic());
            statement.setString(4, client.getMobNum());
            int code = statement.executeUpdate();
            if (code == 1) {
                logger.debug("Client with ID: {} created successfully", client.getClientID());
                clientConnector.sendObject(true);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

