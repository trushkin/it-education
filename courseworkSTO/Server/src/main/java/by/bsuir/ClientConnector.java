package by.bsuir;

import by.pojo.Client;

import java.io.IOException;
import java.util.ArrayList;

public interface ClientConnector {

    Client receiveObject() throws IOException, ClassNotFoundException;

    void sendObject(ArrayList<Client> clientArrayList) throws IOException;
}
