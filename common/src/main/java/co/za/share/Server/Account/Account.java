package co.za.share.Server.Account;

import java.util.ArrayList;

import co.za.share.Server.UserDetails.UserCredentials;

public class Account {
    private UserCredentials accountHolder;
    private String accountHolderId;
    private double balance;
    private boolean isTheAccountActive;
    private ArrayList<String> transactionHistoryList;

    public Account(UserCredentials owner, String accountId) {
        this.accountHolder = owner;
        this.accountHolderId = accountId;
        this.balance = 0.00;
        this.isTheAccountActive = true;
        this.transactionHistoryList = new ArrayList<>();
    }

    public UserCredentials getAccountHolder() {
        return this.accountHolder;
    }

    public String getAccountId() {
        return this.accountHolderId;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public void activateAccount() {
        this.isTheAccountActive = true;
    }

    public void deactivateAccount() {
        this.isTheAccountActive = false;
    }

    public boolean getAccountStatus() {
        return this.isTheAccountActive;
    }

    public void deposit(double amount) {
        if (ValidateTransactions.isDepositValid(amount, isTheAccountActive)) {
            this.balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (ValidateTransactions.isTransactionValid(amount, this.balance, isTheAccountActive)) {
            this.balance -= amount;   
        }
    }

    public void addToTransactionHistory(String transaction) {
        this.transactionHistoryList.add(transaction);
    }

    public String getTransactionHistory() {
        String transactionHistory = "";
        for (String transaction : transactionHistoryList) {
            if (!transaction.isEmpty()) {
                transactionHistory += transaction + "\n";
            }
        }
        if (transactionHistory.isEmpty()) {
            return "You have yet to make a transaction.";
        }
        return transactionHistory;
    }
}
