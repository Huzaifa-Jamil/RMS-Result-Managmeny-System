package ui;

import java.awt.*;
import javax.swing.*;
import utils.*;

public class System_Controls_UI {
    private JLayeredPane layeredPane;

    public System_Controls_UI() {
        this.layeredPane = RMS_Result_Management_System_UI.mainFrame.getLayeredPane();
        createSystemControls();
    }
  
    public void createSystemControls() {

        Color Green = new Color(37, 211, 102);
        Color DarkGreen = new Color(32, 180, 87);
        Color LightGreen = new Color(52, 230, 120);

        JPanel shield = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 35));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        shield.setBounds(0, 0, RMS_Result_Management_System_UI.mainFrame.getWidth(), 
                RMS_Result_Management_System_UI.mainFrame.getHeight());
        shield.setOpaque(false);

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(Green);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

                g2.dispose();
            }
        };

        panel.setOpaque(false);
        panel.setSize(500, 300);
        int x = (RMS_Result_Management_System_UI.mainFrame.getWidth() - 500) / 2;
        int y = (RMS_Result_Management_System_UI.mainFrame.getHeight() - 300) / 2;
        panel.setLocation(x, y);

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                e.consume();
            }
        });

        // Title label
        JLabel titleLabel = new JLabel("System Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 50));


        // Create buttons
        JButton aboutBtn = UI_Styles.createActionButton("About", new Color(66, 133, 244), 120);
        JButton saveBtn = UI_Styles.createActionButton("Save Data", new Color(37, 211, 102), 120);
        JButton loadBtn = UI_Styles.createActionButton("Load Data", new Color(255, 193, 7), 120);
        JButton exitBtn = UI_Styles.createActionButton("Exit System", new Color(255, 59, 48), 120);
        JButton refreshBtn = UI_Styles.createActionButton("Refresh", new Color(156, 39, 176), 120);

        // Add action listeners
        aboutBtn.addActionListener(e -> {
            layeredPane.remove(panel);
            layeredPane.remove(shield);
            layeredPane.repaint();
            UI_Styles.showInformation(
                    RMS_Result_Management_System_UI.mainFrame, Html_Files.showAboutSystem(), "About RMS System");
        });

        saveBtn.addActionListener(e -> {
            layeredPane.remove(panel);
            layeredPane.remove(shield);
            layeredPane.repaint();
            Data_Peristance.save_All_Data_To_DataBase();
        });

        loadBtn.addActionListener(e -> {
            layeredPane.remove(panel);
            layeredPane.remove(shield);
            layeredPane.repaint();
            Data_Peristance.load_Data_From_DataBase();
        });

        exitBtn.addActionListener(e -> {
            layeredPane.remove(panel);
            layeredPane.remove(shield);
            layeredPane.repaint();
            exitFromSystem();
        });

        refreshBtn.addActionListener(e -> {
            layeredPane.remove(panel);
            layeredPane.remove(shield);
            layeredPane.repaint();
            Refresh_All.refresh();
            UI_Styles.showSideNotifications(RMS_Result_Management_System_UI.mainFrame, "System refreshed!");

        });

        JButton okBtn = new JButton("OK") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(DarkGreen);
                } else if (getModel().isRollover()) {
                    g2.setColor(LightGreen);
                } else {
                    g2.setColor(Green);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();

                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        okBtn.setBackground(Green);
        okBtn.setForeground(Color.WHITE);
        okBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        okBtn.setFocusPainted(false);
        okBtn.setContentAreaFilled(false);
        okBtn.setBorderPainted(false);
        okBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        okBtn.addActionListener(e -> {
            layeredPane.remove(panel);
            layeredPane.remove(shield);
            layeredPane.repaint();
        });

        titleLabel.setBounds(0, 20, 500, 40);
        saveBtn.setBounds(55, 100, 120, 40);
        loadBtn.setBounds(195, 100, 120, 40);
        refreshBtn.setBounds(335, 100, 120, 40);
        aboutBtn.setBounds(125, 160, 120, 40);
        exitBtn.setBounds(265, 160, 120, 40);
        okBtn.setBounds(205, 240, 100, 40);

        panel.add(titleLabel);
        panel.add(aboutBtn);
        panel.add(saveBtn);
        panel.add(loadBtn);
        panel.add(exitBtn);
        panel.add(okBtn);
        panel.add(refreshBtn);


        layeredPane.add(shield, Integer.valueOf(JLayeredPane.MODAL_LAYER));
        layeredPane.add(panel, Integer.valueOf(JLayeredPane.POPUP_LAYER));

        shield.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                layeredPane.remove(panel);
                layeredPane.remove(shield);
                layeredPane.repaint();
                shield.removeMouseListener(this);
            }
        });

        shield.revalidate();
        panel.revalidate();
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public static void exitFromSystem() {

        UI_Styles.deleteOptions(RMS_Result_Management_System_UI.mainFrame,
                "confirm to save data before exit",option -> {
                    if (option.equals("confirm")) {
                        Data_Peristance.save_All_Data_To_DataBase();
                        System.exit(0);
                    } else {
                        System.exit(0);
                    }
        });
    }
}