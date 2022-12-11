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

public class UpdateCarCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public UpdateCarCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        String selectSql = "UPDATE Cars SET" + " StateNum = ?, VIN = ?, Brand = ?, Model = ?, ClientID = ? WHERE CarID = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            Car car = clientConnector.receiveObject();
            statement.setString(1, car.getStateNum());
            statement.setString(2, car.getVIN());
            statement.setString(3, car.getBrand());
            statement.setString(4, car.getModel());
            statement.setInt(5, car.getClientID());
            statement.setInt(6, car.getCarID());
            int code = statement.executeUpdate();
            if (code >= 1) {
                logger.debug("Car with ID: {} updated successfully", car.getCarID());
                clientConnector.sendObject(true);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
