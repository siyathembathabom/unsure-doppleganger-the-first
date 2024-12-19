package co.za.share;

public class Account {
    private UserCredentials accountHolder;
    private String accountHolderId;
    private double balance;
    private boolean isTheAccountActive;

    public Account(UserCredentials owner, String accountId) {
        this.accountHolder = owner;
        this.accountHolderId = accountId;
        this.balance = 0.00;
        this.isTheAccountActive = true;
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
        if (isTheAccountActive) {
            this.balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (isTheAccountActive) {
            this.balance -= amount;
        }
    }

    public void transferTo(double amount, UserCredentials targetUser) {
        withdraw(balance);
        targetUser.getUserAccount().deposit(amount);
    }
}
