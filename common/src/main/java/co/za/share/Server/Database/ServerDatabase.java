package co.za.share.Server.Database;

import java.sql.*;

public class ServerDatabase {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:account.db";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS account_records ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "account_holder_id, "
            + "account_balance REAL NOT NULL, "
            + "transaction_history TEXT NOT NULL);";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
