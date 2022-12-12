package by.bsuir.user;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public LoginCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        logger.debug("asd");
        User user;
        try {
            user = clientConnector.receiveObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String selectSql = "SELECT * FROM Users WHERE Login = '" + user.getLogin() + "' AND Password = '" + user.getPassword() + "'";
       logger.debug(selectSql);
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            ResultSet resultSet = statement.executeQuery();
            user = null;
            while (resultSet.next()){
                user = new User(resultSet.getInt("UserID"), resultSet.getString("Login"), resultSet.getString("Password"), resultSet.getString("Role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(user);
            logger.debug("Login checked");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
