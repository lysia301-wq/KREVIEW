package com.com;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginScreen extends JFrame {

    JTextField rollNoField;
    JPasswordField passwordField;

    public LoginScreen() {
        KReviewTheme.setupFrame(this, "KREVIEW - Login", 
                                480, 580);
        setLayout(new BorderLayout());
        getContentPane().setBackground(KReviewTheme.LIGHT_BG);

        // Header
        add(KReviewTheme.headerPanel(
            "Hostel Mess Food Review System"), 
            BorderLayout.NORTH);

        // Center card
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(KReviewTheme.LIGHT_BG);

        JPanel card = KReviewTheme.cardPanel();
        card.setLayout(new GridBagLayout());
        card.setPreferredSize(new Dimension(360, 320));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridwidth = 2;

        // Login title on card
        JLabel loginTitle = new JLabel("Login to your account");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 16));
        loginTitle.setForeground(KReviewTheme.DARK_BLUE);
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridx = 0; g.gridy = 0;
        card.add(loginTitle, g);

        // Roll No
        g.gridy = 1;
        card.add(KReviewTheme.styledLabel("Roll No / Staff ID"), 
                 g);
        rollNoField = KReviewTheme.styledField(300);
        rollNoField.setToolTipText("Enter your roll number");
        g.gridy = 2;
        card.add(rollNoField, g);

        // Password
        g.gridy = 3;
        card.add(KReviewTheme.styledLabel("Password"), g);
        passwordField = KReviewTheme.styledPassword(300);
        g.gridy = 4;
        card.add(passwordField, g);

        // Login button
        JButton loginBtn = KReviewTheme.primaryButton("LOGIN");
        loginBtn.setPreferredSize(new Dimension(300, 44));
        g.gridy = 5;
        g.insets = new Insets(20, 8, 8, 8);
        card.add(loginBtn, g);

        center.add(card);
        add(center, BorderLayout.CENTER);
        add(KReviewTheme.footerPanel(), BorderLayout.SOUTH);

        loginBtn.addActionListener(e -> login());
        passwordField.addActionListener(e -> login());

        setVisible(true);
    }

    void login() {
        String rollNo = rollNoField.getText().trim();
        String password = new String(
            passwordField.getPassword());

        if (rollNo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter Roll No and Password!",
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM users WHERE " +
                     "roll_no = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, rollNo);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                String name = rs.getString("name");
                int userId = rs.getInt("user_id");
                dispose();
                if (role.equals("student")) {
                    new StudentDashboard(userId, name);
                } else {
                    new ManagementDashboard(userId, name);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                    "Invalid Roll No or Password!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}