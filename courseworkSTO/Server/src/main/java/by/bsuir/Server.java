package by.bsuir;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int SERVER_PORT = 1022;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        try{
            while (true){
                System.out.println("Waiting for client");
                Socket newClientSocket = serverSocket.accept();
                ConnectedClientInfo newClient = new ConnectedClientInfo(newClientSocket);
                ClientHandler newThread = new ClientHandler(newClient);
                newThread.start();
                System.out.println("Client accepted");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}