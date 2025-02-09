package src.test.java.co.za.share.UnitTests.Server.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import co.za.share.Server.UserDetails.User.UniqueIdentifierCreator;

public class UniqueIdentifierCreatorTest {

    @Test
    public void testNoUniqueIDShouldBeTheSame() {
        UniqueIdentifierCreator uniqueIdentifierCreator1 = new UniqueIdentifierCreator();
        UniqueIdentifierCreator uniqueIdentifierCreator2 = new UniqueIdentifierCreator();
        assertNotEquals(uniqueIdentifierCreator1.createUserID(), 
            uniqueIdentifierCreator2.createUserID());
    }

    @Test
    public void testContainsIdentifierKey() {
        UniqueIdentifierCreator uniqueIdentifierCreator = new UniqueIdentifierCreator();
        String userID = uniqueIdentifierCreator.createUserID();
        assertTrue(userID.contains("#-195-"));
    }

    @Test
    public void testUserIDLengthShouldBeEqual() {
        UniqueIdentifierCreator uniqueIdentifierCreator = new UniqueIdentifierCreator();
        String userID = uniqueIdentifierCreator.createUserID();
        assertEquals(10, userID.length());
    }
}
