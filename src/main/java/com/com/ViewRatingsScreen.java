package com.com;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class ViewRatingsScreen extends JFrame {

    public ViewRatingsScreen() {
        setTitle("Today's Ratings");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"Item", "Meal", "Avg Stars", 
                            "Total Ratings", "Feedback"};
        Object[][] data = getData();

        JTable table = new JTable(data, columns);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }

    Object[][] getData() {
        String today = LocalDate.now().toString();
        String sql = "SELECT m.item_name, m.meal_type, " +
                     "AVG(r.stars) as avg_stars, " +
                     "COUNT(r.rating_id) as total, " +
                     "GROUP_CONCAT(r.feedback SEPARATOR ' | ') " +
                     "as feedbacks " +
                     "FROM menu m LEFT JOIN ratings r " +
                     "ON m.menu_id = r.menu_id " +
                     "WHERE m.menu_date = ? " +
                     "GROUP BY m.menu_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, today);
            ResultSet rs = ps.executeQuery();
            java.util.List<Object[]> rows = new java.util.ArrayList<>();
            while (rs.next()) {
                rows.add(new Object[]{
                    rs.getString("item_name"),
                    rs.getString("meal_type"),
                    String.format("%.1f", rs.getDouble("avg_stars")),
                    rs.getInt("total"),
                    rs.getString("feedbacks")
                });
            }
            return rows.toArray(new Object[0][]);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return new Object[0][0];
        }
    }
}