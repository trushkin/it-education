package by.bsuir.lift;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Client;
import by.pojo.Lift;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllLiftsCommand implements ManageCommand {
    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public GetAllLiftsCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        ArrayList<Lift> liftList = new ArrayList<>();
        String selectSql = "SELECT LiftType, LiftID, LiftName, LiftingCapacity from Lifts";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                liftList.add(new Lift(resultSet.getString("LiftType"), resultSet.getInt("LiftID"), resultSet.getString("LiftName"), resultSet.getInt("LiftingCapacity")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(liftList);
            logger.debug("Lift list sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

