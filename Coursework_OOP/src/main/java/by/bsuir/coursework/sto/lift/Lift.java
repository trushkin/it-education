package by.bsuir.coursework.sto.lift;

import by.bsuir.coursework.sto.application.DatabaseConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Lift {
    private String liftType;
    private int liftID;

    @Override
    public String toString() {
        return liftType;
    }

    public Lift(String name, int liftID) {
        this.liftType = name;
        this.liftID = liftID;
    }

    public void setLiftID(int liftID) {
        this.liftID = liftID;
    }

    public void setLiftType(String liftType) {
        this.liftType = liftType;
    }

    public int getLiftID() {
        return liftID;
    }

    public String getLiftType() {
        return liftType;
    }

    public static ArrayList<Lift> loadLifts() throws SQLException {
       ArrayList<Lift> liftList = new ArrayList<Lift>();
        String selectSql = "SELECT LiftType, LiftID from Lifts";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            liftList.add(new Lift(resultSet.getString(1), resultSet.getInt(2)));
        }
        return liftList;

    }



}
