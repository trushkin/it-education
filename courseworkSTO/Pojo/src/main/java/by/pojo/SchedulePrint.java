package by.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SchedulePrint implements Serializable {
    private String stateNum;
    private String brand;
    private String model;
    private String FIO;
    private int mileage;
    private LocalDateTime startDate;
    private int duration;
    private int scheduleID;
    private String liftName;
    private String comment;



    public SchedulePrint(String stateNum, String brand, String model, String FIO, int mileage, LocalDateTime startDate, int duration, int scheduleID, String liftName, String comment) {
        this.stateNum = stateNum;
        this.brand = brand;
        this.model = model;
        this.FIO = FIO;
        this.mileage = mileage;
        this.startDate = startDate;
        this.duration = duration;
        this.scheduleID = scheduleID;
        this.liftName = liftName;
        this.comment = comment;

    }

    public int getScheduleID() {
        return scheduleID;
    }


    @Override
    public String toString() {
        return brand + " " + model + " " + stateNum;
    }

    public SchedulePrint(String stateNum, String brand, String model, int scheduleID) {
        this.stateNum = stateNum;
        this.brand = brand;
        this.model = model;
        this.scheduleID = scheduleID;
    }


    public String getStateNum() {
        return stateNum;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getFIO() {
        return FIO;
    }

    public int getMileage() {
        return mileage;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public int getDuration() {
        return duration;
    }
    public String getLiftName() {
        return liftName;
    }

    public String getComment() {
        return comment;
    }
}
