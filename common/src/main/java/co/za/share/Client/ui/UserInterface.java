package co.za.share.Client.ui;

import java.util.Scanner;

import co.za.share.Help;
import co.za.share.Client.User.UniqueIdentifierCreator;
import co.za.share.Client.User.UserCredentials;

public class UserInterface {
    private Scanner scanner;
    private UserCredentials userCredentials;
    private SignUp signUp;
    private UniqueIdentifierCreator uniqueIdentifierCreator;
    private String id = "";

    public UserInterface(Scanner scanner) {
            this.scanner = scanner;
            this.userCredentials = new UserCredentials();
            this.signUp = new SignUp(scanner, userCredentials);
            this.uniqueIdentifierCreator = new UniqueIdentifierCreator();
            this.id = uniqueIdentifierCreator.createUserID();
        }

    public void welcome() {
        System.out.println("Welcome!");
        signUp.createName();
        signUp.createEmail();
        signUp.createNumber();
        signUp.createUniqueIdentifier(this.id);
        signUp.createAccount(this.id);
        start();
    }

    public void start() {
        System.out.println(Help.help());

        while (true) {
            System.out.print("Enter an option: ");
            String userInput = this.scanner.nextLine();

            if (userInput.equalsIgnoreCase("0")) {
                System.out.println("See you next time!");
                break;
            }

            if (userInput.equalsIgnoreCase("1")) {
                System.out.println(Help.help());
            }

            if (userInput.equalsIgnoreCase("2")) {
                System.out.println("Your account details are:");
                System.out.println("Name:                     " + this.userCredentials.getUserName());
                System.out.println("Phone number:             " + this.userCredentials.getUserPhoneNumber());
                System.out.println("Email:                    " + this.userCredentials.getUserEmail());
                System.out.println("Account number:           " + this.userCredentials.getUserIdentifier());
                System.out.println("Balance                   " + this.userCredentials.getUserAccount().getBalance());
            }

            if (userInput.equalsIgnoreCase("3")) {
                this.userCredentials.getUserAccount().withdraw(getAmount(this.scanner));
                System.out.println("Your balance is now: " + this.userCredentials.getUserAccount().getBalance());
            }

            if (userInput.equalsIgnoreCase("4")) {
                this.userCredentials.getUserAccount().deposit(getAmount(this.scanner));
                System.out.println("Your balance is now: " + this.userCredentials.getUserAccount().getBalance());
            }

            if (userInput.equalsIgnoreCase("5")) {
                this.userCredentials.getUserAccount().withdraw(getAmount(this.scanner));
            }

            if (userInput.equalsIgnoreCase("6")) {
                System.out.println("Your avialable balance is " + 
                    this.userCredentials.getUserAccount().getBalance());
            }
        }
    }

    public double getAmount(Scanner scanner) {
        double amount = 0.00;
        while (true) {
            System.out.print("Enter the amount: ");
            String input = scanner.nextLine();
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Your amount is invalid please try again.");
                continue;
            }
            return amount;
        }
    }
}
