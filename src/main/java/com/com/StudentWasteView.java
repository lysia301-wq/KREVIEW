package com.com;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentWasteView extends JFrame {

    public StudentWasteView() {
        setTitle("Waste Report");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"Date", "Meal", 
                            "Total Prepared (kg)", 
                            "Waste (kg)", "Waste %"};
        Object[][] data = getData();

        JTable table = new JTable(data, columns);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }

    Object[][] getData() {
        String sql = "SELECT waste_date, meal_type, " +
                     "total_prepared_kg, waste_kg " +
                     "FROM waste_tracker " +
                     "ORDER BY waste_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            java.util.List<Object[]> rows = 
                new java.util.ArrayList<>();
            while (rs.next()) {
                double total = rs.getDouble("total_prepared_kg");
                double waste = rs.getDouble("waste_kg");
                double percent = (waste / total) * 100;
                rows.add(new Object[]{
                    rs.getString("waste_date"),
                    rs.getString("meal_type"),
                    total,
                    waste,
                    String.format("%.1f%%", percent)
                });
            }
            return rows.toArray(new Object[0][]);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return new Object[0][0];
        }
    }
}