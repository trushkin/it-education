package by.bsuir.car;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadClientCarsInCbCommand implements ManageCommand {
    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public LoadClientCarsInCbCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        ArrayList<Car> carList = new ArrayList<>();
        String sqlQuery;
        int clientID;
        try {
            clientID = clientConnector.receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (clientID == -1) {
            sqlQuery = "SELECT * from Cars";
        } else
            sqlQuery = "SELECT StateNum, VIN, Brand, Model, CarID, ClientID from Cars Where ClientID = '" + clientID + "'";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                carList.add(new Car(resultSet.getString("StateNum"), resultSet.getString("VIN"),
                        resultSet.getString("Brand"), resultSet.getString("Model"),
                        resultSet.getInt("CarID"), resultSet.getInt("ClientID")));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(carList);
            logger.debug("Car list to combobox sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
