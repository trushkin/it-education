package by.bsuir.coursework.sto.lift;

import by.bsuir.coursework.sto.application.DatabaseConnectionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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

    public static ArrayList<Lift> loadLifts() throws SQLException {
       ArrayList<Lift> liftList = new ArrayList<Lift>();
        String selectSql = "SELECT Name, LiftID from Lifts";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            liftList.add(new Lift(resultSet.getString(1), resultSet.getInt(2)));
        }
        return liftList;

    }



}
