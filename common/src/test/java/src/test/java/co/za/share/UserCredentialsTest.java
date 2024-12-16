package src.test.java.co.za.share;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import co.za.share.UserCredentials;

public class UserCredentialsTest {

    private UserCredentials user = new UserCredentials("#-195-7214");
    
    @Test
    public void testGetUserIdentifier() {
        int length = 10;
        String result = user.getUserIdentifier();
        assertEquals(length, result.length());
    }

    @Test
    public void testUniqueIdentifiersShouldBeDifferent() {
        String resultOne = user.getUserIdentifier();
        UserCredentials userTwo = new UserCredentials("#-195-4143");
        String resultTwo = userTwo.getUserIdentifier();
        assertNotEquals(resultOne, resultTwo);
    }

    @Test
    public void testGetUserNameShouldBeEqual() {
        String testString = "Jonathan";
        user.setUserName("Jonathan");
        assertEquals(testString, user.getUserName());
    }

    @Test
    public void testGetUserEmail() {
        String testString = "mahalaba@gmail.com";
        user.setUserEmail(testString);
        assertEquals(testString, user.getUserEmail());
    }

    @Test
    public void testGetUserPhoneNumber() {
        String testString = "+27679125701";
        user.setUserPhoneNumber(testString);
        assertEquals(testString, user.getUserPhoneNumber());
    }

    @Test
    public void testGetUserAccountBalance() {
        Double testAmount = 100.00;
        user.setUserAccountBalance(testAmount);
        Double delta = 0.01;
        assertEquals(testAmount, user.getUserAccountBalance(), delta);
    }
}
