package by.bsuir.client.service;


import by.pojo.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnectionModule {
    private Socket socket;
    private final String serverIp;
    private final int serverPort;

    public ClientConnectionModule(String serverIp, int serverPort) {

        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    private void sendObject(Serializable object) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }

    private <T> T receiveObject() throws IOException, ClassNotFoundException {

        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (T) objectInputStream.readObject();
    }

    public void connectToServer() throws IOException {
        socket = new Socket(serverIp, serverPort);
    }

    public ArrayList<Client> getAllClients() {
        try {
            sendObject("GET_ALL_CLIENTS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createClient(Client client) {
        try {
            sendObject("CREATE_CLIENT");
            sendObject(client);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteClient(int clientId) {
        try {
            sendObject("DELETE_CLIENT");
            sendObject(clientId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateClient(Client client) {
        try {
            sendObject("UPDATE_CLIENT");
            sendObject(client);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CarRow> getAllCars() {
        try {
            sendObject("GET_ALL_CARS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean createCar(Car car) {
        try {
            sendObject("CREATE_CAR");
            sendObject(car);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteCar(int carId) {
        try {
            sendObject("DELETE_CAR");
            sendObject(carId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateCar(Car car) {
        try {
            sendObject("UPDATE_CAR");
            sendObject(car);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SchedulePrint> getSchedulesToPrint() {
        try {
            sendObject("GET_SCHEDULES_TO_PRINT");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Car> loadClientCarsInCB(int clientId) {
        try {
            sendObject("LOAD_CLIENT_CARS_IN_CB");
            sendObject(clientId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean deleteSchedule(int scheduleId){
        try{
            sendObject("DELETE_SCHEDULE");
            sendObject(scheduleId);
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Properties getWorkingHours(){
        try{
            sendObject("GET_WORKING_HOURS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Lift> getAllLifts(){
        try{
            sendObject("GET_ALL_LIFTS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

