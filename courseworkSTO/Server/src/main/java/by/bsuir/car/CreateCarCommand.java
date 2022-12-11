package by.bsuir.car;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CreateCarCommand implements ManageCommand {
    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public CreateCarCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        String sqlQuery = "INSERT INTO Cars(StateNum, VIN, Brand, Model, ClientID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            Car car = clientConnector.receiveObject();
            statement.setString(1, car.getStateNum());
            statement.setString(2, car.getVIN());
            statement.setString(3, car.getBrand());
            statement.setString(4, car.getModel());
            statement.setInt(5, car.getClientID());
            int code = statement.executeUpdate();
            if (code == 1) {
                logger.debug("Car with ID: {} created successfully", car.getClientID());
                clientConnector.sendObject(true);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            logger.error("Adding car error!\n" + new RuntimeException(e));
            throw new RuntimeException(e);

        }
    }

}
