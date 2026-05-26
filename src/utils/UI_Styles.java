package utils;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class UI_Styles {

    public static boolean isDialogueShowing = false;
    public static int ySlideUp; // To add some extra distance from existing panel
    public static int count;

    public static JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(15, 15, 15, 15)));
        return panel;
    }

    public static JPanel createHeaderPanel(String title) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setPreferredSize(new Dimension(0, 60));
        headerPanel.setBorder(new Round_Panel_Border_All(30, new Color(200, 200, 200)));
        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(51, 51, 51));

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        return headerPanel;
    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return label;
    }

    public static JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setOpaque(false);

        final Color[] borderColor = { new Color(200, 200, 200) };

        Border roundedBorder = new AbstractBorder() {
            private int radius = 8;

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(borderColor[0]);
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
                g2.dispose();
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(5, 10, 5, 10);
            }

            @Override
            public Insets getBorderInsets(Component c, Insets insets) {
                insets.left = insets.right = 10;
                insets.top = insets.bottom = 5;
                return insets;
            }
        };

        field.setBorder(roundedBorder);
        field.setBackground(new Color(255, 255, 255));
        field.setCaretColor(Color.BLACK);
        field.setPreferredSize(new Dimension(0, 32));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                borderColor[0] = new Color(52, 152, 219);
                field.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                borderColor[0] = new Color(200, 200, 200);
                field.repaint();
            }
        });
        return field;
    }

    public static JButton createActionButton(String text, Color color, int width) {
        JButton button = new JButton(text) {
            private int radius = 12;
            private Color currentColor = color;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(currentColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(bg);
                currentColor = bg;
                repaint();
            }
        };

        button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(width, 35));

        // button.addMouseListener(new java.awt.event.MouseAdapter() {
        //     public void mouseEntered(java.awt.event.MouseEvent evt) {
        //         button.setBackground(color.brighter());
                

        //     public void mouseExited(java.awt.event.MouseEvent evt) {
        //         button.setBackground(color);
        //     }
        // });

        button.setToolTipText("<html>" +
                                "<div style='" +
                                "background-color: #E3E3E3; " +
                                "color: #333333; " +
                                "padding: 4px 8px; " +
                                "margin: -2px;'>" +
                                text +
                                "</div></html>");

        button.addChangeListener(e -> {
            ButtonModel model = button.getModel();
            
            if (model.isArmed()) {
                button.setBackground(color.darker());
            } else if (model.isRollover()) {
                button.setBackground(color.brighter());
            } else {
                button.setBackground(color);
            }
        });

        return button;
    }

    public static JTable createStyledTable(DefaultTableModel model) {
        Color WHATSAPP_GREEN = new Color(37, 211, 102);
        Color ROW_ALT = new Color(241, 252, 245);
        Color GRID = new Color(200, 235, 215);

        JTable table = new JTable(model);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(32);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.DARK_GRAY);
        table.setSelectionBackground(new Color(37, 211, 102, 70));
        table.setSelectionForeground(Color.BLACK);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setGridColor(GRID);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFocusable(false);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                JLabel cell = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                cell.setHorizontalAlignment(SwingConstants.CENTER);

                if (!isSelected) {
                    cell.setBackground(row % 2 == 0 ? Color.WHITE : ROW_ALT);
                }

                cell.setBorder(new EmptyBorder(0, 12, 0, 12));
                return cell;
            }
        });

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(WHATSAPP_GREEN);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 38));
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                JLabel label = new JLabel(value.toString());
                label.setFont(new Font("Segoe UI", Font.BOLD, 13));
                label.setOpaque(true);
                label.setBackground(WHATSAPP_GREEN);
                label.setForeground(Color.WHITE);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(new EmptyBorder(6, 10, 6, 10));
                return label;
            }
        });

        return table;
    }

    public static void styleTableScrollPane(JScrollPane tableScroll) {
        tableScroll.setOpaque(true);
        tableScroll.setBorder(new LineBorder(new Color(230, 230, 230)));
    }


    public static void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        combo.setForeground(Color.DARK_GRAY);
        combo.setBackground(Color.WHITE);
        combo.setFocusable(false);

        combo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(2, 8, 2, 2)
        ));

        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);
                label.setBorder(new EmptyBorder(3, 8, 3, 8));
                return label;
            }
        });
    }

    public static JPanel createStatCard(String title, JLabel valueLabel, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(color, 2),
                new EmptyBorder(20, 10, 10, 20)));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(52, 73, 94));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        valueLabel.setForeground(new Color(52, 73, 94));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(color.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(color);
            }
        });

        return card;
    }

    public static void showError(JFrame parent, String text) {
        JLayeredPane layeredPane = parent.getLayeredPane();

        Color Error = new Color(244, 67, 54);
        Color ErrorDark = new Color(211, 47, 47);
        Color ErrorLight = new Color(229, 115, 115);

        JPanel shield = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 35));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        shield.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        shield.setOpaque(false);

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(Error);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2.dispose();
            }
        };

        panel.setOpaque(false);
        panel.setSize(300, 130);
        int x = (parent.getWidth() - 300) / 2;
        int y = (parent.getHeight() - 130) / 2;
        panel.setLocation(x, y);

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                e.consume();
            }
        });

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setBounds(10, 20, 280, 40);
        label.setFont(new Font("SansSerif", Font.PLAIN, 15));
        label.setForeground(new Color(50, 50, 50));
        panel.add(label);

        JButton okBtn = new JButton("OK") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(ErrorLight);
                } else if (getModel().isRollover()) {
                    g2.setColor(ErrorLight);
                } else {
                    g2.setColor(Error);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();

                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        okBtn.setBounds(100, 80, 100, 40);
        okBtn.setBackground(Error);
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

        panel.add(okBtn);

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

        Timer timer = new Timer(2000, e -> {
            layeredPane.remove(panel);
            layeredPane.remove(shield);
            layeredPane.repaint();
        });
        timer.setRepeats(false);
        timer.start();

        shield.revalidate();
        panel.revalidate();
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public static void showNotification(JFrame parent, String text) {
        JLayeredPane layeredPane = parent.getLayeredPane();

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
        shield.setBounds(0, 0, parent.getWidth(), parent.getHeight());
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
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
                g2.dispose();
            }
        };

        panel.setOpaque(false);
        panel.setSize(300, 130);
        int x = (parent.getWidth() - 300) / 2;
        int y = (parent.getHeight() - 130) / 2;
                panel.setLocation(x, y);

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            e.consume();
            }
        });

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setBounds(10, 20, 280, 40);
        label.setFont(new Font("SansSerif", Font.PLAIN, 15));
        label.setForeground(new Color(50, 50, 50));
        panel.add(label);

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

        okBtn.setBounds(100, 80, 100, 40);
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

        panel.add(okBtn);

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

        Timer timer = new Timer(2000, e -> {
            layeredPane.remove(panel);
            layeredPane.remove(shield);
            layeredPane.repaint();
        });
        timer.setRepeats(false);
        timer.start();

        shield.revalidate();
        panel.revalidate();
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public static void showInformation(JFrame parent, String htmlText, String Title) {

        JLayeredPane layeredPane = parent.getLayeredPane();
        Color DarkGreen = new Color(32, 180, 87);
        Color LightGreen = new Color(52, 230, 120);

        JPanel shield = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 35));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        shield.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        shield.setOpaque(false);

        int dialogWidth = 900;
        int dialogHeight = 650;

        JPanel panel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

                g2.dispose();
            }
        };

        panel.setOpaque(false);
        panel.setSize(dialogWidth, dialogHeight);
        int x = (parent.getWidth() - dialogWidth) / 2;
        int y = (parent.getHeight() - dialogHeight - 45) / 2;
        panel.setLocation(x, y);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(Title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(52, 73, 94));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(htmlText);
        editorPane.setEditable(false);
        editorPane.setCaretPosition(0);
        editorPane.setBackground(new Color(248, 250, 252));

        editorPane.addMouseWheelListener(e -> {
            JScrollBar scrollBar = ((JScrollPane) editorPane.getParent().getParent()).getVerticalScrollBar();
            scrollBar.setValue(scrollBar.getValue() + e.getWheelRotation() * 30);
        });

        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.getVerticalScrollBar().setUnitIncrement(80);

        UI_Styles.styleTableScrollPane(scrollPane);

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));

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
                    g2.setColor(DarkGreen);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();

                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        okBtn.setPreferredSize(new Dimension(120, 40));
        okBtn.setBackground(LightGreen);
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

        buttonPanel.add(okBtn);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(contentPanel, BorderLayout.CENTER);

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
        panel.requestFocusInWindow();
        editorPane.requestFocusInWindow();
    }

    public static void showSideErrors(JFrame parent, String text) {
        if (count > 0) {
            if (ySlideUp < parent.getHeight()) {
                ySlideUp = ySlideUp + 63;
            } else {
                ySlideUp = 0;
            }
        } else {
            ySlideUp = 0;
        }

        count = count + 1;
        
        JLayeredPane layeredPane = parent.getLayeredPane();
        Color red = new Color(244, 67, 54);

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(red);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
            }
        };

        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.setSize(270, 60);
        int x = parent.getWidth() - 305;
        int y = parent.getHeight() - 115 - ySlideUp;
        panel.setLocation(x, y);

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.CENTER);

        layeredPane.add(panel, Integer.valueOf(JLayeredPane.POPUP_LAYER));

        Timer timer = new Timer(2000, e -> {
            layeredPane.remove(panel);
            layeredPane.repaint();
            count = count - 1;
        });
        timer.setRepeats(false);
        timer.start();

        panel.revalidate();
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public static void showSideNotifications(JFrame parent, String text) {
        if (count > 0) {
            if (ySlideUp < parent.getHeight()) {
                ySlideUp = ySlideUp + 63;
            }
            else {
                ySlideUp = 0;
            }
        }
        else {
            ySlideUp = 0;
        }

        count = count + 1;

        JLayeredPane layeredPane = parent.getLayeredPane();
        Color Green = new Color(37, 211, 102);

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Green);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
            }
        };

        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.setSize(270, 60);
        int x = parent.getWidth() - 305;
        int y = (parent.getHeight() - 115 ) - ySlideUp;
        panel.setLocation(x, y);

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.CENTER);

        layeredPane.add(panel, Integer.valueOf(JLayeredPane.POPUP_LAYER));

        Timer timer = new Timer(2000, e -> {
            layeredPane.remove(panel);
            layeredPane.repaint();
            count = count - 1;
        });
        timer.setRepeats(false);
        timer.start();

        panel.revalidate();
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public static void makeFocusStealerRecursive(JPanel root) {
        root.setFocusable(true);
        root.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                root.requestFocusInWindow();
            }
        });

        // preform focus stealing by recursion method and suitable for nested panels
        for (Component c : root.getComponents()) {
            if (c instanceof JPanel) {
                makeFocusStealerRecursive((JPanel) c);
            }
        }
    }

    public static void deleteOptions(JFrame parent, String text, Consumer<String> callback) {
        if (isDialogueShowing) {
            return;
        }
        isDialogueShowing = true;
        JLayeredPane layeredPane = parent.getLayeredPane();

        Color Yellow = new Color(255, 193, 7);
        Color DarkYellow = new Color(220, 160, 0);
        Color LightYellow = new Color(255, 214, 80);

        JPanel shield = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 35));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        shield.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        shield.setOpaque(false);

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(Yellow);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2.dispose();
            }
        };

        panel.setOpaque(false);
        panel.setSize(300, 130);
        int x = (parent.getWidth() - 300) / 2;
        int y = (parent.getHeight() - 130) / 2;
        panel.setLocation(x, y);

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                e.consume();
            }
        });

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setBounds(10, 20, 280, 40);
        label.setFont(new Font("SansSerif", Font.PLAIN, 15));
        label.setForeground(new Color(50, 50, 50));
        panel.add(label);

        JButton confirmBtn = new JButton("confirm") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(DarkYellow);
                }
                else if (getModel().isRollover()) {
                    g2.setColor(LightYellow);
                }
                else {
                    g2.setColor(Yellow);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();

                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        confirmBtn.setBounds(40, 75, 100, 40);
        confirmBtn.setBackground(Yellow);
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirmBtn.setFocusPainted(false);
        confirmBtn.setContentAreaFilled(false);
        confirmBtn.setBorderPainted(false);
        confirmBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        confirmBtn.addActionListener(e -> {
            layeredPane.remove(panel);
            layeredPane.remove(shield);
            layeredPane.repaint();
            isDialogueShowing = false;
            callback.accept("confirm");
        });

        JButton cancelBtn = new JButton("Cancel") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(new Color(180, 180, 180));
                }
                else if (getModel().isRollover()) {
                    g2.setColor(new Color(210, 210, 210));
                }
                else {
                    g2.setColor(new Color(190, 190, 190));
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        cancelBtn.setBounds(160, 75, 100, 40);
        cancelBtn.setBackground(Yellow);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelBtn.addActionListener(e -> {
            layeredPane.remove(panel);
            layeredPane.remove(shield);
            layeredPane.repaint();
            isDialogueShowing = false;
            callback.accept("cancel");
        });

        panel.add(cancelBtn);
        panel.add(confirmBtn);

        layeredPane.add(shield, Integer.valueOf(JLayeredPane.MODAL_LAYER));
        layeredPane.add(panel, Integer.valueOf(JLayeredPane.POPUP_LAYER));

        shield.revalidate();
        panel.revalidate();
        layeredPane.revalidate();
        layeredPane.repaint();
    }
}