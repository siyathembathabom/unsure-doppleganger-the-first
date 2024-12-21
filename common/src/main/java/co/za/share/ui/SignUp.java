package co.za.share.ui;

import java.util.Scanner;

import co.za.share.Account.Account;
import co.za.share.Client.UserCredentials;

public class SignUp {
    private Scanner scanner;
    private UserCredentials userCredentials;
    private Account account;
    private ValidateSignUp validate;
    
    public SignUp(Scanner scanner, UserCredentials userCredentials) {
        this.scanner = scanner;
        this.userCredentials = userCredentials;
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

    public void createAccount(String id) {
        this.account = new Account(this.userCredentials, id);
    this.userCredentials.setUserAccount(this.account);
    }
}
