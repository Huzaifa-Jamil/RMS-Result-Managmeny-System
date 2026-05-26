package ui;

import javax.swing.*;
import java.awt.*;

public class Splash_Screen_UI extends JWindow {

    private JProgressBar progressBar;
    ImageIcon icon;
    JLabel title;
    JPanel root;
    JPanel centerPanel;
    JLabel tagLine;

    public Splash_Screen_UI() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        Rectangle bounds = gc.getBounds();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

        int usableWidth = bounds.width - screenInsets.left - screenInsets.right;
        int usableHeight = bounds.height - screenInsets.top - screenInsets.bottom;

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int screenW = screen.width;
        int screenH = screen.height;

        root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        int logoSize = Math.min(screenW, screenH) / 6;

        icon = new ImageIcon(getClass().getResource("/icons/logo.png"));
        Image img = icon.getImage().getScaledInstance(
                logoSize, logoSize, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 25, 0);
        centerPanel.add(logo, gbc);

        title = new JLabel("RMS Result Management System");
        title.setFont(new Font("Segoe UI", Font.PLAIN, screenW / 50));
        title.setForeground(new Color(60, 60, 60));

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 25, 0);
        centerPanel.add(title, gbc);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(false);
        progressBar.setBorderPainted(false);
        progressBar.setPreferredSize(new Dimension(screenW / 4, 5));
        progressBar.setBackground(new Color(230, 230, 230));
        progressBar.setForeground(new Color(37, 211, 102));

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 35, 0);
        centerPanel.add(progressBar, gbc);

        tagLine = new JLabel("✦ Academic Results, Simplified");
        tagLine.setFont(new Font("Segoe UI Symbol", Font.PLAIN, screenW / 90));
        tagLine.setForeground(Color.GRAY);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        centerPanel.add(tagLine, gbc);

        root.add(centerPanel, BorderLayout.CENTER);
        setContentPane(root);

        setBounds(screenInsets.left, screenInsets.top, usableWidth, usableHeight);
        setVisible(true);
    }

    public void closeSplash() {
        dispose();
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }
}
