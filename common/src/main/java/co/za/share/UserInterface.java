package co.za.share;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private UserCredentials userCredentials;
    private Account account;
    private UniqueIdentifierCreator uniqueIdentifierCreator;

    public UserInterface(Scanner scanner) {
            this.scanner = scanner;
            this.userCredentials = new UserCredentials();
            this.uniqueIdentifierCreator = new UniqueIdentifierCreator();
        }

    public void welcome() {
        System.out.println("Welcome!");
        createName();
        createEmail();
        createNumber();
        String id = uniqueIdentifierCreator.createUserID();
        createUniqueIdentifier(id);
        createAccount(id);
        start();
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
        this.userCredentials.setUserEmail(email);
        return "\n";
    }

    public String createNumber() {
        System.out.print("Enter your phone number: ");
        String number = this.scanner.nextLine();
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
