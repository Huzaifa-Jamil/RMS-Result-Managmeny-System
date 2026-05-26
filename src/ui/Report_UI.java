package ui;

import models.*;
import utils.*;
import java.awt.*;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.*;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Report_UI extends JPanel {

    public Report_UI() {
        createPDFReportPanel();
    }

    public void createPDFReportPanel() {
        this.setLayout(new BorderLayout(20, 20));
        this.setBorder(new Round_Panel_Border_Top_Left(15, new Color(240, 242, 245)));
        this.setOpaque(false);
        this.setBackground(new Color(240, 242, 245));

        JPanel headerPanel = UI_Styles.createHeaderPanel("Reports Generator");

        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(240, 242,
                        245), 1, true),
                new EmptyBorder(15, 15, 15, 15)));

        cardPanel.add(createStudentCard(), "student");
        cardPanel.add(createCourseCard(), "course");
        cardPanel.add(createInstructorCard(), "instructor");
        cardPanel.add(createStatisticsCard(), "statistics");

        JPanel navPanel = createNavigationPanel(cardPanel);

        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setBackground(new Color(240, 242, 245));
        contentPanel.add(navPanel, BorderLayout.NORTH);
        contentPanel.add(cardPanel, BorderLayout.CENTER);

        UI_Styles.makeFocusStealerRecursive(this);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createNavigationPanel(JPanel cardPanel) {
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        navPanel.setBackground(new Color(240, 242, 245));

        String[] navItems = { "Student Report", "Course Report", "Instructor Report", "System Statistics" };
        String[] cardNames = { "student", "course", "instructor", "statistics" };
        Color[] navColors = {
                new Color(155, 89, 182),
                new Color(52, 152, 219),
                new Color(230, 126, 34),
                new Color(39, 174, 96)
        };

        for (int i = 0; i < navItems.length; i++) {
            JButton navBtn = UI_Styles.createActionButton(navItems[i], navColors[i], 140);

            final String cardName = cardNames[i];
            navBtn.addActionListener(e -> {
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, cardName);

                for (int j = 0; j < navPanel.getComponentCount(); j++) {
                    Component comp = navPanel.getComponent(j);
                    if (comp instanceof JButton) {
                        comp.setBackground(navColors[j]);
                    }
                }
            });
            navPanel.add(navBtn);
        }

    if (navPanel.getComponentCount() > 0) {
        navPanel.getComponent(0).setBackground(new Color(41, 128, 185));
    }

    return navPanel;
}

private JPanel createStudentCard() {
    JPanel container = new JPanel(new BorderLayout());
    container.setBackground(Color.WHITE);

    // Header
    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBackground(Color.WHITE);
    headerPanel.setPreferredSize(new Dimension(0, 40));
    headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(224, 224, 224)));

    JLabel titleLabel = new JLabel("Student Performance Report");
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
    titleLabel.setForeground(new Color(51, 51, 51));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    headerPanel.add(titleLabel, BorderLayout.CENTER);

    container.add(headerPanel, BorderLayout.NORTH);

    // Form
    JPanel panel = UI_Styles.createFormPanel();

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(UI_Styles.createLabel("Select Student:"), gbc);

    RMS_Result_Management_System_UI.pdf_Report_Student_Combo = new JComboBox<>();
    UI_Styles.styleComboBox(RMS_Result_Management_System_UI.pdf_Report_Student_Combo);
    gbc.gridx = 1;
    gbc.gridy = 0;
    panel.add(RMS_Result_Management_System_UI.pdf_Report_Student_Combo, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(UI_Styles.createLabel("Recipient Email:"), gbc);

    JTextField studentemailField = UI_Styles.createTextField();
    gbc.gridx = 1;
    gbc.gridy = 1;
    panel.add(studentemailField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JButton generateBtn = UI_Styles
            .createActionButton("Generate & Send Student Report",
                    new Color(155, 89, 182), 120);
    generateBtn.addActionListener(e -> {
        String studentId = (String) RMS_Result_Management_System_UI.pdf_Report_Student_Combo.getSelectedItem();
        Student student = RMS_Result_Management_System_UI.students.search(studentId);
        sendEmail(studentemailField.getText(), Html_Files.getStudentDetails(student), "Student");
    });
    panel.add(generateBtn, gbc);

    container.add(panel, BorderLayout.CENTER);

    return container;
}

private JPanel createCourseCard() {
    JPanel container = new JPanel(new BorderLayout());
    container.setBackground(Color.WHITE);

    // Header
    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBackground(Color.WHITE);
    headerPanel.setPreferredSize(new Dimension(0, 40));
    headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(224, 224, 224)));

    JLabel titleLabel = new JLabel("Course Report");
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
    titleLabel.setForeground(new Color(51, 51, 51));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    headerPanel.add(titleLabel, BorderLayout.CENTER);

    container.add(headerPanel, BorderLayout.NORTH);

    // Form
    JPanel panel = UI_Styles.createFormPanel();

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Course selection
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(UI_Styles.createLabel("Select Course:"), gbc);

    RMS_Result_Management_System_UI.pdf_Report_Course_Combo = new JComboBox<>();
    UI_Styles.styleComboBox(RMS_Result_Management_System_UI.pdf_Report_Course_Combo);
    gbc.gridx = 1;
    gbc.gridy = 0;
    panel.add(RMS_Result_Management_System_UI.pdf_Report_Course_Combo, gbc);

    // Email field

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(UI_Styles.createLabel("Recipient Email:"), gbc);

    JTextField courseEmailField = UI_Styles.createTextField();
    gbc.gridx = 1;
    gbc.gridy = 1;
    panel.add(courseEmailField, gbc);

    // Generate button
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JButton generateBtn = UI_Styles
            .createActionButton("Generate & Send Course Report",
            new Color(52, 152, 219),120);
    generateBtn.addActionListener(e -> {
        String courseCode = (String) RMS_Result_Management_System_UI.pdf_Report_Course_Combo.getSelectedItem();
        Course course = RMS_Result_Management_System_UI.courses.search(courseCode);
        sendEmail(courseEmailField.getText(), Html_Files.getCourseDetails(course), "Course");
    });
    panel.add(generateBtn, gbc);

    container.add(panel, BorderLayout.CENTER);

    return container;
}

