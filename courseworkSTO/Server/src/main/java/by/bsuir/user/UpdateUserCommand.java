package by.bsuir.user;

import by.bsuir.ClientHandler;
import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.Parts;
import by.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUserCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public UpdateUserCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        String selectSql = "UPDATE Users SET" + " Password = ?, Login = ?, Role = ? WHERE UserID = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            User user = clientConnector.receiveObject();
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getUserID());
            int code = statement.executeUpdate();
            if (code >= 1) {
                logger.debug("Part with ID: {} updated successfully", user.getUserID());
                clientConnector.sendObject(true);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
