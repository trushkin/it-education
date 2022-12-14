package by.bsuir.user;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Schedule;
import by.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllUsersCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public GetAllUsersCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        ArrayList<User> userArrayList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Users Where Role != 'Admin'";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userArrayList .add(new User(resultSet.getInt("UserID"), resultSet.getString("Login"), resultSet.getString("Password"), resultSet.getString("Role")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(userArrayList);
            logger.debug("User list sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
