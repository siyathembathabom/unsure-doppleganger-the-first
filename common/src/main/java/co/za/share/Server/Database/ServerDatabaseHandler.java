package co.za.share.Server.Database;

import java.sql.*;

import co.za.share.Server.Account.Account;

public class ServerDatabaseHandler {

    public static void insertAccountRecords(String accountID, double balance,
         String transactionHistory) {
        String url = "jdbc:sqlite:account.db";
        String insertSQL = "INSERT INTO account_records(account_holder_id, account_balance, transaction_history) VALUES(?, ?, ?)";
        

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setDouble(1, balance);
            pstmt.setString(2, transactionHistory);
            pstmt.setString(3, accountID);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAccountRecords(String accountID, double balance,
         String transactionHistory) {
        String url = "jdbc:sqlite:account.db";

        String querySQL = "UPDATE account_records SET account_balance = ?, transaction_history = ? WHERE account_holder_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
            pstmt.setDouble(1, balance);
            pstmt.setString(2, transactionHistory);
            pstmt.setString(3, accountID);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void loadUserAcountRecords(String accountID, Account account) {
        String url = "jdbc:sqlite:account.db";
        String querySQL = "SELECT * FROM account_records WHERE account_holder_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
            pstmt.setString(1, accountID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                account.setBalance(rs.getDouble("account_balance"));
                account.addToTransactionHistory(rs.getString("transaction_history"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
