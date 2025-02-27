package co.za.share.Server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startSever() {
        try {
            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has conneted!");
                ClientHandler userHandler = new ClientHandler(socket);

                Thread thread = new Thread(userHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage() + "\nServer not started!");
            e.printStackTrace();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(9000);
        Server server = new Server(serverSocket);
        server.startSever();
    }
}
