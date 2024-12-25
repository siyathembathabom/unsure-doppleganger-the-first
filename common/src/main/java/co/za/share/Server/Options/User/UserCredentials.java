package co.za.share.Server.Options.User;

import co.za.share.Server.Options.Account.Account;

public class UserCredentials {

    private String uniqueIdentifier;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private Account userAccount;

    public void setUserIdentifier(String id) {
        this.uniqueIdentifier = id;
    }

    public String getUserIdentifier() {
        return uniqueIdentifier;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserPhoneNumber(String number) {
        this.userPhoneNumber = number;
    }

    public String getUserPhoneNumber() {
        return this.userPhoneNumber;
    }

    public void setUserAccount(Account account) {
        this.userAccount = account;
    }

    public Account getUserAccount() {
        return this.userAccount;
    }
}
