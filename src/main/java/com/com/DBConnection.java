// DBConnection.java - Database Connection Class
package com.com;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static final String URL = "jdbc:mysql://localhost:3306/trafficdb";
    static final String USER = "root";
    static final String PASSWORD = "lysia"; // change this
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected!");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
        return conn;
    }
}