package co.za.share.Server.Options.Account;

public class ValidateTransactions {
    
    public static boolean isDepositValid(double value, boolean isAccountActive) {
        if (value > 0 && isAccountActive) {
            return true;
        }
        return false;
    }

    public static boolean isTransactionValid(double amountToTransfer, double amountAvailable,
        boolean isAccountActive) {
            if ((amountAvailable - amountToTransfer) >= 0 && isAccountActive) {
                return true;
            }
            return false;
        }
}
