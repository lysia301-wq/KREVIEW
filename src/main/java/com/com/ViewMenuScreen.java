package com.com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class ViewMenuScreen extends JFrame {
    int userId;

    public ViewMenuScreen(int userId) {
        this.userId = userId;
        KReviewTheme.setupFrame(this, 
            "KREVIEW - Today's Menu", 650, 600);
        setLayout(new BorderLayout());
        getContentPane().setBackground(KReviewTheme.LIGHT_BG);

        add(KReviewTheme.headerPanel("Today's Menu"), 
            BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Arial", Font.BOLD, 13));
        tabs.setBackground(KReviewTheme.WHITE);
        tabs.addTab("Breakfast", createMealPanel("breakfast"));
        tabs.addTab("Lunch", createMealPanel("lunch"));
        tabs.addTab("Snacks", createMealPanel("snacks"));
        tabs.addTab("Dinner", createMealPanel("dinner"));

        add(tabs, BorderLayout.CENTER);
        add(KReviewTheme.footerPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    JPanel createMealPanel(String mealType) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(KReviewTheme.LIGHT_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(
            15, 20, 15, 20));

        String today = LocalDate.now().toString();
        String sql = "SELECT * FROM menu WHERE " +
                     "menu_date = ? AND meal_type = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, today);
            ps.setString(2, mealType);
            ResultSet rs = ps.executeQuery();

            boolean hasItems = false;
            while (rs.next()) {
                hasItems = true;
                int menuId = rs.getInt("menu_id");
                String itemName = rs.getString("item_name");

                // Card for each item
                JPanel itemCard = new JPanel();
                itemCard.setLayout(new GridBagLayout());
                itemCard.setBackground(KReviewTheme.WHITE);
                itemCard.setBorder(
                    BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                            new Color(220, 230, 245), 1),
                        BorderFactory.createEmptyBorder(
                            12, 15, 12, 15)));
                itemCard.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 180));

                GridBagConstraints g = new GridBagConstraints();
                g.insets = new Insets(5, 5, 5, 5);
                g.fill = GridBagConstraints.HORIZONTAL;

                // Item name
                JLabel nameLabel = new JLabel(itemName);
                nameLabel.setFont(new Font("Arial", 
                    Font.BOLD, 15));
                nameLabel.setForeground(KReviewTheme.DARK_BLUE);
                g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
                itemCard.add(nameLabel, g);

                // Rating label
                JLabel rateLabel = KReviewTheme.styledLabel(
                    "Rating:");
                g.gridx = 0; g.gridy = 1; g.gridwidth = 1;
                itemCard.add(rateLabel, g);

                JComboBox<Integer> stars = new JComboBox<>(
                    new Integer[]{1, 2, 3, 4, 5});
                stars.setFont(KReviewTheme.INPUT_FONT);
                stars.setPreferredSize(new Dimension(80, 35));
                g.gridx = 1; g.gridy = 1;
                itemCard.add(stars, g);

                // Feedback label
                JLabel feedLabel = KReviewTheme.styledLabel(
                    "Feedback:");
                g.gridx = 0; g.gridy = 2; g.gridwidth = 2;
                itemCard.add(feedLabel, g);

                // Big feedback text area
                JTextArea feedArea = 
                    KReviewTheme.styledTextArea();
                feedArea.setRows(3);
                JScrollPane scroll = new JScrollPane(feedArea);
                scroll.setPreferredSize(
                    new Dimension(550, 80));
                g.gridx = 0; g.gridy = 3; g.gridwidth = 2;
                itemCard.add(scroll, g);

                // Rate button
                JButton rateBtn = KReviewTheme.primaryButton(
                    "Submit Rating");
                rateBtn.setPreferredSize(
                    new Dimension(150, 38));
                g.gridx = 0; g.gridy = 4; g.gridwidth = 2;
                g.insets = new Insets(10, 5, 5, 5);
                itemCard.add(rateBtn, g);

                rateBtn.addActionListener(e -> {
                    int rating = (Integer) 
                        stars.getSelectedItem();
                    submitRating(menuId, rating, 
                        feedArea.getText());
                });

                panel.add(itemCard);
                panel.add(Box.createRigidArea(
                    new Dimension(0, 10)));
            }

            if (!hasItems) {
                JPanel empty = new JPanel(
                    new FlowLayout(FlowLayout.CENTER));
                empty.setBackground(KReviewTheme.LIGHT_BG);
                JLabel msg = new JLabel(
                    "No menu added for today yet!");
                msg.setFont(new Font("Arial", Font.ITALIC, 14));
                msg.setForeground(KReviewTheme.TEXT_GRAY);
                empty.add(msg);
                panel.add(empty);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar()
            .setUnitIncrement(16);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(scrollPane, BorderLayout.CENTER);
        return wrapper;
    }

    void submitRating(int menuId, int stars, String feedback) {
        String sql = "INSERT INTO ratings (menu_id, " +
                     "student_id, stars, feedback) " +
                     "VALUES (?, ?, ?, ?) ON DUPLICATE KEY " +
                     "UPDATE stars=?, feedback=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, menuId);
            ps.setInt(2, userId);
            ps.setInt(3, stars);
            ps.setString(4, feedback);
            ps.setInt(5, stars);
            ps.setString(6, feedback);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this,
                "Rating submitted successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}