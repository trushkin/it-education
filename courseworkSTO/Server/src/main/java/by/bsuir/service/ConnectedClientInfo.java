package by.bsuir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.Socket;


public class ConnectedClientInfo {
    private Socket connectionSocket;

    public ConnectedClientInfo() {
    }

    public ConnectedClientInfo(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    public Socket getConnectionSocket() {
        return connectionSocket;
    }

    public synchronized void setConnectionSocket(Socket connectionSocket) {
        if (connectionSocket == null) return;
        this.connectionSocket = connectionSocket;
    }

}

