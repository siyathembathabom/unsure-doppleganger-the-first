package src.test.java.co.za.share.UnitTests.ServerTest.Account.User;

import org.json.JSONObject;

public class UserDetailsToSend {

    public static JSONObject createUserCredentialsJSONObject(UserCredentials userCredentials) {
        JSONObject userObject = new JSONObject();

        userObject.put("name", userCredentials.getUserName());
        userObject.put("email", userCredentials.getUserEmail());
        userObject.put("number", userCredentials.getUserPhoneNumber());
        userObject.put("unique identifier", userCredentials.getUserIdentifier());

        return userObject;
    }
}
