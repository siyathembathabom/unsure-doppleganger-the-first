package co.za.share.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONObject;

import co.za.share.Help;
import co.za.share.Server.Options.Account.Account;
import co.za.share.Server.Options.User.UserCredentials;

public class UserHandler implements Runnable {
    public static ArrayList<UserHandler> userHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private UserCredentials userCredentials;
    private String userDetails;

    public UserHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userCredentials = new UserCredentials();
            this.userDetails = bufferedReader.readLine();
            createUser(this.userDetails);
            userHandlers.add(this);

        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String userInput;
        sendMessage("Welcome! \n" + Help.help());

        while (socket.isConnected()) {
            try {
                sendMessage("Enter an option:");
                userInput = bufferedReader.readLine();
            if (userInput.equalsIgnoreCase("0")) {
                sendMessage("See you next time!");
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }

            if (userInput.equalsIgnoreCase("1")) {
                sendMessage(Help.help());
            }

            if (userInput.equalsIgnoreCase("2")) {
                sendMessage(getUserDetails());
            }

            if (userInput.equalsIgnoreCase("3")) {
                while (true) {
                    sendMessage("Enter amount:");
                    String inputFromClient = listenForMessage();
                    if (getAmount(inputFromClient)) {
                        this.userCredentials.getUserAccount().withdraw(Double.parseDouble(inputFromClient));
                        sendMessage("Your available balance is now: " + this.userCredentials.getUserAccount().getBalance());
                        break;
                    }
                }
            }

            if (userInput.equalsIgnoreCase("4")) {
                while (true) {
                    sendMessage("Enter amount:");
                    String inputFromClient = listenForMessage();
                    if (getAmount(inputFromClient)) {
                        this.userCredentials.getUserAccount().deposit(Double.parseDouble(inputFromClient));
                        sendMessage("Your available balance is now: " + 
                            this.userCredentials.getUserAccount().getBalance());
                        break;
                    }
                }
            }

            if (userInput.equalsIgnoreCase("5")) {
                boolean isTransferSuccessful = false;
                while (!isTransferSuccessful) {
                    sendMessage("Enter account number:");
                    String accountNumber = listenForMessage();
                    sendMessage("Enter Amount: ");
                    String amount = listenForMessage();
                    if (getAmount(amount)) {
                        for (UserHandler user : userHandlers) {
                            if (user.userCredentials.getUserIdentifier().equals(accountNumber)) {
                                if (this.userCredentials.getUserAccount().getBalance() - Double.parseDouble(amount) >= 0) {
                                    this.userCredentials.getUserAccount()
                                    .withdraw(Double.parseDouble(amount));
                                    user.userCredentials.getUserAccount().deposit(Double.parseDouble(amount));
                                }
                                isTransferSuccessful = true;
                            }
                        }
                    }
                    if (!isTransferSuccessful) {
                        sendMessage("Invalid Account number or amount. Please try again");
                    }
                }
            }

            if (userInput.equalsIgnoreCase("6")) {
                sendMessage("Your avialable balance is " + 
                    this.userCredentials.getUserAccount().getBalance());
            }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    } 

    public boolean getAmount(String amountFromClient) {
            try {
                Double.parseDouble(amountFromClient);
            } catch (NumberFormatException e) {
                System.out.println("Your amount is invalid please try again.");
                return false;
            }
            return true;
    }

    public void sendMessage(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String listenForMessage() {
        try {
            return bufferedReader.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public void createUser(String userDetails) {
        JSONObject jsonObject = new JSONObject(userDetails);
        this.userCredentials.setUserName(jsonObject.getString("name"));
        this.userCredentials.setUserEmail(jsonObject.getString("email"));
        this.userCredentials.setUserPhoneNumber(jsonObject.getString("number"));
        this.userCredentials.setUserIdentifier(jsonObject.getString("unique identifier"));
        this.userCredentials.setUserAccount(new Account(this.userCredentials, this.userCredentials.getUserIdentifier()));
    }

    public String getUserDetails() {
        return "Your account details are:" + 
            "\nName:                     " + this.userCredentials.getUserName() + 
            "\nPhone number:             "+ this.userCredentials.getUserPhoneNumber() + 
            "\nEmail:                    " + this.userCredentials.getUserEmail() +
            "\nAccount number:           " + this.userCredentials.getUserIdentifier() +
            "\nBalance                   " + this.userCredentials.getUserAccount().getBalance();
    }

    public void removeClientHandler() {
        userHandlers.remove(this);
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
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
}
