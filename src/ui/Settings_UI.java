package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.*;

public class Settings_UI extends JPanel {

    public Settings_UI() {
        createSettingsPanel();
    }

    public void createSettingsPanel() {
        this.setLayout(new BorderLayout(20, 20));
        this.setBorder(new Round_Panel_Border_Top_Left(15, new Color(240, 242, 245)));
        this.setOpaque(false);
        this.setBackground(new Color(240, 242, 245));

        JPanel headerPanel = UI_Styles.createHeaderPanel("System Settings");

        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(0, 50, 10, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        JLabel welcomeLabel = new JLabel("Application Configuration Center", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        content.add(welcomeLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.0;

        gbc.gridx = 0;
        JPanel gradingCard = GradingSystemPanel();
        gradingCard.setPreferredSize(new Dimension(200, 200));
        content.add(gradingCard, gbc);

        gbc.gridx = 1;
        JPanel shortcutCard = ShortcutPanel();
        shortcutCard.setPreferredSize(new Dimension(200, 200));
        content.add(shortcutCard, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        content.add(createInfoPanel(), gbc);

        JPanel topPanel = new JPanel(new BorderLayout(0, 20));
        topPanel.setBackground(new Color(240, 242, 245));
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(content, BorderLayout.CENTER);

        this.add(topPanel, BorderLayout.CENTER);
    }

    private JPanel ShortcutPanel() {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(getInsets()));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Title
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 15, 10, 15);
        JLabel title = new JLabel("Keyboard Shortcuts", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(55, 55, 55));
        card.add(title, gbc);

        // Separator
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 15, 15, 15);
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(230, 230, 230));
        card.add(separator, gbc);

        // Description label
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 15, 5, 15);
        JLabel descLabel = new JLabel(
                "<html>Use shortcuts for faster workflow</html>", SwingConstants.CENTER);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descLabel.setForeground(new Color(100, 100, 100));
        card.add(descLabel, gbc);

        // Button panel
        gbc.gridy = 3;
        gbc.insets = new Insets(30, 15, 15, 15);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        GridBagConstraints btnGbc = new GridBagConstraints();
        btnGbc.insets = new Insets(5, 5, 5, 5);

        JButton viewBtn = UI_Styles.createActionButton("📊 View Shortcuts", new Color(52, 152, 219), 180);
        viewBtn.addActionListener(e -> showKeyboardShortcuts());

        buttonPanel.add(viewBtn, btnGbc);
        card.add(buttonPanel, gbc);

