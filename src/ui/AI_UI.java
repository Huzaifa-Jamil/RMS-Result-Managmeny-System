package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import utils.*;

public class AI_UI extends JPanel{

    private JPanel chatArea;
    private JTextField inputField;
    private JLabel sendIcon;
    private JPanel headerPanel;
    private JLabel plusIcon;
    private JPanel centerPanel;
    private JScrollPane chatScrollPane;
    private Timer activeTypewriterTimer;
    public boolean welcomeShowed = false;

    public AI_UI() {
        createAIPanel();
    }

    public void createAIPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new Round_Panel_Border_Top_Left(15, Color.BLACK));
        this.setOpaque(false);
        this.setBackground(Color.BLACK);

        headerPanel = createHeaderPanel();

        chatArea = new JPanel();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        chatArea.setBackground(Color.BLACK);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setBorder(new EmptyBorder(20, 150, 20, 150));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        centerPanel.add(headerPanel, gbc);

        gbc.gridy = 1;
        centerPanel.add(chatArea, gbc);

        chatScrollPane = new JScrollPane(centerPanel);
        chatScrollPane.setBorder(null);
        chatScrollPane.getViewport().setBackground(Color.BLACK);
        chatScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        chatScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        this.add(chatScrollPane, BorderLayout.CENTER);
        this.add(createInputPanel(), BorderLayout.SOUTH);

        MouseAdapter focusGrabber = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AI_UI.this.requestFocusInWindow();
            }
        };

        this.addMouseListener(focusGrabber);
        centerPanel.addMouseListener(focusGrabber);
        setupLogic();

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public JPanel createHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.BLACK);
        headerPanel.setBorder(new EmptyBorder(100, 0, 20, 0));

        JPanel logo = new JPanel();
        logo.setLayout(new BoxLayout(logo, BoxLayout.X_AXIS));
        logo.setBackground(Color.BLACK);

        JLabel logoIcon = new JLabel();
        logoIcon.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        try {
            ImageIcon Icon = new ImageIcon(getClass().getResource("/icons/echoLogo.png"));
            Image img = Icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            logoIcon.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            logoIcon.setText("<html><font color='white' size='30'>◎</font></html>");
        }

        JLabel logoTitle = new JLabel("Echo");
        logoTitle.setFont(new Font("San Francisco", Font.BOLD, 80));
        logoTitle.setForeground(Color.WHITE);
        logoTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        logoTitle.setBorder(new EmptyBorder(0, 15, 0, 0));

        logo.add(Box.createHorizontalGlue());
        logo.add(logoIcon);
        logo.add(logoTitle);
        logo.add(Box.createHorizontalGlue());

        JLabel lableTagline = new JLabel("Think. Execute. Echo. ");
        lableTagline.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lableTagline.setForeground(new Color(200, 200, 200));
        lableTagline.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        lableTagline.setBorder(new EmptyBorder(20, 0, 10, 0));

        headerPanel.add(logo);
        headerPanel.add(lableTagline);

        return headerPanel;
    }


    public JPanel createInputPanel() {
        JPanel inputpanel = new JPanel(new BorderLayout());
        inputpanel.setBackground(Color.BLACK);
        inputpanel.setBorder(new EmptyBorder(20, 150, 40, 150));

        JPanel roundedBox = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(32, 35, 39));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
                g2.dispose();
            }
        };
        roundedBox.setOpaque(false);
        roundedBox.setPreferredSize(new Dimension(0, 50));
        roundedBox.setBorder(new EmptyBorder(5, 15, 5, 15));

        plusIcon = new JLabel();
        try {
            ImageIcon rawPlus = new ImageIcon(getClass().getResource("/icons/newChat.png"));
            Image scaledPlus = rawPlus.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            plusIcon.setIcon(new ImageIcon(scaledPlus));
        } catch (Exception e) {
            plusIcon.setText("<html><font color='black' size='5'> + </font></html>");
        }
        plusIcon.setBorder(new EmptyBorder(0, 5, 0, 10));

        inputField = new JTextField("Ask Echo anything");
        inputField.setBackground(new Color(32, 35, 39));
        inputField.setForeground(Color.GRAY);
        inputField.setCaretColor(Color.WHITE);
        inputField.setBorder(null);
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        sendIcon = new JLabel();
        try {
            ImageIcon rawIcon = new ImageIcon(getClass().getResource("/icons/sendButton.png"));
            Image scaledImg = rawIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            sendIcon.setIcon(new ImageIcon(scaledImg));
        } catch (Exception e) {
            sendIcon.setText("<html><font color='white' size='6'>↑</font></html>");
        }
        plusIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        roundedBox.add(plusIcon, BorderLayout.WEST);
        roundedBox.add(inputField, BorderLayout.CENTER);
        roundedBox.add(sendIcon, BorderLayout.EAST);

        inputpanel.add(roundedBox, BorderLayout.CENTER);
        return inputpanel;
    }

    
    private void setupLogic() {

        inputField.addActionListener(e -> handelSend());

        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Ask Echo anything")) {
                    inputField.setText("");
                    inputField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setForeground(Color.GRAY);
                    inputField.setText("Ask Echo anything");
                }
            }
        });

        plusIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetUI();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.getWindowAncestor(plusIcon).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                plusIcon.setEnabled(false);
                plusIcon.setFocusable(true);
                plusIcon.setToolTipText(
                        "<html>" +
                                "<div style='" +
                                "background-color: #E3E3E3; " +
                                "color: #333333; " +
                                "padding: 4px 8px; " +
                                "margin: -2px;'>" +
                                "New Chat" +
                                "</div></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.getWindowAncestor(plusIcon).setCursor(Cursor.getDefaultCursor());
                plusIcon.setEnabled(true);
            }
        });

        sendIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.getWindowAncestor(sendIcon).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                sendIcon.setEnabled(false);
                sendIcon.setFocusable(true);
                
                sendIcon.setToolTipText(
                        "<html>" +
                                "<div style='" +
                                "background-color: #E3E3E3; " +
                                "color: #333333; " +
                                "padding: 4px 8px; " +
                                "margin: -2px;'>" +
                                "Send" +
                                "</div></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.getWindowAncestor(sendIcon).setCursor(Cursor.getDefaultCursor());
                sendIcon.setEnabled(true);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                handelSend();
            }
        });
    }

    public void handelSend() {
        String text = inputField.getText().trim();
        if (!text.isEmpty() && !text.equals("Ask Echo anything")) {
            if (headerPanel.isVisible() == true) {
                headerPanel.setVisible(false);
                headerPanel.getParent().revalidate();
            }

            addMessage(chatArea, text, true, 10);
            inputField.setText("");
            inputField.requestFocusInWindow();
            Data_Peristance.save_Data_From_DataBase_to_Json();

            new Thread(() -> {
                String aiResponse = askEchoAI(text);

                if (aiResponse.equals("error")) {
                    UI_Styles.showSideErrors(RMS_Result_Management_System_UI.mainFrame, "Error Occurs in echo_AI.exe");
                }
                else if (aiResponse.equals("Json not found")) {
                    UI_Styles.showSideErrors(RMS_Result_Management_System_UI.mainFrame, "Error Occurs in loding file");
                }
                else {
                    SwingUtilities.invokeLater(() -> {
                        addMessage(chatArea, aiResponse, false, 10);
                    });
                }
            }).start();
        }
    }

    public String askEchoAI(String query) {
        StringBuilder response = new StringBuilder();

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "src/ai/echo_AI.exe", query
            );

            pb.redirectErrorStream(true);

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }

            process.waitFor();

        } catch (Exception e) {
            UI_Styles.showSideErrors(RMS_Result_Management_System_UI.mainFrame, "Unexpected Error Occurs");
        }

        return response.toString().trim();
    }

    public void addMessage(JPanel container, String text, boolean isUser, int delay) {

        if (!isUser && activeTypewriterTimer != null && activeTypewriterTimer.isRunning()) {
            activeTypewriterTimer.stop();
        }

        JPanel alignmentWrapper = new JPanel();
        alignmentWrapper.setBackground(Color.BLACK);
        alignmentWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1000));
        alignmentWrapper.setBorder(new EmptyBorder(10, 0, 10, 0));

        if (isUser) {
            alignmentWrapper.setLayout(new FlowLayout(FlowLayout.RIGHT));
            JLabel userBubble = new JLabel("<html><body style='padding: 5px;'>" + text + "</body></html>") {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(32, 35, 39));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    super.paintComponent(g);
                    g2.dispose();
                }
            };
            userBubble.setForeground(Color.WHITE);
            userBubble.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            userBubble.setBorder(new EmptyBorder(8, 15, 8, 15));
            alignmentWrapper.add(userBubble);
        } else {

            alignmentWrapper.setLayout(new BoxLayout(alignmentWrapper, BoxLayout.X_AXIS));

            JTextArea aiText = new JTextArea();
            aiText.setLineWrap(true);
            aiText.setWrapStyleWord(true);
            aiText.setEditable(false);
            aiText.setBackground(Color.BLACK);
            aiText.setForeground(new Color(220, 220, 220));
            aiText.setFont(new Font("Segoe UI", Font.PLAIN, 19));
            aiText.setBorder(new EmptyBorder(10, 0, 10, 0));

            aiText.setMaximumSize(new Dimension(700, Integer.MAX_VALUE));
            aiText.setAlignmentX(Component.LEFT_ALIGNMENT);

            alignmentWrapper.add(aiText);

            activeTypewriterTimer = new Timer(delay, new ActionListener() {
                private int charIndex = 0;
                private StringBuilder displayedText = new StringBuilder();

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (charIndex < text.length()) {
                        displayedText.append(text.charAt(charIndex));
                        aiText.setText(displayedText.toString()
                                                .replace("**", ""));
                        charIndex++;
                        if (charIndex % 3 == 0) {
                            scrollPaneToBottom();
                        }
                    } else {
                            scrollPaneToBottom();
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            activeTypewriterTimer.start();
        }

        container.add(alignmentWrapper);
        container.revalidate();
        container.repaint();
    }

    private void scrollPaneToBottom() {
        SwingUtilities.invokeLater(() -> {
            if (chatScrollPane != null) {
                JScrollBar verticalBar = chatScrollPane.getVerticalScrollBar();
                verticalBar.setValue(verticalBar.getMaximum());            }
        });
    }

    public void showWelcomeMessage() {
        Timer welcome = new Timer(300, ev -> {
            addMessage(chatArea, "Hello ! I am Echo. How can I help you today?", false,20);
        });
        welcome.setRepeats(false);
        welcome.start();
        welcomeShowed = true;
    }

    public void resetUI() {
        if (activeTypewriterTimer != null && activeTypewriterTimer.isRunning()) {
            activeTypewriterTimer.stop();
        }
        chatArea.removeAll();
        headerPanel.setVisible(true);
        inputField.setText("Ask Echo anything");
        inputField.setForeground(Color.GRAY);
        this.requestFocusInWindow();
        welcomeShowed = false;
        showWelcomeMessage();
        this.revalidate();
        this.repaint();
    }
}