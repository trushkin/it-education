package by.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Schedule implements Serializable {
    private int scheduleID;
    private int clientID;
    private int carID;
    private int liftID;
    private LocalDateTime startDate;
    private int duration;
    private String comment;
    private int mileage;

    public Schedule(int scheduleID, int clientID, int carID, int liftID, LocalDateTime startDate, int duration, String comment, int mileage) {
        this.scheduleID = scheduleID;
        this.clientID = clientID;
        this.carID = carID;
        this.liftID = liftID;
        this.startDate = startDate;
        this.duration = duration;
        this.comment = comment;
        this.mileage = mileage;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getLiftID() {
        return liftID;
    }

    public void setLiftID(int liftID) {
        this.liftID = liftID;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }


}
