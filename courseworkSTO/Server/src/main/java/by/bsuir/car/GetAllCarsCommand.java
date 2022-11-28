package by.bsuir.car;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.CarRow;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllCarsCommand implements ManageCommand {
    private ClientConnector clientConnector;

    public GetAllCarsCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        ArrayList<CarRow> carRowArrayList = new ArrayList<>();
        String selectSql = "SELECT StateNum, VIN, Brand, Model, CarID, Cars.ClientID, Name, Surname, Patronymic from Cars INNER JOIN Clients ON Cars.ClientID = Clients.ClientID";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                carRowArrayList.add(new CarRow(resultSet.getString("StateNum"), resultSet.getString("VIN"),
                        resultSet.getString("Brand"), resultSet.getString("Model"),
                        resultSet.getInt("CarID"), resultSet.getInt("ClientID"),
                        resultSet.getString("Name") + " " + resultSet.getString("Surname") + " " + resultSet.getString("Patronymic")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(carRowArrayList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}