package by.bsuir;

import by.pojo.Client;

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
