package com.com;

// SuggestionDAO.java - Students submit suggestions
import java.sql.*;

public class SuggestionDAO {
    
    // Submit suggestion
    public void submitSuggestion(int studentId, String item,
                                  String mealType, String description) {
        String sql = "INSERT INTO suggestions (student_id, " +
                     "suggested_item, meal_type, description) " +
                     "VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, studentId);
            ps.setString(2, item);
            ps.setString(3, mealType);
            ps.setString(4, description);
            ps.executeUpdate();
            System.out.println("Suggestion submitted!");
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
