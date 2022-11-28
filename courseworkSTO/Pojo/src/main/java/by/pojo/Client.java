package by.pojo;

import java.io.Serializable;

public class Client implements Serializable {

    private String name;
    private String surname;
    private String patronymic;
    private String mobNum;
    private int clientID;

    public Client(String name, String surname, String patronymic, String mobNum, int clientID) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.mobNum = mobNum;
        this.clientID = clientID;
    }
    public Client(String name, String surname, String patronymic, String mobNum) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.mobNum = mobNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getMobNum() {
        return mobNum;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    @Override
    public String toString() {
        return name + " " +  surname +" "+ patronymic;
    }
}
