package by.bsuir;

import by.bsuir.service.ConnectedClientInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int SERVER_PORT = 1022;
    public static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        logger.info("START");
        logger.info("Server port: {} ", SERVER_PORT);
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        try{
            while (true){
                logger.debug("Waiting for client");
                Socket newClientSocket = serverSocket.accept();
                ConnectedClientInfo newClient = new ConnectedClientInfo(newClientSocket);
                ClientHandler newThread = new ClientHandler(newClient);
                newThread.start();
                logger.debug("Client accepted");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}