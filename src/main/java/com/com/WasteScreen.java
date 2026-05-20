package com.com;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class WasteScreen extends JFrame {
    int userId;

    public WasteScreen(int userId) {
        this.userId = userId;
        setTitle("Enter Waste Data");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);

        g.gridx = 0; g.gridy = 0;
        add(new JLabel("Meal Type:"), g);
        JComboBox<String> mealType = new JComboBox<>(
            new String[]{"breakfast","lunch","snacks","dinner"});
        g.gridx = 1; add(mealType, g);

        g.gridx = 0; g.gridy = 1;
        add(new JLabel("Total Prepared (kg):"), g);
        JTextField totalField = new JTextField(10);
        g.gridx = 1; add(totalField, g);

        g.gridx = 0; g.gridy = 2;
        add(new JLabel("Waste (kg):"), g);
        JTextField wasteField = new JTextField(10);
        g.gridx = 1; add(wasteField, g);

        JButton submitBtn = new JButton("Submit");
        g.gridx = 0; g.gridy = 3; g.gridwidth = 2;
        add(submitBtn, g);

        submitBtn.addActionListener(e -> {
            try {
                double total = Double.parseDouble(
                    totalField.getText());
                double waste = Double.parseDouble(
                    wasteField.getText());
                saveWaste(mealType.getSelectedItem()
                    .toString(), total, waste);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers!");
            }
        });

        setVisible(true);
    }

    void saveWaste(String meal, double total, double waste) {
        String today = LocalDate.now().toString();
        String sql = "INSERT INTO waste_tracker (waste_date, " +
                     "meal_type, total_prepared_kg, waste_kg, " +
                     "entered_by) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, today);
            ps.setString(2, meal);
            ps.setDouble(3, total);
            ps.setDouble(4, waste);
            ps.setInt(5, userId);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, 
                "Waste data saved!");
            dispose();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}