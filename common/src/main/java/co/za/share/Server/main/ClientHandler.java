package co.za.share.Server.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONObject;

import co.za.share.Server.Account.Account;
import co.za.share.Server.Account.ValidateTransactions;
import co.za.share.Server.UserDetails.UserCredentials;
import co.za.share.Server.Database.ServerDatabaseHandler;

public class ClientHandler implements Runnable {
    private static ArrayList<ClientHandler> userHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private UserCredentials userCredentials;
    private String userDetails;
    private boolean userStatus;

    public ClientHandler(Socket socket) {
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
        sendMessage("Welcome! \n" + Menu.menu());

        while (socket.isConnected()) {
            try {
                sendMessage("Enter an option:");
                userInput = bufferedReader.readLine();

                if (userInput.equals("1")) {
                    sendMessage(getUserDetails());
                }

                if (userInput.equals("2")) {
                    boolean isWithdrawalSuccessful = false;
                    while (!isWithdrawalSuccessful) {
                        sendMessage("Enter amount:");
                        String amount = listenForMessage();
                        if (getAmount(amount) && ValidateTransactions.isTransactionValid(Double.parseDouble(amount),
                            this.userCredentials.getUserAccount().getBalance(), this.userCredentials.getUserAccount().getAccountStatus())) {
                            this.userCredentials.getUserAccount().withdraw(Double.parseDouble(amount));
                            sendMessage("Your available balance is now: " + this.userCredentials.getUserAccount().getBalance());
                            this.userCredentials.getUserAccount().addToTransactionHistory("You have withdrawn " + 
                            amount + " from your account.");
                            isWithdrawalSuccessful = true;
                        }

                        if (!isWithdrawalSuccessful) {
                            sendMessage("The amount you are trying to withdraw either exceeds the amount you have or you have" +
                                " entered an invalid amount. Please try again.");
                            isWithdrawalSuccessful = true;
                        }
                    }
                }

                if (userInput.equals("3")) {
                    boolean isDepositSuccessful = false;
                    while (!isDepositSuccessful) {
                        sendMessage("Enter amount:");
                        String amount = listenForMessage();
                        if (getAmount(amount)) {
                            this.userCredentials.getUserAccount().deposit(Double.parseDouble(amount));
                            sendMessage("Your available balance is now: " + 
                            this.userCredentials.getUserAccount().getBalance());
                            this.userCredentials.getUserAccount().addToTransactionHistory("You have deposited " +
                                amount + " into your account.");
                            isDepositSuccessful = true;
                        }

                        if (!isDepositSuccessful) {
                            sendMessage("The amount you are trying to deposit is invalid. Please try again.");
                            isDepositSuccessful = true;
                        }
                    }
                }

                if (userInput.equals("4")) {
                    boolean isTransferSuccessful = false;
                    while (!isTransferSuccessful) {
                        sendMessage("Enter account number:");
                        String accountNumber = listenForMessage();
                        sendMessage("Enter Amount: ");
                        String amount = listenForMessage();
                        if (getAmount(amount)) {
                            for (ClientHandler user : userHandlers) {
                                if (user.userCredentials.getUserIdentifier().equals(accountNumber) &&
                                    ValidateTransactions.isTransactionValid( Double.parseDouble(amount),
                                    this.userCredentials.getUserAccount().getBalance(),
                                    this.userCredentials.getUserAccount().getAccountStatus())) {
                                    this.userCredentials.getUserAccount().withdraw(Double.parseDouble(amount));
                                    user.userCredentials.getUserAccount().deposit(Double.parseDouble(amount));
                                    user.userCredentials.getUserAccount().addToTransactionHistory(amount + " was transfered into your account");
                                    this.userCredentials.getUserAccount().addToTransactionHistory("You have transfered " +
                                        amount + " to account number " + accountNumber);
                                    isTransferSuccessful = true;
                                }
                            }
                        }
                        if (!isTransferSuccessful) {
                            sendMessage("Invalid Account number or amount. Please try again.");
                            isTransferSuccessful = true;
                        }
                    }
                }

                if (userInput.equals("5")) {
                    sendMessage("Your avialable balance is " + 
                        this.userCredentials.getUserAccount().getBalance());
                }

                if (userInput.equals("6")) {
                    sendMessage(this.userCredentials.getUserAccount().getTransactionHistory());
                }

                if (userInput.equals("7")) {
                    sendMessage("See you next time!");
                    if (!this.userStatus) {
                        ServerDatabaseHandler.insertAccountRecords(this.userCredentials.getUserAccount().getAccountId(),
                         this.userCredentials.getUserAccount().getBalance(), this.userCredentials.getUserAccount().getTransactionHistory());
                        System.out.println("Did we get here?");
                    } else {
                        ServerDatabaseHandler.updateAccountRecords(this.userCredentials.getUserAccount().getAccountId(),
                         this.userCredentials.getUserAccount().getBalance(), this.userCredentials.getUserAccount().getTransactionHistory());
                        System.out.println("Did we get here?");
                    }
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
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
        if (jsonObject.getString("user_status").equals("true")) {
            ServerDatabaseHandler.loadUserAcountRecords(this.userCredentials.getUserIdentifier(), this.userCredentials.getUserAccount());
            this.userStatus = true;
        }
        this.userStatus = false;
    }

    public String getUserDetails() {
        return "Your account details are:" + 
            "\nName:                     " + this.userCredentials.getUserName() + 
            "\nPhone number:             " + this.userCredentials.getUserPhoneNumber() + 
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
