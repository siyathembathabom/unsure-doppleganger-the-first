package src.test.java.co.za.share.UnitTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import co.za.share.ValidateTransactions;

public class ValidateTransactionsTest {

    public ValidateTransactions validate = new ValidateTransactions();

    @Test
    public void testDepositShouldBeValid() {
        assertTrue(validate.isDepositValid(100.00, true));
    }

    @Test
    public void testDepositShouldNotBeValidForNegativeValues() {
        assertFalse(validate.isDepositValid(-1.00, true));
    }

    @Test
    public void testDepositShouldNotBeValidForInactiveAccount() {
        assertFalse(validate.isDepositValid(100.00, false));
    }

    @Test
    public void testWithdrawalShouldBeValid() {
        assertTrue(validate.isWithdrawalValid(100.00, 150.00, true));
    }

    @Test
    public void testWithdrawalShouldBeInvalidForNegativeValues() {
        assertFalse(validate.isWithdrawalValid(200, 100.00, true));
    }

    @Test
    public void testWithdrawalShouldBeInvalidForInactiveAccount() {
        assertFalse(validate.isWithdrawalValid(10, 0, false));
    }
}