        return card;
    }

    private JPanel GradingSystemPanel() {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(getInsets()));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Title
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 15, 10, 15);
        JLabel title = new JLabel("Grading System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(55, 55, 55));
        card.add(title, gbc);

        // Separator
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 15, 15, 15);
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(230, 230, 230));
        card.add(separator, gbc);

        // Description label
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 15, 5, 15);
        JLabel descLabel = new JLabel(
                "<html>View the grading scale of Application.</html>", SwingConstants.CENTER);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descLabel.setForeground(new Color(100, 100, 100));
        card.add(descLabel, gbc);

        // Button panel
        gbc.gridy = 3;
        gbc.insets = new Insets(30, 15, 15, 15);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        GridBagConstraints btnGbc = new GridBagConstraints();
        btnGbc.insets = new Insets(5, 5, 5, 5);

        JButton viewBtn = UI_Styles.createActionButton("📊 View Grade Scale", new Color(52, 152, 219), 180);
        viewBtn.addActionListener(e -> showGradingSystem());

        buttonPanel.add(viewBtn, btnGbc);
        card.add(buttonPanel, gbc);

        return card;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(240, 242, 245));
        infoPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Version info
        gbc.gridy = 0;
        JLabel versionLabel = new JLabel("Version 2.0 | RMS Result Management System", SwingConstants.CENTER);
        versionLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        versionLabel.setForeground(new Color(100, 100, 100));
        infoPanel.add(versionLabel, gbc);

        return infoPanel;
    }

    public void showGradingSystem() {
        String gradingInformation = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f8fafc; margin: 20px; color: #2d3748; }"
                +
                ".header { text-align: center; margin-bottom: 30px; padding-bottom: 20px; border-bottom: 2px solid #e2e8f0; }"
                +
                ".header h1 { color: #4a5568; font-size: 28px; margin: 0; }" +
                ".header p { color: #718096; font-size: 14px; margin-top: 5px; }" +
                ".section { margin-bottom: 30px; background: white; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.05); }"
                +
                ".section-title { color: #4a5568; font-size: 20px; font-weight: bold; margin-bottom: 15px; padding-bottom: 10px; border-bottom: 1px solid #e2e8f0; }"
                +
                "table { width: 100%; border-collapse: collapse; margin: 15px 0; }" +
                "th { background-color: #4a5568; color: white; padding: 12px; text-align: center; font-weight: 600; }" +
                "td { padding: 10px; text-align: center; border-bottom: 1px solid #e2e8f0; }" +
                "tr:nth-child(even) { background-color: #f7fafc; }" +
                ".grade-a { color: #38a169; font-weight: bold; }" +
                ".grade-b { color: #3182ce; font-weight: bold; }" +
                ".grade-c { color: #d69e2e; font-weight: bold; }" +
                ".grade-d { color: #ed8936; font-weight: bold; }" +
                ".grade-f { color: #ec3a0d; font-weight: bold; }" +
                ".notes { background-color: #d7e1f0; padding: 15px; border-radius: 8px; border-left: 4px solid #ed8936; margin-top: 20px; }"
                +
                ".notes-title { color: #2448e6; font-weight: bold; margin-bottom: 10px; }" +
                ".notes li { margin: 8px 0; color: black; }" +
                ".highlight { padding: 2px 6px; border-radius: 3px; font-weight: 500; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='header'>" +
                "<h1>📊 Grading System</h1>" +
                "<p>Academic Performance Evaluation Scale</p>" +
                "</div>" +

                "<div class='section'>" +
                "<div class='section-title'>COURSE GRADING SCALE</div>" +
                "<table>" +
                "<tr><th>Marks Range</th><th>Grade</th><th>Grade Points</th></tr>" +
                "<tr><td>85 - 100</td><td class='grade-a'>A </td><td>4.00</td></tr>" +
                "<tr><td>80 - 84</td><td class='grade-a'>A-</td><td>3.66</td></tr>" +
                "<tr><td>75 - 79</td><td class='grade-b'>B+</td><td>3.33</td></tr>" +
                "<tr><td>71 - 74</td><td class='grade-b'>B </td><td>3.00</td></tr>" +
                "<tr><td>68 - 70</td><td class='grade-b'>B-</td><td>2.66</td></tr>" +
                "<tr><td>64 - 67</td><td class='grade-c'>C+</td><td>2.33</td></tr>" +
                "<tr><td>61 - 63</td><td class='grade-c'>C </td><td>2.00</td></tr>" +
                "<tr><td>58 - 60</td><td class='grade-c'>C-</td><td>1.66</td></tr>" +
                "<tr><td>54 - 57</td><td class='grade-d'>D+</td><td>1.30</td></tr>" +
                "<tr><td>50 - 53</td><td class='grade-d'>D </td><td>1.00</td></tr>" +
                "<tr><td>0 - 49</td><td class='grade-f'>F </td><td>0.00</td></tr>" +
                "</table>" +
                "</div>" +

                "<div class='section'>" +
                "<div class='section-title'>OVERALL GPA GRADING SCALE</div>" +
                "<table>" +
                "<tr><th>GPA Range</th><th>Overall Grade</th></tr>" +
                "<tr><td>4.00</td><td class='grade-a'>A</td></tr>" +
                "<tr><td>3.66 - 3.99</td><td class='grade-a'>A-</td></tr>" +
                "<tr><td>3.33 - 3.65</td><td class='grade-b'>B+</td></tr>" +
                "<tr><td>3.00 - 3.32</td><td class='grade-b'>B</td></tr>" +
                "<tr><td>2.66 - 2.99</td><td class='grade-b'>B-</td></tr>" +
                "<tr><td>2.33 - 2.65</td><td class='grade-c'>C+</td></tr>" +
                "<tr><td>2.00 - 2.32</td><td class='grade-c'>C</td></tr>" +
                "<tr><td>1.66 - 1.99</td><td class='grade-c'>C-</td></tr>" +
                "<tr><td>1.30 - 1.65</td><td class='grade-d'>D+</td></tr>" +
                "<tr><td>1.00 - 1.29</td><td class='grade-d'>D</td></tr>" +
                "<tr><td>Below 1.00</td><td class='grade-f'>F</td></tr>" +
                "</table>" +
                "</div>" +

                "<div class='notes'>" +
                "<div class='notes-title'>📝 Important Notes</div>" +
                "<ul>" +
                "<li><span class='highlight'>Passing Marks:</span> 50 and above</li>" +
                "<li><span class='highlight'>GPA Calculation:</span> ∑(Grade Points * Credit Hours) ÷ Total Credit Hours</li>"
                +
                "<li><span class='highlight'>Overall Grade:</span> Based on Cumulative GPA</li>" +
                "<li><span class='highlight'>Uniform Application:</span> All student types use the same grading scale</li>"
                +
                "</ul>" +
                "</div>" +
                "</body>" +
                "</html>";

        UI_Styles.showInformation(RMS_Result_Management_System_UI.mainFrame, gradingInformation, "Grading System");
    }

    public void showKeyboardShortcuts() {
        String shortcutsHTML = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f8fafc; margin: 20px; color: #2d3748; }"
                +
                ".header { text-align: center; margin-bottom: 30px; padding-bottom: 20px; border-bottom: 2px solid #e2e8f0; }"
                +
                ".header h1 { color: #4a5568; font-size: 28px; margin: 0; }" +
                ".header p { color: #718096; font-size: 14px; margin-top: 5px; }" +
                ".section { margin-bottom: 30px; background: white; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.05); }"
                +
                ".section-title { color: #4a5568; font-size: 20px; font-weight: bold; margin-bottom: 20px; padding-bottom: 10px; border-bottom: 1px solid #e2e8f0; }"
                +
                ".shortcut-table { width: 100%; border-collapse: collapse; margin: 15px 0; }" +
                ".shortcut-table th { background-color: #4a5568; color: white; padding: 12px; text-align: center; font-weight: 600; }"
                +
                ".shortcut-table td { padding: 12px; text-align: center; border-bottom: 1px solid #e2e8f0; }" +
                ".shortcut-table tr:nth-child(even) { background-color: #f7fafc; }" +
                ".shortcut-table tr:hover { background-color: #edf2f7; }" +
                ".key-combo { font-family: 'Consolas', monospace; background: #4a5568; color: white; padding: 6px 12px; border-radius: 6px; font-weight: bold; display: inline-block; }"
                +
                ".key-action { color: #2d3748; font-size: 15px; }" +
                ".notes { background-color: #e6fffa; padding: 20px; border-radius: 8px; border-left: 4px solid #38b2ac; margin-top: 30px; }"
                +
                ".notes-title { color: #234e52; font-weight: bold; margin-bottom: 15px; font-size: 18px; }" +
                ".notes li { margin: 10px 0; color: #234e52; font-size: 14px; }" +
                ".highlight { background-color: #c6f6d5; padding: 2px 6px; border-radius: 3px; font-weight: 500; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='header'>" +
                "<h1>⌨️ Keyboard Shortcuts</h1>" +
                "<p>Boost your productivity with these time-saving shortcuts</p>" +
                "</div>" +

                "<div class='section'>" +
                "<div class='section-title'>Essential Keyboard Shortcuts</div>" +
                "<table class='shortcut-table'>" +
                "<tr><th>Shortcut</th><th>Action</th></tr>" +
                "<tr><td><span class='key-combo'>Ctrl + S</span></td><td class='key-action'>Save Data</td></tr>" +
                "<tr><td><span class='key-combo'>Ctrl + L</span></td><td class='key-action'>Load Data</td></tr>" +
                "<tr><td><span class='key-combo'>Ctrl + R</span></td><td class='key-action'>Refresh System</td></tr>" +
                "<tr><td><span class='key-combo'>Ctrl + A</span></td><td class='key-action'>About System</td></tr>" +
                "<tr><td><span class='key-combo'>Ctrl + E</span></td><td class='key-action'>Exit Application</td></tr>"
                +
                "</table>" +
                "</div>" +

                "<div class='notes'>" +
                "<div class='notes-title'>💡 Tips for Using Shortcuts</div>" +
                "<ul>" +
                "<li>Shortcuts work from anywhere in the application</li>" +
                "<li>Use <span class='highlight'>Ctrl+S</span> frequently to save your work</li>" +
                "<li><span class='highlight'>Ctrl+E</span> will prompt for confirmation before exiting</li>" +
                "</ul>" +
                "</div>" +
                "</body>" +
                "</html>";
                

        UI_Styles.showInformation(RMS_Result_Management_System_UI.mainFrame, shortcutsHTML, "KeyBoard Shortcuts");
    }
}