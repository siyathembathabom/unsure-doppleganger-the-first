package co.za.share.Client.SignUp;

import java.util.Scanner;

import co.za.share.Client.User.UserCredentials;
import co.za.share.Client.Database.ClientDatabaseHandler;

public class SignUp {
    private Scanner scanner;
    private UserCredentials userCredentials;
    private ValidateSignUp validate;
    private String password;
    
    public SignUp(Scanner scanner, UserCredentials user) {
        this.scanner = scanner;
        this.userCredentials = user;
        this.validate = new ValidateSignUp();
    }

    public String createName() {
        System.out.print("Enter your name: ");
        String name = this.scanner.nextLine();
        this.userCredentials.setUserName(name);
        return "\n";
    }

    public String createEmail() {
        System.out.print("Enter you email address: ");
        String email = this.scanner.nextLine();

        while (!this.validate.isEmailValid(email)) {
            System.out.print("Invalid email!\nEnter your email address: ");
            email = this.scanner.nextLine();
        }
        this.userCredentials.setUserEmail(email);
        return "\n";
    }

    public String createNumber() {
        System.out.print("Enter your phone number: ");
        String number = this.scanner.nextLine();
        while (!this.validate.isPhoneNumberValid(number)) {
            System.out.print("Invalid number!\nEnter your phone number: ");
            number = this.scanner.nextLine();
        }
        this.userCredentials.setUserPhoneNumber(number);
        return "\n";
    }

    public void createUniqueIdentifier(String id) {
        this.userCredentials.setUserIdentifier(id);
    }

    public void createPassword() {
        while (true) {
            System.out.print("Enter your password: ");
            this.password = this.scanner.nextLine();

            System.out.print("Re-enter password: ");
            String passwordTheSecond = this.scanner.nextLine();

            if (password.equals(passwordTheSecond)) {
                break;
            }
            System.out.println("Passwords do not match. Please try again.");
        }
    }

    public void signUp(String id) {
        createName();
        createEmail();
        createNumber();
        createUniqueIdentifier(id);
        createPassword();
        ClientDatabaseHandler.registerUser(this.password, this.userCredentials.getUserIdentifier(),
             this.userCredentials.getUserName(), this.userCredentials.getUserEmail(),
             this.userCredentials.getUserPhoneNumber());
    }
}
