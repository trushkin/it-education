package by.bsuir.client;




import by.pojo.Car;
import by.pojo.CarRow;
import by.pojo.Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnectionModule {
    private Socket socket;
    private String serverIp;
    private int serverPort;

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

    public void createClient(Client client){
        try {
            sendObject("CREATE_CLIENT");
            sendObject(client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteClient(int clientId){
        try {
            sendObject("DELETE_CLIENT");
            sendObject(clientId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateClient(Client client){
        try{
            sendObject("UPDATE_CLIENT");
            sendObject(client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CarRow> getAllCars(){
        try {
            sendObject("GET_ALL_CARS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createCar(Car car){
        try {
            sendObject("CREATE_CAR");
            sendObject(car);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCar(int carId){
        try {
            sendObject("DELETE_CAR");
            sendObject(carId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCar(Car car){
        try{
            sendObject("UPDATE_CAR");
            sendObject(car);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
