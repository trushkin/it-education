package by.bsuir.coursework.sto.client;

import by.bsuir.coursework.sto.application.DatabaseConnectionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String FIO;
    private String mobNum;
    private int clientID;

    public static Logger logger = LogManager.getLogger();
    @Override
    public String toString() {
        return FIO;
    }

    public Client(String FIO, String mobNum, int ClientID)
    {
        this.FIO = FIO;
        this.mobNum = mobNum;
        this.clientID = ClientID;
    }

    public String getFIO() {
        return FIO;
    }

    public String getMobNum() {
        return mobNum;
    }

    public int getClientID() {
        return clientID;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public static List<Client> loadClientsInConsole() throws SQLException {
        List<Client> clientList = new ArrayList<Client>();
        String selectSql = "SELECT FIO, MobNum, ClientID from Clients";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            clientList.add(new Client(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getInt(3)));
        }
        return clientList;
    }

    public static ArrayList<Client> loadClientsInForm() throws SQLException {
        ArrayList<Client> clientList = new ArrayList<Client>();
        String selectSql = "SELECT FIO, MobNum, ClientID from Clients";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            clientList.add(new Client(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getInt(3)));
        }
        return clientList;
    }


    public static void addClient(String FIO, String mobNum) throws SQLException {

        String  selectSql = "INSERT INTO Clients VALUES ('" + FIO + "','" + mobNum + "')";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        int code = statement.executeUpdate();
        if (code == 1) {
            logger.info("Client added successfully");
            return;
        }

    }

    public static void deleteClient(int clientID) throws SQLException{
        String selectSql = "DELETE FROM Clients WHERE ClientID =  '" + clientID + "'";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        int code = statement.executeUpdate();
        if (code >= 1) {
            logger.info("Client deleted successfully");
            return;
        }
    }

    public static void updateClient(int clientID, String newFIO, String newMobNum) throws SQLException{
        String selectSql = "UPDATE Clients SET FIO = '" + newFIO + "', MobNum = '" + newMobNum + "' WHERE ClientID = '" +
                clientID + "'";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        int code = statement.executeUpdate();
        if (code >= 1) {
            logger.info("Client updated successfully");
            return;
        }
    }

}
