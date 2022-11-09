package by.bsuir.coursework.sto.car;

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

public class Car {
    private String stateNum;
    private String VIN;
    private String brand;
    private String model;
    private int carID;
    private int clientID;

    public static final Logger logger;// = LogManager.getLogger();

    static {
        logger = LogManager.getLogger();
    }
    {
        this.brand = "Toyota"; //Ваня - тойота, я - лексус
    }

    public Car(String stateNum, String VIN, String brand, String model, int carID, int clientID) {
        this.stateNum = stateNum;
        this.VIN = VIN;
        //this.brand = brand;
        this.brand = "lexus";
        this.model = model;
        this.carID = carID;
        this.clientID = clientID;
    }


    public String getStateNum() {
        return stateNum;
    }

    public void setStateNum(String stateNum) {
        stateNum = stateNum;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }


    public static List<Car> loadCarsInCb(int clientID) throws SQLException {
        String selectSql;
        if (clientID == -1) {
            selectSql = "SELECT * from Cars";
        } else
            selectSql = "SELECT StateNum, VIN, Brand, Model, CarID, ClientID from Cars Where ClientID = '" + clientID + "'";
        List<Car> carList = new ArrayList<>();
        PreparedStatement preparedStatement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            carList.add(new Car(resultSet.getString("StateNum"), resultSet.getString("VIN"),
                    resultSet.getString("Brand"), resultSet.getString("Model"),
                    resultSet.getInt("CarID"), resultSet.getInt("ClientID")));

        }
        return carList;
    }

    public static void addCar(String stateNum, String VIN, String brand, String model, int clientID) throws SQLException {
        String selectSql = "INSERT INTO Cars(StateNum, VIN, Brand, Model, ClientID) VALUES ('" + stateNum + "','"
                + VIN + "','" + brand + "','" + model + "','" + clientID + "')";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        int code = statement.executeUpdate();
        if (code == 1) {
            logger.info("Car added successfully");
            return;
        }
        throw null;

    }

    public static void deleteCar(int carID) throws SQLException {
        String selectSql = "DELETE FROM Cars WHERE CarID =  '" + carID + "'";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        int code = statement.executeUpdate();
        if (code >= 1) {
            logger.info("Car deleted successfully");
            return;
        }
        throw null;
    }

    public static ArrayList<CarRow> loadCarDetails() throws SQLException {
        ArrayList<CarRow> carList = new ArrayList<CarRow>();
        String selectSql = "SELECT StateNum, VIN, Brand, Model, CarID, Cars.ClientID, FIO from Cars INNER JOIN Clients ON Cars.ClientID = Clients.ClientID";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            carList.add(new CarRow(resultSet.getString("StateNum"), resultSet.getString("VIN"),
                    resultSet.getString("Brand"), resultSet.getString("Model"),
                    resultSet.getInt("CarID"), resultSet.getInt("ClientID"),
                    resultSet.getString("FIO")));
        }
        return carList;
    }

    public static void updateCar(int carID, String stateNum, String VIN, String brand, String model, int clientID) throws SQLException {
        String selectSql = "UPDATE Cars SET StateNum = '" + stateNum + "', VIN = '" + VIN + "', Brand = '" +
                brand + "', Model = '" + model + "', ClientID = '" + clientID + "' WHERE CarID = '" + carID + "'";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        int code = statement.executeUpdate();
        if (code >= 1) {
            logger.info("Car updated successfully");
            return;
        }
    }

    public static List<Car> loadAllCars() throws SQLException {
        List<Car> carList = new ArrayList<>();
        String selectSql = "SELECT * from Cars";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            carList.add(new Car(resultSet.getString("StateNum"), resultSet.getString("VIN"),
                    resultSet.getString("Brand"), resultSet.getString("Model"),
                    resultSet.getInt("CarID"), resultSet.getInt("ClientID")));
        }
        return carList;
    }

    public static Car getCarByID(int carID, List<Car> carList) throws SQLException {
        for (Car temp : carList) {
            if (temp.getCarID() == carID)
                return temp;
        }
        return null;
    }


    @Override
    public String toString() {
        return brand + " " + model + " " + stateNum;
    }
}
