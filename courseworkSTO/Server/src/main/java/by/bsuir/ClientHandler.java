package by.bsuir;

import by.pojo.Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private final ConnectedClientInfo clientInfo;

    public ClientHandler(ConnectedClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    private void sendObject(Serializable object) throws IOException {

        Socket socket = clientInfo.getConnectionSocket();
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }

    public <T> T receiveObject() throws IOException, ClassNotFoundException {

        Socket socket = clientInfo.getConnectionSocket();
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (T) objectInputStream.readObject();
    }

    private void clientProcessing() throws IOException, ClassNotFoundException {
        ClientConnector clientConnector = new ClientConnector() {
            @Override
            public Client receiveObject() throws IOException, ClassNotFoundException {
                return receiveObject();
            }

            @Override
            public void sendObject(ArrayList<Client> clientArrayList) throws IOException {
                ClientHandler.this.sendObject(clientArrayList);
            }
        };
        while (true) {
            String command = receiveObject();
            switch (command) {
                case "GET_ALL_CLIENTS" -> {
                    GetAllClientsCommand getAllClientsCommand = new GetAllClientsCommand(clientConnector);
                    getAllClientsCommand.execute();
                }
                case "2" -> sendObject("test2");

                default -> throw new IllegalStateException("Unexpected value: " + command);
            }
        }
    }

    @Override
    public void run() {
        System.out.println("start run");
        try {
            clientProcessing();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
        System.out.println("start run");
        super.run();
    }
}
