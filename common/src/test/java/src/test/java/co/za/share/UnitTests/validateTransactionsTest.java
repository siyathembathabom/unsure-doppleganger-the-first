package src.test.java.co.za.share.UnitTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import co.za.share.Server.UserDetails.Account.ValidateTransactions;

public class ValidateTransactionsTest {

    public ValidateTransactions validate = new ValidateTransactions();

    @Test
    public void testDepositShouldBeValid() {
        assertTrue(ValidateTransactions.isDepositValid(100.00, true));
    }

    @Test
    public void testDepositShouldNotBeValidForNegativeValues() {
        assertFalse(ValidateTransactions.isDepositValid(-1.00, true));
    }

    @Test
    public void testDepositShouldNotBeValidForInactiveAccount() {
        assertFalse(ValidateTransactions.isDepositValid(100.00, false));
    }

    @Test
    public void testWithdrawalShouldBeValid() {
        assertTrue(ValidateTransactions.isTransactionValid(100.00, 150.00, true));
    }

    @Test
    public void testWithdrawalShouldBeInvalidForNegativeValues() {
        assertFalse(ValidateTransactions.isTransactionValid(200, 100.00, true));
    }

    @Test
    public void testWithdrawalShouldBeInvalidForInactiveAccount() {
        assertFalse(ValidateTransactions.isTransactionValid(10, 0, false));
    }
}
