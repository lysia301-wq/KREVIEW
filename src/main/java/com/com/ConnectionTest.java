package com.com;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "lysia";

        System.out.println("Attempting to connect...");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("SUCCESS!");
        } catch (SQLException e) {
            System.out.println("FAILED: " + e.getMessage());
        }
    }
}
