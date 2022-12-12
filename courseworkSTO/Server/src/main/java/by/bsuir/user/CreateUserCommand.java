package by.bsuir.user;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Operation;
import by.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUserCommand implements ManageCommand {
    private static Logger logger = LogManager.getLogger();
    private ClientConnector clientConnector;

    public CreateUserCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        String selectSql = "INSERT INTO Users" + " (Login, Password, Role) " + ("VALUES(?, ?, ?)");
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            User user = clientConnector.receiveObject();
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, "Receptionist");
            int code = statement.executeUpdate();
            if (code == 1) {
                logger.debug("User created successfully");
                clientConnector.sendObject(true);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
