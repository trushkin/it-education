package by.bsuir.client;




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

    public String test() throws IOException, ClassNotFoundException {
        sendObject("1");
        return receiveObject();
    }

    public ArrayList<Client> getAllClients() {
        try {
            sendObject("GET_ALL_CLIENTS");
            return receiveObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
