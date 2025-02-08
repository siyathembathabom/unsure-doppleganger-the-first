package co.za.share.Server.UserDetails.Account;

public class ValidateTransactions {
    
    public static boolean isDepositValid(double amountToDeposit, boolean isAccountActive) {
        if (amountToDeposit > 0 && isAccountActive) {
            return true;
        }
        return false;
    }

    public static boolean isTransactionValid(double amountToTransfer, double amountAvailable,
        boolean isAccountActive) {
            if (amountToTransfer > 0 && (amountAvailable - amountToTransfer) >= 0 && isAccountActive) {
                return true;
            }
            return false;
        }
}
