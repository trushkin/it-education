package by.bsuir.coursework.sto.car;

public class CarRow {
    private String stateNum;
    private String VIN;
    private String brand;
    private String model;
    private int carID;
    private int clientID;
    private String FIO;


    @Override
    public String toString() {
        return brand +" " + model + " " + stateNum;
    }

    public CarRow(String stateNum, String VIN, String brand, String model, int carID, int clientID, String FIO) {
        this.stateNum = stateNum;
        this.VIN = VIN;
        this.brand = brand;
        this.model = model;
        this.carID = carID;
        this.clientID = clientID;
        this.FIO = FIO;
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

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }



}
