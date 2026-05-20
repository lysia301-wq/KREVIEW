package com.com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ManagementDashboard extends JFrame {

    public ManagementDashboard(int userId, String name) {
        KReviewTheme.setupFrame(this,
            "KREVIEW - Management Dashboard", 520, 620);
        setLayout(new BorderLayout());
        getContentPane().setBackground(KReviewTheme.LIGHT_BG);

        add(KReviewTheme.headerPanel(
            "Management Dashboard"), BorderLayout.NORTH);

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(KReviewTheme.LIGHT_BG);

        JPanel card = KReviewTheme.cardPanel();
        card.setLayout(new GridBagLayout());
        card.setPreferredSize(new Dimension(380, 420));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridwidth = 1;

        JLabel welcome = new JLabel("Welcome, " + name + "!");
        welcome.setFont(new Font("Arial", Font.BOLD, 17));
        welcome.setForeground(KReviewTheme.DARK_BLUE);
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
        card.add(welcome, g);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(220, 230, 245));
        g.gridy = 1;
        card.add(sep, g);

        JButton addMenuBtn = KReviewTheme.primaryButton(
            "Add Today's Menu");
        JButton ratingsBtn = KReviewTheme.primaryButton(
            "View Ratings & Feedback");
        JButton wasteBtn = KReviewTheme.primaryButton(
            "Enter Waste Data");
        JButton suggestBtn = KReviewTheme.primaryButton(
            "View Suggestions");
        JButton logoutBtn = KReviewTheme.goldButton("Logout");

        g.gridy = 2; card.add(addMenuBtn, g);
        g.gridy = 3; card.add(ratingsBtn, g);
        g.gridy = 4; card.add(wasteBtn, g);
        g.gridy = 5; card.add(suggestBtn, g);
        g.gridy = 6; card.add(logoutBtn, g);

        addMenuBtn.addActionListener(e -> 
            new AddMenuScreen(userId));
        ratingsBtn.addActionListener(e -> 
            new ViewRatingsScreen());
        wasteBtn.addActionListener(e -> 
            new WasteScreen(userId));
        suggestBtn.addActionListener(e -> 
            new ViewSuggestionsScreen());
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginScreen();
        });

        center.add(card);
        add(center, BorderLayout.CENTER);
        add(KReviewTheme.footerPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }
}