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
    private String name;
    private String surname;
    private String patronymic;
    private String mobNum;
    private int clientID;

    public Client(String name, String surname, String patronymic, String mobNum, int clientID) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.mobNum = mobNum;
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }


    public static Logger logger = LogManager.getLogger();

    @Override
    public String toString() {
        return name + " " +  surname +" "+ patronymic;
    }

    public String getFIO() {
        return name + " " +  surname +" "+ patronymic;
    }

    public String getMobNum() {
        return mobNum;
    }

    public int getClientID() {
        return clientID;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public static List<Client> loadClientsInForm() throws SQLException {
        List<Client> clientList = new ArrayList<>();
        String selectSql = "SELECT Name, Surname, Patronymic, MobNum, ClientID from Clients";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            clientList.add(new Client(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    resultSet.getInt(5)));
        }
        return clientList;
    }


    public static void addClient(String name, String surname, String patronymic, String mobNum) throws SQLException {

        String selectSql = "INSERT INTO Clients" + " (Name, Surname, Patronymic, MobNum) " + ("VALUES(?, ?, ?, ?)");
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setString(3, patronymic);
        statement.setString(4, mobNum);
        int code = statement.executeUpdate();
        if (code == 1) {
            logger.info("Client added successfully");
        }

    }

    public static void deleteClient(int clientID) throws SQLException {
        String selectSql = "DELETE FROM Clients WHERE ClientID =  '" + clientID + "'";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        int code = statement.executeUpdate();
        if (code >= 1) {
            logger.info("Client {} deleted successfully", clientID);
        }
    }

    public static void updateClient(int clientID, String newName,String newSurname, String newPatronymic, String newMobNum) throws SQLException {
        String selectSql = "UPDATE Clients SET" + " Name = ?, Surname = ?, Patronymic = ?, MobNum = ? WHERE ClientID = ?";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        statement.setString(1, newName);
        statement.setString(2,  newSurname);
        statement.setString(3, newPatronymic);
        statement.setString(4, newMobNum);
        statement.setInt(5, clientID);
        int code = statement.executeUpdate();
        if (code >= 1) {
            logger.info("Client updated successfully");
        }
    }

}
