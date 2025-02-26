package co.za.share.Client.Database;

import java.sql.*;

import co.za.share.Client.SignUp.PasswordUtils;

public class ClientDatabaseHandler {

    public static void registerUser(String password,String uniqueIdentifier,
         String name, String email, String number) {
        String hashPassword = PasswordUtils.hashPassword(password);

        String url = "jdbc:sqlite:user.db";

        String sql = "INSERT INTO user_records(password_hash, user_identifier, name, email, number) VALUES(?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
}
