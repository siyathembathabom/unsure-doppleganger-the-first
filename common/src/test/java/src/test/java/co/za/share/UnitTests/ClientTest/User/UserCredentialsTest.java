package src.test.java.co.za.share.UnitTests.ClientTest.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import src.test.java.co.za.share.UnitTests.ServerTest.Account.User.UserCredentials;

public class UserCredentialsTest {

    private UserCredentials user = new UserCredentials();
    
    @Test
    public void testGetUserIdentifierLength() {
        int length = 10;
        user.setUserIdentifier("#-195-4143");
        String result = user.getUserIdentifier();
        assertEquals(length, result.length());
    }

    @Test
    public void testUniqueIdentifiersShouldBeDifferent() {
        user.setUserIdentifier("#-195-4143");
        String resultOne = user.getUserIdentifier();

        UserCredentials userTwo = new UserCredentials();
        userTwo.setUserIdentifier("#-195-6190");
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

}
