package co.za.share.Account;

public class ValidateTransactions {
    
    public boolean isDepositValid(double value, boolean isAccountActive) {
        if (value > 0 && isAccountActive) {
            return true;
        }
        return false;
    }

    public boolean isWithdrawalValid(double value, double amountAvailable,
        boolean isAccountActive) {
            if ((amountAvailable - value) >= 0 && isAccountActive) {
                return true;
            }
            return false;
        }
}
