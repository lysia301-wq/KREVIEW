package com.com;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KReviewTheme {

    // KCT Colors
    public static final Color DARK_BLUE = new Color(0, 51, 102);
    public static final Color GOLD = new Color(255, 184, 0);
    public static final Color LIGHT_BG = new Color(240, 245, 255);
    public static final Color WHITE = Color.WHITE;
    public static final Color LIGHT_GRAY = new Color(245, 245, 245);
    public static final Color TEXT_GRAY = new Color(100, 100, 100);

    // Fonts
    public static final Font TITLE_FONT = 
        new Font("Arial", Font.BOLD, 22);
    public static final Font SUBTITLE_FONT = 
        new Font("Arial", Font.PLAIN, 13);
    public static final Font LABEL_FONT = 
        new Font("Arial", Font.BOLD, 13);
    public static final Font INPUT_FONT = 
        new Font("Arial", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = 
        new Font("Arial", Font.BOLD, 14);

    // Styled text field
    public static JTextField styledField(int width) {
        JTextField f = new JTextField();
        f.setFont(INPUT_FONT);
        f.setPreferredSize(new Dimension(width, 42));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(200, 210, 230), 1),
            BorderFactory.createEmptyBorder(5, 12, 5, 12)));
        f.setBackground(WHITE);
        return f;
    }

    // Styled password field
    public static JPasswordField styledPassword(int width) {
        JPasswordField f = new JPasswordField();
        f.setFont(INPUT_FONT);
        f.setPreferredSize(new Dimension(width, 42));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(200, 210, 230), 1),
            BorderFactory.createEmptyBorder(5, 12, 5, 12)));
        f.setBackground(WHITE);
        return f;
    }

    // Styled text area (for feedback)
    public static JTextArea styledTextArea() {
        JTextArea ta = new JTextArea(4, 20);
        ta.setFont(INPUT_FONT);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(200, 210, 230), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        ta.setBackground(WHITE);
        return ta;
    }

    // Primary blue button
    public static JButton primaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(BUTTON_FONT);
        btn.setBackground(DARK_BLUE);
        btn.setForeground(WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(220, 42));
        btn.setBorder(BorderFactory.createEmptyBorder(
            10, 20, 10, 20));
        btn.setOpaque(true);
        return btn;
    }

    // Gold button
    public static JButton goldButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(BUTTON_FONT);
        btn.setBackground(GOLD);
        btn.setForeground(DARK_BLUE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(220, 42));
        btn.setBorder(BorderFactory.createEmptyBorder(
            10, 20, 10, 20));
        btn.setOpaque(true);
        return btn;
    }

    // Card panel (white rounded card)
    public static JPanel cardPanel() {
        JPanel p = new JPanel();
        p.setBackground(WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(220, 230, 245), 1),
            BorderFactory.createEmptyBorder(
                30, 40, 30, 40)));
        return p;
    }

    // Header panel with KCT branding
    public static JPanel headerPanel(String subtitle) {
        JPanel header = new JPanel();
        header.setBackground(DARK_BLUE);
        header.setLayout(new BoxLayout(
            header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(
            25, 20, 25, 20));

        JLabel title = new JLabel("KREVIEW");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(GOLD);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel(subtitle);
        sub.setFont(SUBTITLE_FONT);
        sub.setForeground(WHITE);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel college = new JLabel(
            "Kumaraguru College of Technology");
        college.setFont(new Font("Arial", Font.PLAIN, 11));
        college.setForeground(new Color(180, 200, 230));
        college.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(Box.createRigidArea(new Dimension(0, 5)));
        header.add(sub);
        header.add(Box.createRigidArea(new Dimension(0, 3)));
        header.add(college);

        return header;
    }

    // Footer panel
    public static JPanel footerPanel() {
        JPanel footer = new JPanel();
        footer.setBackground(DARK_BLUE);
        footer.setBorder(BorderFactory.createEmptyBorder(
            8, 10, 8, 10));
        JLabel label = new JLabel(
            "KCT Hostel Mess  |  Coimbatore - 641049");
        label.setForeground(new Color(180, 200, 230));
        label.setFont(new Font("Arial", Font.PLAIN, 11));
        footer.add(label);
        return footer;
    }

    // Label style
    public static JLabel styledLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(LABEL_FONT);
        l.setForeground(DARK_BLUE);
        return l;
    }

    // Set frame defaults
    public static void setupFrame(JFrame frame, 
                                   String title,
                                   int width, int height) {
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(LIGHT_BG);
    }
}