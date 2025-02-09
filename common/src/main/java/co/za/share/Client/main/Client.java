package co.za.share.Client.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import co.za.share.Client.SignUp.SignUp;
import co.za.share.Client.User.UserCredentials;
import co.za.share.Client.User.UserDetailsToSend;
import co.za.share.Server.UserDetails.UniqueIdentifierCreator;

public class Client {
    private Scanner scanner;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userDetails;
    
    public Client(Socket socket, Scanner scanner, String userDetails) {
        try {
            this.scanner = scanner;
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(userDetails);
            this.userDetails = userDetails;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(this.userDetails);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            while (socket.isConnected()) {
                String messageToSend = this.scanner.nextLine();
                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException E) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromServer;
                while (socket.isConnected()) {
                    try {
                        messageFromServer = bufferedReader.readLine();
                        System.out.println(messageFromServer);
                    } catch (Exception e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        UserCredentials user = new UserCredentials();
        UniqueIdentifierCreator uniqueIdentifierCreator = new UniqueIdentifierCreator();
        SignUp signUp = new SignUp(scanner, user);
        signUp.signUp(uniqueIdentifierCreator.createUserID());
        Socket socket = new Socket("localhost", 9000);
        Client client = new Client(socket, scanner, UserDetailsToSend.createUserCredentialsJSONObject(user).toString());
        client.listenForMessage();
        client.sendMessage();
    }
}
