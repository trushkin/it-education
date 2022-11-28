package by.bsuir;

import by.bsuir.car.CreateCarCommand;
import by.bsuir.car.DeleteCarCommand;
import by.bsuir.car.GetAllCarsCommand;
import by.bsuir.car.UpdateCarCommand;
import by.bsuir.client.CreateClientCommand;
import by.bsuir.client.DeleteClientCommand;
import by.bsuir.client.GetAllClientsCommand;
import by.bsuir.client.UpdateClientCommand;
import by.bsuir.service.ClientConnector;
import by.bsuir.service.ConnectedClientInfo;

import java.io.*;
import java.net.Socket;

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
            public <T> T receiveObject() throws IOException, ClassNotFoundException {
                return ClientHandler.this.receiveObject();
            }

            @Override
            public void sendObject(Serializable object) throws IOException {
                ClientHandler.this.sendObject(object);
            }
        };
        while (true) {
            String command = receiveObject();
            System.out.println("Receive command: " + command);
            switch (command) {
                case "GET_ALL_CLIENTS" -> {
                    GetAllClientsCommand getAllClientsCommand = new GetAllClientsCommand(clientConnector);
                    getAllClientsCommand.execute();
                }
                case "CREATE_CLIENT" -> {
                    CreateClientCommand createClientCommand = new CreateClientCommand(clientConnector);
                    createClientCommand.execute();
                }
                case "DELETE_CLIENT" -> {
                    DeleteClientCommand deleteClientCommand = new DeleteClientCommand(clientConnector);
                    deleteClientCommand.execute();
                }
                case "UPDATE_CLIENT" -> {
                    UpdateClientCommand updateClientCommand = new UpdateClientCommand(clientConnector);
                    updateClientCommand.execute();
                }
                case "GET_ALL_CARS" -> {
                    GetAllCarsCommand getAllCarsCommand = new GetAllCarsCommand(clientConnector);
                    getAllCarsCommand.execute();
                }
                case "CREATE_CAR"->{
                    CreateCarCommand createCarCommand=new CreateCarCommand(clientConnector);
                    createCarCommand.execute();
                }
                case "DELETE_CAR"->{
                    DeleteCarCommand deleteCarCommand = new DeleteCarCommand(clientConnector);
                    deleteCarCommand.execute();
                }
                case "UPDATE_CAR"->{
                    UpdateCarCommand updateCarCommand = new UpdateCarCommand(clientConnector);
                    updateCarCommand.execute();
                }

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
