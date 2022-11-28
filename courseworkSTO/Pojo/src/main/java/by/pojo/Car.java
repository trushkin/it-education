package by.pojo;

import java.io.Serializable;

public class Car implements Serializable {
    private String stateNum;
    private String VIN;
    private String brand;
    private String model;
    private int carID;
    private int clientID;

    public Car(String stateNum, String VIN, String brand, String model, int carID, int clientID) {
        this.stateNum = stateNum;
        this.VIN = VIN;
        this.brand = brand;
        this.model = model;
        this.carID = carID;
        this.clientID = clientID;
    }
    public Car(String stateNum, String VIN, String brand, String model, int clientID) {
        this.stateNum = stateNum;
        this.VIN = VIN;
        this.brand = brand;
        this.model = model;
      this.clientID = clientID;
    }

    public String getStateNum() {
        return stateNum;
    }

    public void setStateNum(String stateNum) {
        this.stateNum = stateNum;
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

    @Override
    public String toString() {
        return "Car{" +
                "stateNum='" + stateNum + '\'' +
                ", VIN='" + VIN + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carID=" + carID +
                ", clientID=" + clientID +
                '}';
    }
}
