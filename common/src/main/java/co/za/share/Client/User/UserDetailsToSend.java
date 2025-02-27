package co.za.share.Client.User;

import org.json.JSONObject;

public class UserDetailsToSend {

    public static JSONObject createNewUserCredentialsJSONObject(UserCredentials user, String userStatus) {
        JSONObject userObject = new JSONObject();

        userObject.put("name", user.getUserName());
        userObject.put("email", user.getUserEmail());
        userObject.put("number", user.getUserPhoneNumber());
        userObject.put("unique identifier", user.getUserIdentifier());
        userObject.put("user_status", userStatus);

        return userObject;
    }
}
