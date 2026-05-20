package com.com;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewSuggestionsScreen extends JFrame {

    public ViewSuggestionsScreen() {
        setTitle("Student Suggestions");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"Student", "Dish", "Meal Type", 
                            "Description", "Status"};
        Object[][] data = getData();

        JTable table = new JTable(data, columns);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }

    Object[][] getData() {
        String sql = "SELECT u.name, s.suggested_item, " +
                     "s.meal_type, s.description, s.status " +
                     "FROM suggestions s JOIN users u " +
                     "ON s.student_id = u.user_id " +
                     "ORDER BY s.submitted_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            java.util.List<Object[]> rows = new java.util.ArrayList<>();
            while (rs.next()) {
                rows.add(new Object[]{
                    rs.getString("name"),
                    rs.getString("suggested_item"),
                    rs.getString("meal_type"),
                    rs.getString("description"),
                    rs.getString("status")
                });
            }
            return rows.toArray(new Object[0][]);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return new Object[0][0];
        }
    }
}