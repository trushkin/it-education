package by.bsuir.service;

import by.pojo.Client;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public interface ClientConnector {

    <T> T receiveObject() throws IOException, ClassNotFoundException;


    void sendObject(Serializable object) throws IOException;
}
