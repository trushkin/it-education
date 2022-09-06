package by.bsuir.coursework.sto.client;

import by.bsuir.coursework.sto.application.DatabaseConnectionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String FIO;
    private String mobNum;
    private int clientID;


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
        Statement statement = DatabaseConnectionProvider.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            clientList.add(new Client(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
        }
        return clientList;
    }

    public static ObservableList<Client> loadClientsInForm() throws SQLException {
        ObservableList<Client> clientList = FXCollections.observableArrayList();
        String selectSql = "SELECT FIO, MobNum, ClientID from Clients";
        Statement statement = DatabaseConnectionProvider.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            clientList.add(new Client(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
        }
        return clientList;
    }


    public static void addClient(String FIO, String mobNum) throws SQLException {
//        List<Client> clientList = new ArrayList<Client>();
//        try {
//            clientList = Client.loadClients();
//        } catch (SQLException e) {
//            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
//            System.out.println("Ошибка SQL !");
//            return;
//        }
//        int ID = 0;
//        if (!(clientList.isEmpty())) {
//            for (Client temp : clientList) {
//                if (ID <= temp.clientID)
//                    ID = temp.clientID + 1;
//            }
//        }
        String  selectSql = "INSERT INTO Clients VALUES ('" + FIO + "','" + mobNum + "')";
        Statement statement = DatabaseConnectionProvider.getConnection().createStatement();
        int code = statement.executeUpdate(selectSql);
        if (code == 1) {
            System.out.println("Запрос успешно выполнен!");
            return;
        }
        //throw null;

    }

    public static void deleteClient(int clientID) throws SQLException{
        String selectSql = "DELETE FROM Clients WHERE ClientID =  '" + clientID + "'";
        Statement statement = DatabaseConnectionProvider.getConnection().createStatement();
        int code = statement.executeUpdate(selectSql);
        if (code >= 1) {
            System.out.println("Запрос успешно выполнен!");
            return;
        }
       // throw null;
    }

    public static void updateClient(int clientID, String newFIO, String newMobNum) throws SQLException{
        String selectSql = "UPDATE Clients SET FIO = '" + newFIO + "', MobNum = '" + newMobNum + "' WHERE ClientID = '" + clientID + "'";
        Statement statement = DatabaseConnectionProvider.getConnection().createStatement();
        int code = statement.executeUpdate(selectSql);
        if (code >= 1) {
            System.out.println("Запрос успешно выполнен!");
            return;
        }
    }

}
