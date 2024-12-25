package src.test.java.co.za.share.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import co.za.share.Server.Options.Account.Account;
import co.za.share.Server.Options.User.UniqueIdentifierCreator;
import co.za.share.Server.Options.User.UserCredentials;

public class AccountTest {

    private Account newAccount() {
        final UserCredentials user = new UserCredentials();
        user.setUserName("John Doe");
        final UniqueIdentifierCreator uniqueIdentifierCreator = new UniqueIdentifierCreator();
        final String id = uniqueIdentifierCreator.createUserID();

        return new Account(user, id);
    }

    @Test
    public void testGetAccountIdShouldContainSpecialSequence() {
        String userId = newAccount().getAccountId();
        assertTrue(userId.contains("#-195-"));
    }

    @Test
    public void testGetUser() {
       assertTrue(newAccount().getAccountHolder() instanceof UserCredentials);         
       assertTrue(newAccount().getAccountHolder().getUserName().equals("John Doe"));
    }

    @Test
    public void testGetAccountBalance() {
        assertEquals(0.00, newAccount().getBalance(), 0.01);
    }

    @Test
    public void testAccountShoulBeActiveIfUserCreatesANewAccount() {
        assertTrue(newAccount().getAccountStatus());
    }

    @Test
    public void testAccountShouldNotBeActiveIfUserDeactivatesAccount() {
        Account account = newAccount();
        account.deactivateAccount();
        boolean isActive = account.getAccountStatus();
        assertFalse(isActive);
    }
}
