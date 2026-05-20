package com.com;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class AddMenuScreen extends JFrame {
    int userId;

    public AddMenuScreen(int userId) {
        this.userId = userId;
        setTitle("Add Today's Menu");
        setSize(400, 350);
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
        add(new JLabel("Item Name:"), g);
        JTextField itemField = new JTextField(15);
        g.gridx = 1; add(itemField, g);

        JButton addBtn = new JButton("Add Item");
        g.gridx = 0; g.gridy = 2; g.gridwidth = 2;
        add(addBtn, g);

        JTextArea addedItems = new JTextArea(8, 25);
        addedItems.setEditable(false);
        g.gridy = 3;
        add(new JScrollPane(addedItems), g);

        addBtn.addActionListener(e -> {
            String item = itemField.getText().trim();
            String meal = mealType.getSelectedItem().toString();
            if (!item.isEmpty()) {
                addMenuItem(meal, item);
                addedItems.append(meal + ": " + item + "\n");
                itemField.setText("");
            }
        });

        setVisible(true);
    }

    void addMenuItem(String mealType, String itemName) {
        String today = LocalDate.now().toString();
        String sql = "INSERT INTO menu (menu_date, meal_type, " +
                     "item_name, created_by) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, today);
            ps.setString(2, mealType);
            ps.setString(3, itemName);
            ps.setInt(4, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}