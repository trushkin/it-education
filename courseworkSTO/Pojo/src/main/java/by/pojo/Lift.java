package by.pojo;

import java.io.Serializable;

public class Lift implements Serializable {
    private String liftType;
    private int liftID;
    private String liftName;
    private int liftingCapacity;

    @Override
    public String toString() {
        return liftType;
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

    public Lift(String liftType, int liftID, String liftName, int liftingCapacity) {
        this.liftType = liftType;
        this.liftID = liftID;
        this.liftName = liftName;
        this.liftingCapacity = liftingCapacity;
    }

    public String getLiftName() {
        return liftName;
    }

    public void setLiftName(String liftName) {
        this.liftName = liftName;
    }

    public int getLiftingCapacity() {
        return liftingCapacity;
    }

    public void setLiftingCapacity(int liftingCapacity) {
        this.liftingCapacity = liftingCapacity;
    }
}
