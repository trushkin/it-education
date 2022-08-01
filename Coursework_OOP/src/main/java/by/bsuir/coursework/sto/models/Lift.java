package by.bsuir.coursework.sto.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Lift {
    private String name;
    private int liftID;

    @Override
    public String toString() {
        return name;
    }

    public Lift(String name, int liftID) {
        this.name = name;
        this.liftID = liftID;
    }

    public void setLiftID(int liftID) {
        this.liftID = liftID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLiftID() {
        return liftID;
    }

    public String getName() {
        return name;
    }

    public static ObservableList<Lift> loadLifts() throws SQLException {
        ObservableList<Lift> liftList = FXCollections.observableArrayList();
        String selectSql = "SELECT Name, LiftID from Lifts";
        Statement statement = DatabaseCheck.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            liftList.add(new Lift(resultSet.getString(1), resultSet.getInt(2)));
        }
        return liftList;

    }



}
