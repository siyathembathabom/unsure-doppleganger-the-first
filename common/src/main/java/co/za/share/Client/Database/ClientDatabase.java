package co.za.share.Client.Database;

import java.sql.*;

public class ClientDatabase {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:user.db";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS user_records ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "password_hash TEXT NOT NULL, "
                + "user_identifier TEXT NOT NULL, "
                + "name TEXT NOT NULL, "
                + "email TEXT NOT NULL, "
                + "number TEXT NOT NULL);";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();) {
            stmt.execute(createTableSQL);
            System.out.println("Database and table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
