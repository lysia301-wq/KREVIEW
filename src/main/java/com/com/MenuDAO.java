package com.com;

// MenuDAO.java - Management adds daily menu
import java.sql.*;

public class MenuDAO {
    
    // Add a menu item
    public void addMenuItem(String date, String mealType, 
                             String itemName, int createdBy) {
        String sql = "INSERT INTO menu (menu_date, meal_type, " +
                     "item_name, created_by) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, date);       // e.g. "2026-05-01"
            ps.setString(2, mealType);   // e.g. "breakfast"
            ps.setString(3, itemName);   // e.g. "Pongal"
            ps.setInt(4, createdBy);     // management user_id
            ps.executeUpdate();
            System.out.println("Menu item added!");
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Get today's menu by meal type
    public void getMenuByDate(String date, String mealType) {
        String sql = "SELECT * FROM menu WHERE menu_date = ? " +
                     "AND meal_type = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, date);
            ps.setString(2, mealType);
            ResultSet rs = ps.executeQuery();
            
            System.out.println("Menu for " + mealType + " on " + date);
            while (rs.next()) {
                System.out.println("- " + rs.getString("item_name"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