private JPanel createInstructorCard() {
    JPanel container = new JPanel(new BorderLayout());
    container.setBackground(Color.WHITE);

    // Header
    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBackground(Color.WHITE);
    headerPanel.setPreferredSize(new Dimension(0, 40));
    headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(224, 224, 224)));

    JLabel titleLabel = new JLabel("Instructor Performance Report");
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
    titleLabel.setForeground(new Color(51, 51, 51));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    headerPanel.add(titleLabel, BorderLayout.CENTER);

    container.add(headerPanel, BorderLayout.NORTH);

    // Form
    JPanel panel = UI_Styles.createFormPanel();

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(UI_Styles.createLabel("Select Instructor:"), gbc);

    RMS_Result_Management_System_UI.pdf_Report_Instructor_Combo = new JComboBox<>();
    UI_Styles.styleComboBox(RMS_Result_Management_System_UI.pdf_Report_Instructor_Combo);
    gbc.gridx = 1;
    gbc.gridy = 0;
    panel.add(RMS_Result_Management_System_UI.pdf_Report_Instructor_Combo, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(UI_Styles.createLabel("Recipient Email:"), gbc);
    JTextField instructorEmailField = UI_Styles.createTextField();
    gbc.gridx = 1;
    gbc.gridy = 1;
    panel.add(instructorEmailField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JButton generateBtn = UI_Styles.createActionButton("Generate & Send Instructor Report",
            new Color(230, 126, 34), 120);
    generateBtn.addActionListener(e -> {
        
        String instructorName = (String) RMS_Result_Management_System_UI.pdf_Report_Instructor_Combo.getSelectedItem();
        Course_Instructor instructor = RMS_Result_Management_System_UI.instructors.search(instructorName);
        sendEmail(instructorEmailField.getText(), Html_Files.getInstructorDetails(instructor), "Instructors");
    });
    panel.add(generateBtn, gbc);

    container.add(panel, BorderLayout.CENTER);

    return container;
}

private JPanel createStatisticsCard() {
    JPanel container = new JPanel(new BorderLayout());
    container.setBackground(Color.WHITE);

    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBackground(Color.WHITE);
    headerPanel.setPreferredSize(new Dimension(0, 40));
    headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(224, 224, 224)));

    JLabel titleLabel = new JLabel("System statistics Report");
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
    titleLabel.setForeground(new Color(51, 51, 51));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    headerPanel.add(titleLabel, BorderLayout.CENTER);

    container.add(headerPanel, BorderLayout.NORTH);

    JPanel panel = UI_Styles.createFormPanel();

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    panel.add(UI_Styles.createLabel("Recipient Email:"), gbc);

    JTextField statsEmailField = UI_Styles.createTextField();
    gbc.gridx = 1;
    gbc.gridy = 0;
    panel.add(statsEmailField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JButton generateBtn = UI_Styles.createActionButton("Generate & Send Statistics Report",
            new Color(39, 174, 96), 120);
    generateBtn.addActionListener(e -> {

        sendEmail(statsEmailField.getText(), Html_Files.getStatisticsDetails(), "Statistics");
    });
    panel.add(generateBtn, gbc);

    container.add(panel, BorderLayout.CENTER);

    return container;
    }

    
    private void sendEmail(String toEmail, String htmlContent, String Type) {
        try {
            Properties config = new Properties();
            config.load(new java.io.FileInputStream("data/config.properties"));
            String fromEmail = config.getProperty("EMAIL");
            String appPassword = config.getProperty("APP_PASSWORD");

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
                @Override
                protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new jakarta.mail.PasswordAuthentication(fromEmail, appPassword);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "RMS Result Managment System"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Details of " + Type + " by RMS");

            // send as html
            message.setContent(htmlContent, "text/html; charset=utf-8");

            Transport.send(message);
            UI_Styles.showSideNotifications(RMS_Result_Management_System_UI.mainFrame, "Email sent successfully");
    

        } catch (Exception e) {
            UI_Styles.showSideErrors(RMS_Result_Management_System_UI.mainFrame, "Error sending Email");
            System.err.println(e.getMessage());

        }
    }
}