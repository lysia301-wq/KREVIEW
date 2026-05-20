package com.com;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTutorial {

    // Database details
    static final String DB_URL = "jdbc:mysql://localhost:3306/school_db";
    static final String USER = "root";
    static final String PASS = "lysia";  // change if your password is different

    public static void main(String[] args) {

        // SQL query
        String query = "SELECT id, name, age, grade FROM students WHERE age > ?";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(query)
        ) {

            System.out.println("Connected to the database successfully!");

            // set value for '?'
            pstmt.setInt(1, 18);

            // execute query
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- Student Records ---");

            // read data
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String grade = rs.getString("grade");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade);
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}