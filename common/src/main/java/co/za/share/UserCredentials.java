package co.za.share;

public class UserCredentials {

    private String uniqueIdentifier;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private Double AccountBalance;

    public UserCredentials() {
        this.AccountBalance = 0.00;
    }

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

    public void setUserAccountBalance(Double amount) {
        this.AccountBalance = amount;
    }

    public Double getUserAccountBalance() {
        return this.AccountBalance;
    }
}
