package co.za.share.Client.Login;

import java.util.Scanner;
import co.za.share.Client.User.UserCredentials;

public class Login {
    private Scanner scanner;
    private UserCredentials user;

    public Login(Scanner scanner, UserCredentials user) {
        this.scanner = scanner;
        this.user = user;
    }

    public void setUserName(String name) {
        this.user.setUserName(name);
    }

    public void setUserEmail(String email) {
        this.user.setUserEmail(email);
    }

    public void setUserPhoneNumber(String phoneNumber) {
        this.setUserPhoneNumber(phoneNumber);
    }

    public void setUserIdentifier(String identifier) {
        this.user.setUserIdentifier(identifier);
    }

    // public UserCredentials login() {
    //     this.scanner.
    // }
}
