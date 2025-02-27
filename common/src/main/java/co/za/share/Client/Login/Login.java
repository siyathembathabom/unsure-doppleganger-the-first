package co.za.share.Client.Login;

import java.util.Scanner;

import co.za.share.Client.Database.ClientDatabaseHandler;
import co.za.share.Client.User.UserCredentials;

public class Login {
    private Scanner scanner;
    private UserCredentials user;

    public Login(Scanner scanner, UserCredentials user) {
        this.scanner = scanner;
        this.user = user;
    }

    public UserCredentials login() {
        System.out.print("Enter your username: ");
        String username = this.scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = this.scanner.nextLine();

        int count = 5;
        while (count > 0) {
            if (ClientDatabaseHandler.loginUser(username, password, user)) {
                return user;
            }
        }
        return user;
    }
}
