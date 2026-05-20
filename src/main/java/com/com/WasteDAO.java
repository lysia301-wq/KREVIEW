package com.com;

// WasteDAO.java - Management logs waste
import java.sql.*;

public class WasteDAO {
    
    // Add waste entry
    public void addWasteEntry(String date, String mealType,
                               double totalKg, double wasteKg, 
                               int enteredBy) {
        String sql = "INSERT INTO waste_tracker (waste_date, meal_type," +
                     " total_prepared_kg, waste_kg, entered_by) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, date);
            ps.setString(2, mealType);
            ps.setDouble(3, totalKg);
            ps.setDouble(4, wasteKg);
            ps.setInt(5, enteredBy);
            ps.executeUpdate();
            System.out.println("Waste entry added!");
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}