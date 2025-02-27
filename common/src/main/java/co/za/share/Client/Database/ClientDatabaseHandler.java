package co.za.share.Client.Database;

import java.sql.*;

import co.za.share.Client.User.UserCredentials;

public class ClientDatabaseHandler {

    public static void registerUser(String password, String uniqueIdentifier,
         String name, String email, String number) {
        String hashPassword = PasswordUtils.hashPassword(password);

        String url = "jdbc:sqlite:user.db";

        String querySQL = "INSERT INTO user_records(password_hash, user_identifier, name, email, number) VALUES(?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
            pstmt.setString(1, hashPassword);
            pstmt.setString(2, uniqueIdentifier);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.setString(5, number);
            pstmt.executeUpdate();
            System.out.println(name + ", you have now successfully signed up!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean loginUser(String username, String password, UserCredentials user) {
        String url = "jdbc:sqlite:user.db";
        String querySQL = "SELECT * FROM user_records WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                if (PasswordUtils.checkPassword(password, storedHash)) {
                    System.out.println("Password found!");
                    user.setUserIdentifier(rs.getString("user_identifier"));
                    user.setUserName(username);
                    user.setUserEmail(rs.getString("email"));
                    user.setUserPhoneNumber(rs.getString("number"));

                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
