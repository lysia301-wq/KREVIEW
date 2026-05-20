package com.com;

// RatingDAO.java - Students submit ratings
import java.sql.*;

public class RatingDAO {
    
    // Submit a rating
    public void submitRating(int menuId, int studentId, 
                              int stars, String feedback, 
                              String taggedIssue) {
        String sql = "INSERT INTO ratings (menu_id, student_id, " +
                     "stars, feedback, tagged_issue) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, menuId);
            ps.setInt(2, studentId);
            ps.setInt(3, stars);
            ps.setString(4, feedback);
            ps.setString(5, taggedIssue);
            ps.executeUpdate();
            System.out.println("Rating submitted!");
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Get average rating for a menu item
    public void getAverageRating(int menuId) {
        String sql = "SELECT AVG(stars) as avg_rating, " +
                     "COUNT(*) as total_ratings " +
                     "FROM ratings WHERE menu_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, menuId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                System.out.println("Average Rating: " + 
                    rs.getDouble("avg_rating"));
                System.out.println("Total Ratings: " + 
                    rs.getInt("total_ratings"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}