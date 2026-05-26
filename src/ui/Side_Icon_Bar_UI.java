package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import utils.UI_Styles;

public class Side_Icon_Bar_UI extends JPanel{

    private JTabbedPane tabbedPane;
    private System_Controls_UI systemControls;

    public Side_Icon_Bar_UI( JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        createSideIconBar();
    }
    
    private void createSideIconBar() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(60, 0));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(209, 215, 219)));
        this.add(Box.createVerticalStrut(0));
        this.add(createSideButton("/icons/student.png", "Student Management", 0));
        this.add(Box.createVerticalStrut(15));
        this.add(createSideButton("/icons/course.png", "Course Catalog",1));
        this.add(Box.createVerticalStrut(15));
        this.add(createSideButton("/icons/faculty.png", "Faculty Directory",2));
        this.add(Box.createVerticalStrut(15));
        this.add(createSideButton("/icons/result.png", "Results Records",3));
        this.add(Box.createVerticalStrut(15));
        this.add(createSideButton("/icons/analytics.png", "Analytics Dashboard",4));
        this.add(Box.createVerticalStrut(15));
        this.add(createSideButton("/icons/report.png", "PDF Reports",5));
        this.add(Box.createVerticalGlue());
        this.add(createSideButton("/icons/setting.png", "System Settings",6));
        this.add(Box.createVerticalStrut(15));
        this.add(createSideButton("/icons/dashboard.png", "System Dashboard",8));
        this.add(Box.createVerticalStrut(15));

        UI_Styles.makeFocusStealerRecursive(this);
    }

    private JLabel createSideButton(String iconPath, String tooltipText, int targetTabIndex) {
        JLabel label = new JLabel();
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon baseIcon;
        ImageIcon hoverIcon;

        try {
            ImageIcon rawIcon = new ImageIcon(getClass().getResource(iconPath));
            Image scaled = rawIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            baseIcon = new ImageIcon(scaled);
            hoverIcon = Iconchange(baseIcon, new Color(150, 150, 150));
            label.setIcon(baseIcon);

        } catch (Exception e) {
            label.setText("?");
            return label;
        }

        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (targetTabIndex == 8) {
                    systemControls = new System_Controls_UI();
                } else {
                    tabbedPane.setSelectedIndex(targetTabIndex);
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                label.setIcon(hoverIcon);
                label.setToolTipText(
                        "<html><div style='background-color: #E3E3E3; color: #333333; padding: 4px 8px;'>"
                                + tooltipText +
                                "</div></html>");
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                label.setIcon(baseIcon);
            }
        });
        return label;
    }
    private ImageIcon Iconchange(ImageIcon icon, Color color) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();

        BufferedImage tinted = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = tinted.createGraphics();

        g2.drawImage(icon.getImage(), 0, 0, null);

        g2.setComposite(AlphaComposite.SrcAtop);
        g2.setColor(color);
        g2.fillRect(0, 0, w, h);

        g2.dispose();
        return new ImageIcon(tinted);
    }
}
