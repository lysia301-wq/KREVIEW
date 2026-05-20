package com.com;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SuggestionScreen extends JFrame {
    int userId;

    public SuggestionScreen(int userId) {
        this.userId = userId;
        setTitle("Suggest a Dish");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);

        g.gridx = 0; g.gridy = 0;
        add(new JLabel("Dish Name:"), g);
        JTextField dishField = new JTextField(15);
        g.gridx = 1; add(dishField, g);

        g.gridx = 0; g.gridy = 1;
        add(new JLabel("Meal Type:"), g);
        JComboBox<String> mealType = new JComboBox<>(
            new String[]{"breakfast","lunch","snacks","dinner"});
        g.gridx = 1; add(mealType, g);

        g.gridx = 0; g.gridy = 2;
        add(new JLabel("Description:"), g);
        JTextField descField = new JTextField(15);
        g.gridx = 1; add(descField, g);

        JButton submitBtn = new JButton("Submit");
        g.gridx = 0; g.gridy = 3; g.gridwidth = 2;
        add(submitBtn, g);

        submitBtn.addActionListener(e -> {
            submitSuggestion(dishField.getText(),
                mealType.getSelectedItem().toString(),
                descField.getText());
        });

        setVisible(true);
    }

    void submitSuggestion(String dish, String meal, String desc) {
        String sql = "INSERT INTO suggestions (student_id, " +
                     "suggested_item, meal_type, description) " +
                     "VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, dish);
            ps.setString(3, meal);
            ps.setString(4, desc);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, 
                "Suggestion submitted!");
            dispose();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}