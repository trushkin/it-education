package by.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Schedule implements Serializable {
    private int scheduleID;
    private int clientID;
    private int carID;
    private int liftID;
    private LocalDateTime startDate;
    private int duration;
    private String comment;
    private int mileage;
    private ArrayList<Parts> partsArrayList;
    private ArrayList<Operation> operationArrayList;

    public Schedule(int clientID, int carID, int liftID, LocalDateTime startDate, int duration, String comment, int mileage, ArrayList<Parts> partsArrayList, ArrayList<Operation> operationArrayList) {
        this.clientID = clientID;
        this.carID = carID;
        this.liftID = liftID;
        this.startDate = startDate;
        this.duration = duration;
        this.comment = comment;
        this.mileage = mileage;
        this.partsArrayList = partsArrayList;
        this.operationArrayList = operationArrayList;
    }

    public ArrayList<Parts> getPartsArrayList() {
        return partsArrayList;
    }

    public void setPartsArrayList(ArrayList<Parts> partsArrayList) {
        this.partsArrayList = partsArrayList;
    }

    public ArrayList<Operation> getOperationArrayList() {
        return operationArrayList;
    }

    public void setOperationArrayList(ArrayList<Operation> operationArrayList) {
        this.operationArrayList = operationArrayList;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                ", clientID=" + clientID +
                ", carID=" + carID +
                ", liftID=" + liftID +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", comment='" + comment + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    public Schedule(int clientID, int carID, int liftID, LocalDateTime startDate, int duration, String comment, int mileage) {
        this.clientID = clientID;
        this.carID = carID;
        this.liftID = liftID;
        this.startDate = startDate;
        this.duration = duration;
        this.comment = comment;
        this.mileage = mileage;
    }

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
