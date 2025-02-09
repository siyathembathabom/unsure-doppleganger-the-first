package co.za.share.Server;

public class Menu {

    public static String menu() {
        return """
                Choose a number to perform an action. Here are the options:
                1 - Get account details
                2 - Withdraw money
                3 - Deposit money
                4 - Transfer money
                5 - Get Balance
                6 - Get Transaction History
                7 - Exit
                """;
                // Add transaction history in the future
    }
}
