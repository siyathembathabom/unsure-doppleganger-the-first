package co.za.share;

public class Account {
    private UserCredentials accountHolder;
    private ValidateTransactions validTransactions;
    private String accountHolderId;
    private double balance;
    private boolean isTheAccountActive;

    public Account(UserCredentials owner, String accountId) {
        this.accountHolder = owner;
        this.validTransactions = new ValidateTransactions();
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
        if (validTransactions.isDepositValid(amount, isTheAccountActive)) {
            this.balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (validTransactions.isWithdrawalValid(amount, this.balance, isTheAccountActive)) {
            this.balance -= amount;
        }
    }

    public void transferTo(double amount, UserCredentials targetUser) {
        withdraw(this.balance);
        if (validTransactions.isWithdrawalValid(amount, this.balance, isTheAccountActive)) {
            targetUser.getUserAccount().deposit(amount);
        }
    }
}
