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
    public static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public CreateCarCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }
    @Override
    public void execute() {
        //String selectSql = "INSERT INTO Cars(StateNum, VIN, Brand, Model, ClientID) VALUES (?, ?, ?, ?, ?)";
        try  {
            Car car = clientConnector.receiveObject();
            String selectSql = "INSERT INTO Cars(StateNum, VIN, Brand, Model, ClientID) VALUES ('" + car.getStateNum() + "','"
                    + car.getVIN() + "','" + car.getBrand() + "','" + car.getModel() + "','" + car.getClientID()+ "')";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql);

//            statement.setString(1, car.getStateNum());
//            statement.setString(2, car.getVIN());
//            statement.setString(3, car.getBrand());
//            statement.setString(4, car.getModel());
//            statement.setInt(5, car.getClientID());
            int code = statement.executeUpdate();
            if (code == 1) {
                logger.debug("Car {} created successfully", car.getClientID());
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        }

}
