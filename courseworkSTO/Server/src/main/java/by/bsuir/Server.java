package by.bsuir;

import by.bsuir.service.ApplicationContextConnector;
import by.bsuir.service.ConnectedClientInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int SERVER_PORT = 1022;
    public static Logger logger = LogManager.getLogger(Server.class);


    public static void main(String[] args) throws IOException {
        //  logger.info("START");
        // logger.info("Server port: {} ", SERVER_PORT);
        logger.trace("trace");
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        try {
            ClassPathXmlApplicationContext context = ApplicationContextConnector.getContext();
            while (true) {
                logger.debug("Waiting for client");
                Socket newClientSocket = serverSocket.accept();
                ConnectedClientInfo newClient = context.getBean(ConnectedClientInfo.class, newClientSocket);
                //new ConnectedClientInfo(newClientSocket);
               // ClientHandler newThread = new ClientHandler(newClient);
                ClientHandler newThread = context.getBean(ClientHandler.class, newClient, context);
                newThread.start();
                logger.debug("Client accepted");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}