package by.bsuir.service;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
public class ClientConnectorImpl implements ClientConnector {
    private final ConnectedClientInfo clientInfo;

    public ClientConnectorImpl(ConnectedClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    @Override
    public <T> T receiveObject() throws IOException, ClassNotFoundException {
        Socket socket = clientInfo.getConnectionSocket();
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (T) objectInputStream.readObject();
    }

    @Override
    public void sendObject(Serializable object) throws IOException {
        Socket socket = clientInfo.getConnectionSocket();
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }
}
