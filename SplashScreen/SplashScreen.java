package SplashScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class SplashScreen extends JWindow {
    private Timer timer;

    public SplashScreen() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.WHITE);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBorder(new EmptyBorder(50, 50, 10, 50));

        ImageIcon icon = new ImageIcon("Icons/centerIcon.webp");

        if (icon.getIconWidth() > 0) {
            Image originalImage = icon.getImage();
            Image scaledImage = originalImage.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
        } 

        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBackground(Color.WHITE);
        labelPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        JLabel systemLabel = new JLabel("RMS Result Management System");
        systemLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        systemLabel.setForeground(new Color(0, 51, 102));
        systemLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(100, 10));
        progressBar.setBorderPainted(true);
        progressBar.setBackground(new Color(240, 240, 240));
        progressBar.setForeground(new Color(0, 102, 204));

        JPanel textPanel = new JPanel(new GridLayout(1, 1, 5, 5));
        textPanel.setBackground(Color.WHITE);
        textPanel.add(systemLabel);
        labelPanel.add(textPanel, BorderLayout.NORTH);
        labelPanel.add(progressBar, BorderLayout.SOUTH);

        contentPane.add(imagePanel, BorderLayout.CENTER);
        contentPane.add(labelPanel, BorderLayout.SOUTH);

        contentPane.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));

        this.setContentPane(contentPane);
        this.setSize(1200, 800);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setVisible(true);
    }
    public void closeSplash() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        this.dispose();
    }
}
