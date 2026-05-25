package RMSResultManagementSystem;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import RecordList.RecordList;
import ResultCalculator.ResultCalculator;
import DataStore.DataStore;
import Course.Course;
import CourseInstructor.CourseInstructor;
import Student.Student;
import Student.ArtsStudent;
import Student.ScienceStudent;
import Student.EngineeringStudent;
import Transcript.Transcript;
import ResultEntry.ResultEntry;

public class RMSResultManagementSystem extends JFrame {

    // Data stores
    private DataStore<Student> studentStore = new DataStore<>();
    private DataStore<Course> courseStore = new DataStore<>();
    private DataStore<CourseInstructor> instructorStore = new DataStore<>();

    private RecordList<Student> students = new RecordList<>();
    private RecordList<Course> courses = new RecordList<>();
    private RecordList<CourseInstructor> instructors = new RecordList<>();

    // Main components
    private JTabbedPane tabbedPane;

    // Student Management Components
    private JTable studentTable;
    private DefaultTableModel studentTableModel;
    private JTextField studentIdField;
    private JTextField studentNameField;
    private JTextField studentProgramField;
    private JComboBox<String> studentTypeCombo;

    // Course Management Components
    private JTable courseTable;
    private DefaultTableModel courseTableModel;
    private JTextField courseCodeField;
    private JTextField courseTitleField;
    private JTextField creditHoursField;
    private JComboBox<String> courseInstructorCombo;

    // Instructor Management Components
    private JTable instructorTable;
    private DefaultTableModel instructorTableModel;
    private JTextField instructorNameField;
    private JTextField instructorQualificationField;
    private JTextField instructorProgramField;

    // Result Management Components
    private JTable resultTable;
    private DefaultTableModel resultTableModel;
    private JComboBox<String> resultStudentCombo;
    private JComboBox<String> resultCourseCombo;
    private JTextField marksField;

    // Reports Management Components
    private JComboBox<String> reportStudentCombo;
    private JComboBox<String> reportCourseCombo;

    // PDF Report Components
    private JComboBox<String> PdfReportStudentCombo;
    private JComboBox<String> PdfReportCourseCombo;
    private JComboBox<String> PdfReportInstructorCombo;
    private JTextField emailField;


    // Argument Constructor
    public RMSResultManagementSystem() {
        initializeGUI();
        loadDataFromFilesWithoutDialogueBox();
        setupEventHandlers();
    }

    public void initializeGUI() {
        setTitle("RMS Result Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("Icons/logo.webp");
        setIconImage(icon.getImage());

        // a try and catch block to give a modren look gui
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main control pannel
        JPanel controlPanel = createControlPanel();

        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Add tabs to J
        tabbedPane.addTab("Students", createStudentPanel());
        tabbedPane.addTab("Courses", createCoursePanel());
        tabbedPane.addTab("Instructors", createInstructorPanel());
        tabbedPane.addTab("Results", createResultPanel());
        tabbedPane.addTab("Reports", createReportsPanel());
        tabbedPane.addTab("Statistics", createStatisticsPanel());
        tabbedPane.addTab("PDF Reports", createPDFReportPanel());

        // Add components to frame
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    public JPanel createControlPanel() {
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setLayout(flowLayout);
        panel.setBorder(new TitledBorder("System Controls"));
        panel.setBackground(new Color(240, 240, 240));

        // JButtons for control panel
        JButton saveBtn = new JButton("Save All Data");
        JButton loadBtn = new JButton("Load Data");
        JButton gradingBtn = new JButton("Grading System");
        JButton exitBtn = new JButton("Exit");

        // Save Data Button
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        saveBtn.setPreferredSize(new Dimension(140, 35));
        saveBtn.setBackground(Color.GREEN);
        saveBtn.setOpaque(true);
        saveBtn.setBorderPainted(false);
        saveBtn.setContentAreaFilled(true);
        saveBtn.setForeground(Color.BLACK);
        saveBtn.addActionListener(e -> saveAllData());

        // Load Data Button
        loadBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        loadBtn.setPreferredSize(new Dimension(140, 35));
        loadBtn.setBackground(new Color(33, 150, 243));
        loadBtn.setOpaque(true);
        loadBtn.setBorderPainted(false);
        loadBtn.setContentAreaFilled(true);
        loadBtn.setForeground(Color.BLACK);
        loadBtn.addActionListener(e -> loadDataFromFiles());

        // Grading System Button
        gradingBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gradingBtn.setPreferredSize(new Dimension(140, 35));
        gradingBtn.setBackground(new Color(156, 39, 176));
        gradingBtn.setOpaque(true);
        gradingBtn.setBorderPainted(false);
        gradingBtn.setContentAreaFilled(true);
        gradingBtn.setForeground(Color.BLACK);
        gradingBtn.addActionListener(e -> showGradingSystem());

        // Exit Button
        exitBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        exitBtn.setPreferredSize(new Dimension(140, 35));
        exitBtn.setBackground(new Color(255, 59, 48));
        exitBtn.setOpaque(true);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(true);
        exitBtn.setForeground(Color.BLACK);
        exitBtn.addActionListener(e -> exitFromSystem());

        panel.add(saveBtn);
        panel.add(loadBtn);
        panel.add(gradingBtn);
        panel.add(exitBtn);

        return panel;
    }

    private void exitFromSystem() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Do you want to save data before exiting?",
                "Exit Confirmation",
                JOptionPane.YES_NO_CANCEL_OPTION);

        switch (choice) {
            case (JOptionPane.YES_OPTION): {
                saveAllData();
                System.exit(0);
            }
                break;
            case (JOptionPane.NO_OPTION): {
                System.exit(0);
                break;
            }
        }
    }

    public void showGradingSystem() {
        // Create the entire grading information as one long string
        String gradingInformation = "══════════════════════════════════════════════════════════\n" +
                "                    GRADING SYSTEM\n" +
                "══════════════════════════════════════════════════════════\n\n" +
                "COURSE GRADING SCALE (Based on Marks Obtained):\n" +
                "──────────────────────────────────────────────────────────\n" +
                "Marks Range      Grade           Grade Points    \n" +
                "──────────────────────────────────────────────────────────\n" +
                "85 - 100         A               4.00           \n" +
                "80 - 84          A-              3.66           \n" +
                "75 - 79          B+              3.33           \n" +
                "71 - 74          B               3.00           \n" +
                "68 - 70          B-              2.66           \n" +
                "64 - 67          C+              2.33           \n" +
                "61 - 63          C               2.00           \n" +
                "58 - 60          C-              1.66           \n" +
                "54 - 57          D+              1.30           \n" +
                "50 - 53          D               1.00           \n" +
                "0 - 49           F               0.00           \n" +
                "──────────────────────────────────────────────────────────\n\n" +
                "OVERALL GPA GRADING SCALE:\n" +
                "──────────────────────────────────────────────────────────\n" +
                "GPA Range        Overall Grade   \n" +
                "──────────────────────────────────────────────────────────\n" +
                "4.00             A               \n" +
                "3.66 - 3.99      A-              \n" +
                "3.33 - 3.65      B+              \n" +
                "3.00 - 3.32      B               \n" +
                "2.66 - 2.99      B-              \n" +
                "2.33 - 2.65      C+              \n" +
                "2.00 - 2.32      C               \n" +
                "1.66 - 1.99      C-              \n" +
                "1.30 - 1.65      D+              \n" +
                "1.00 - 1.29      D               \n" +
                "Below 1.00       F               \n" +
                "──────────────────────────────────────────────────────────\n\n" +
                "IMPORTANT NOTES:\n" +
                "──────────────────────────────────────────────────────────\n" +
                "• Passing Marks: 50 and above\n" +
                "• GPA is calculated as: Sum(Grade Points × Credit Hours) / Total Credit Hours\n" +
                "• Overall Grade is based on Cumulative GPA\n" +
                "• All student types (Arts, Science, Engineering) use the same grading scale\n" +
                "══════════════════════════════════════════════════════════\n";

        // Create text area to display the information
        JTextArea textArea = new JTextArea(gradingInformation);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);

        // Add scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(650, 500));

        // Show the dialog
        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Grading System Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel createStudentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Top panel for form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new TitledBorder("Student Operations"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Student ID Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Student ID (String) :"), gbc);
        gbc.gridx = 1;
        studentIdField = new JTextField(20);
        formPanel.add(studentIdField, gbc);

        // Name Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Name (String) :"), gbc);
        gbc.gridx = 1;
        studentNameField = new JTextField(20);
        formPanel.add(studentNameField, gbc);

        // Program Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Program (String) :"), gbc);
        gbc.gridx = 1;
        studentProgramField = new JTextField(20);
        formPanel.add(studentProgramField, gbc);

        // Student Type Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Student Type (Select) :"), gbc);
        gbc.gridx = 1;
        studentTypeCombo = new JComboBox<>(new String[] { "Arts Student", "Science Student", "Engineering Student" });
        formPanel.add(studentTypeCombo, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Student");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");
        JButton viewDetailsBtn = new JButton("View Details");
        JButton studentByTypeBtn = new JButton("Student by Type");

        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        clearBtn.addActionListener(e -> clearStudentForm());
        viewDetailsBtn.addActionListener(e -> viewStudentDetails());
        studentByTypeBtn.addActionListener(e -> viewStudentByType());

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(viewDetailsBtn);
        buttonPanel.add(studentByTypeBtn);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        // Table of student tab
        String[] student = { "Student ID", "Name", "Program", "Type", "GPA" };
        studentTableModel = new DefaultTableModel(student, 0) {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable = new JTable(studentTableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadStudentToForm();
            }
        });
        JScrollPane tableScroll = new JScrollPane(studentTable);
        tableScroll.setBorder(new TitledBorder("All Students"));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);

        return panel;
    }

    public JPanel createCoursePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new TitledBorder("Course Operations"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Course Code
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Course Code (String) :"), gbc);
        gbc.gridx = 1;
        courseCodeField = new JTextField(20);
        formPanel.add(courseCodeField, gbc);

        // Title
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Title (String) :"), gbc);
        gbc.gridx = 1;
        courseTitleField = new JTextField(20);
        formPanel.add(courseTitleField, gbc);

        // Credit Hours
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Credit Hours (Double) :"), gbc);
        gbc.gridx = 1;
        creditHoursField = new JTextField(20);
        formPanel.add(creditHoursField, gbc);

        // Instructor
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Instructor (Select) :"), gbc);
        gbc.gridx = 1;
        courseInstructorCombo = new JComboBox<>();
        courseInstructorCombo.addItem("None");
        formPanel.add(courseInstructorCombo, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Course");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");
        JButton viewDetailsBtn = new JButton("View Details");

        addBtn.addActionListener(e -> addCourse());
        updateBtn.addActionListener(e -> updateCourse());
        deleteBtn.addActionListener(e -> deleteCourse());
        clearBtn.addActionListener(e -> clearCourseForm());
        viewDetailsBtn.addActionListener(e -> viewCourseDetails());

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(viewDetailsBtn);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        String[] course = { "Course Code", "Title", "Credit Hours", "Instructor" };
        courseTableModel = new DefaultTableModel(course, 0) {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        courseTable = new JTable(courseTableModel);
        courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadCourseToForm();
            }
        });
        JScrollPane tableScroll = new JScrollPane(courseTable);
        tableScroll.setBorder(new TitledBorder("All Courses"));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);

        return panel;
    }

    public JPanel createInstructorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new TitledBorder("Instructor Operations"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name (String) :"), gbc);
        gbc.gridx = 1;
        instructorNameField = new JTextField(20);
        formPanel.add(instructorNameField, gbc);

        // Qualification
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Qualification (String) :"), gbc);
        gbc.gridx = 1;
        instructorQualificationField = new JTextField(20);
        formPanel.add(instructorQualificationField, gbc);

        // Program
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Program (String) :"), gbc);
        gbc.gridx = 1;
        instructorProgramField = new JTextField(20);
        formPanel.add(instructorProgramField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Instructor");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");
        JButton viewDetailsBtn = new JButton("View Details");

        addBtn.addActionListener(e -> addCourseInstructor());
        updateBtn.addActionListener(e -> updateCourseInstructor());
        deleteBtn.addActionListener(e -> deleteCourseInstructor());
        clearBtn.addActionListener(e -> clearCourseInstructorForm());
        viewDetailsBtn.addActionListener(e -> viewCourseInstructorDetails());

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(viewDetailsBtn);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        // Table
        String[] instructor = { "Name", "Qualification", "Program", "Courses Taught" };
        instructorTableModel = new DefaultTableModel(instructor, 0) {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        instructorTable = new JTable(instructorTableModel);
        instructorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        instructorTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadInstructorToForm();
            }
        });

        JScrollPane tableScroll = new JScrollPane(instructorTable);
        tableScroll.setBorder(new TitledBorder("All Instructors"));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);

        return panel;
    }

    public JPanel createResultPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new TitledBorder("Result Operations"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Student
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Student (Select) :"), gbc);
        gbc.gridx = 1;
        resultStudentCombo = new JComboBox<>();
        formPanel.add(resultStudentCombo, gbc);

        // Course
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Course (Select) :"), gbc);
        gbc.gridx = 1;
        resultCourseCombo = new JComboBox<>();
        formPanel.add(resultCourseCombo, gbc);

        // Marks
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Marks (0-100) :"), gbc);
        gbc.gridx = 1;
        marksField = new JTextField(20);
        formPanel.add(marksField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Result");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");
        JButton viewTranscriptBtn = new JButton("View Transcript");
        JButton calculateGPABtn = new JButton("Calculate GPA");

        addBtn.addActionListener(e -> addResult());
        updateBtn.addActionListener(e -> updateResult());
        deleteBtn.addActionListener(e -> deleteResult());
        clearBtn.addActionListener(e -> clearResultForm());
        viewTranscriptBtn.addActionListener(e -> viewTranscript());
        calculateGPABtn.addActionListener(e -> calculateGPA());

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(viewTranscriptBtn);
        buttonPanel.add(calculateGPABtn);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        // Table
        String[] result = { "Student ID", "Student Name", "Course Code", "Course Title", "Marks", "Grade",
                "Grade Points" };
        resultTableModel = new DefaultTableModel(result, 0) {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        resultTable = new JTable(resultTableModel);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadResultToForm();
            }
        });
        JScrollPane tableScroll = new JScrollPane(resultTable);
        tableScroll.setBorder(new TitledBorder("All Results"));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);

        return panel;
    }

    public JPanel createReportsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new TitledBorder("Generate Reports"));

        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 10, 15));

        contentPanel.add(new JLabel("Select Student:"));
        reportStudentCombo = new JComboBox<>();
        contentPanel.add(reportStudentCombo);

        contentPanel.add(new JLabel("Select Course:"));
        reportCourseCombo = new JComboBox<>();
        contentPanel.add(reportCourseCombo);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBorder(new TitledBorder("Actions"));

        JButton singleStudentBtn = new JButton("Generate Student Report");
        singleStudentBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        singleStudentBtn.addActionListener(e -> generateSingleStudentReport());

        JButton singleCourseBtn = new JButton("Generate Course Report");
        singleCourseBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        singleCourseBtn.addActionListener(e -> generateSingleCourseReport());

        buttonPanel.add(singleStudentBtn);
        buttonPanel.add(singleCourseBtn);

        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createStatisticsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new TitledBorder("System Statistics Operations"));

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBorder(new TitledBorder("Actions"));

        JButton viewStatsBtn = new JButton("View System Statistics");
        viewStatsBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        viewStatsBtn.addActionListener(e -> displayStatistics());

        buttonPanel.add(viewStatsBtn);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    public JPanel createPDFReportPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Student report pannel
        JPanel studentReportPanel = new JPanel(new GridBagLayout());
        studentReportPanel.setBorder(new TitledBorder("Student PDF Operations"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Student PDF Report
        gbc.gridx = 0;
        gbc.gridy = 0;
        studentReportPanel.add(new JLabel("Select Student (DropDown):"), gbc);
        gbc.gridx = 1;
        PdfReportStudentCombo = new JComboBox<>();
        studentReportPanel.add(PdfReportStudentCombo, gbc);

        // Email for student report
        gbc.gridx = 0;
        gbc.gridy = 1;
        studentReportPanel.add(new JLabel("Email (String):"), gbc);
        gbc.gridx = 1;
        JTextField studentemailField = new JTextField(20);
        studentReportPanel.add(studentemailField, gbc);

        // Button for student report
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton generatePDFForStudentBtn = new JButton("Send Report via Gmail");
        generatePDFForStudentBtn.addActionListener(e -> generateandsendPDFViaGmail((String) PdfReportStudentCombo.getSelectedItem(),"Student", 
                studentemailField.getText()));
        studentReportPanel.add(generatePDFForStudentBtn, gbc);




        // Course report pannel
        JPanel courseReportPanel = new JPanel(new GridBagLayout());
        courseReportPanel.setBorder(new TitledBorder("Course PDF Operations"));
        GridBagConstraints gbd = new GridBagConstraints();
        gbd.insets = new Insets(5, 5, 5, 5);
        gbd.anchor = GridBagConstraints.WEST;

        // Course PDF Report
        gbd.gridx = 0;
        gbd.gridy = 0;
        courseReportPanel.add(new JLabel("Select Course (DropDown):"), gbd);
        gbd.gridx = 1;
        PdfReportCourseCombo = new JComboBox<>();
        courseReportPanel.add(PdfReportCourseCombo, gbd);

        // Email for  Course report
        gbd.gridx = 0;
        gbd.gridy = 1;
        courseReportPanel.add(new JLabel("Email (String):"), gbd);
        gbd.gridx = 1;
        JTextField courseEmailField = new JTextField(20);
        courseReportPanel.add(courseEmailField, gbd);

        // Button for Course report
        gbd.gridx = 1;
        gbd.gridy = 2;
        gbd.gridwidth = 2;
        JButton generatePDFForCourseBtn = new JButton("Send Report via Gmail");
        generatePDFForCourseBtn.addActionListener(e -> generateandsendPDFViaGmail((String) PdfReportCourseCombo.getSelectedItem(),"Course", 
                courseEmailField.getText()));
        courseReportPanel.add(generatePDFForCourseBtn, gbd);




        // Instructor report pannel
        JPanel instructorReportPanel = new JPanel(new GridBagLayout());
        instructorReportPanel.setBorder(new TitledBorder("Instructor PDF Operations"));
        GridBagConstraints gbe = new GridBagConstraints();
        gbe.insets = new Insets(5, 5, 5, 5);
        gbe.anchor = GridBagConstraints.WEST;

        // Instructor PDF Report
        gbe.gridx = 0;
        gbe.gridy = 0;
        instructorReportPanel.add(new JLabel("Select Instructor (DropDown):"), gbe);
        gbe.gridx = 1;
        PdfReportInstructorCombo = new JComboBox<>();
        PdfReportInstructorCombo.addItem("None");
        instructorReportPanel.add(PdfReportInstructorCombo, gbe);

        // Email for Instructor report
        gbe.gridx = 0;
        gbe.gridy = 1;
        instructorReportPanel.add(new JLabel("Email (String):"), gbe);
        gbe.gridx = 1;
        JTextField instructorEmailField = new JTextField(20);
        instructorReportPanel.add(instructorEmailField, gbe);

        // Button for Instructor report
        gbe.gridx = 1;
        gbe.gridy = 2;
        gbe.gridwidth = 2;
        JButton generatePDFForInstructorBtn = new JButton("Send Report via Gmail");
        generatePDFForInstructorBtn.addActionListener(e -> generateandsendPDFViaGmail((String) PdfReportInstructorCombo.getSelectedItem(),"Instructor", 
                instructorEmailField.getText()));
        instructorReportPanel.add(generatePDFForInstructorBtn, gbe);




        // Statisticts PDF Report
        JPanel StatististsReportPanel = new JPanel(new GridBagLayout());
        StatististsReportPanel.setBorder(new TitledBorder("Statistics PDF Operations"));
        GridBagConstraints gbf = new GridBagConstraints();
        gbf.insets = new Insets(10, 10, 10, 10);
        gbf.anchor = GridBagConstraints.WEST;

        // Email for Statisticts report
        gbf.gridx = 0;
        gbf.gridy = 0;
        StatististsReportPanel.add(new JLabel("Email (String):"), gbf);
        gbf.gridx = 1;
        JTextField statistcsEmailField = new JTextField(20);
        StatististsReportPanel.add(statistcsEmailField, gbf);

        // Button for statistics report
        gbf.gridx = 1;
        gbf.gridy = 1;
        gbf.gridwidth = 2;
        JButton generatePDFForStatisticsBtn = new JButton("Send Report via Gmail");
        generatePDFForStatisticsBtn.addActionListener(e -> generateandsendPDFViaGmail("Statistics","Statistics", 
                statistcsEmailField.getText()));
        StatististsReportPanel.add(generatePDFForStatisticsBtn, gbf);

        panel.add(studentReportPanel);
        panel.add(courseReportPanel);
        panel.add(instructorReportPanel);
        panel.add(StatististsReportPanel);
        panel.add(Box.createVerticalStrut(10));

        return panel;
    }


    public void viewStudentByType() {
        try {
            String type = (String) studentTypeCombo.getSelectedItem();
            ArrayList<Student> studentbytype= new ArrayList<>();


            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getStudentType().equals(type)) {
                    studentbytype.add(students.get(i));
                }
            }

            if (studentbytype.size() == 0) {
                JOptionPane.showMessageDialog(this,
                        "No students found for the selected type !!",
                        "Student By Type",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            else {
                for (int i = 0; i < studentbytype.size(); i++) {
                    String transcript = "";

                    transcript += "══════════════════════════════════════════════════════════\n";
                    transcript += "      Transcript Of " + type + " \n";
                    transcript += "══════════════════════════════════════════════════════════\n\n";

                    transcript += "Student ID: " + studentbytype.get(i).getStudentID() + "\n";
                    transcript += "Name: " + studentbytype.get(i).getName() + "\n";
                    transcript += "Program: " + studentbytype.get(i).getProgram() + "\n";
                    transcript += "Type: " + studentbytype.get(i).getStudentType() + "\n";
                    transcript += "Percentage: " + studentbytype.get(i).calculatePercentage() + "\n";
                    transcript += String.format("GPA: %.2f\n", studentbytype.get(i).calculateGPA());
                    transcript += "Grade: " + studentbytype.get(i).calculateGrade() + "\n\n";

                    transcript += String.format("%-15s %-15s %-8s %-8s %-12s\n",
                            "Course Code", "Title", "Marks", "Grade", " Course Grade Points");

                    transcript += "───────────────────────────────────────────────────────────────────────────\n";
                    if (studentbytype.get(i).getTranscript().getResultEntry().size() == 0) {
                        transcript += "No results available.\n";
                    }
                    else {
                        for (int j = 0; j < studentbytype.get(i).getTranscript().getResultEntry().size(); j++) {
                            ResultEntry entry = studentbytype.get(i).getTranscript().getResultEntry().get(j);
                            Course course = entry.getCourse();

                            transcript += String.format("%-15s %-15s %-8.0f %-8s %-12.2f\n",
                                    course.getCourseCode(),
                                    course.getTitle().length() > 30
                                            ? course.getTitle().substring(0, 27) + "..."
                                            : course.getTitle(),
                                    entry.getMarksObtained(),
                                    entry.getGrade(),
                                    entry.getGradePoints());
                        }
                    }
                    

                    transcript += "══════════════════════════════════════════════════════════\n";

                    JTextArea textArea = new JTextArea(transcript);
                    textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                    textArea.setEditable(false);

                    JOptionPane.showMessageDialog(this,
                            textArea,
                            "Student By Type", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,
                        "Error: " + e.getMessage(),
                        "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void addStudent() {
        try {
            String studentId = studentIdField.getText().trim();
            String name = studentNameField.getText().trim();
            String program = studentProgramField.getText().trim();
            String type = (String) studentTypeCombo.getSelectedItem();

            if (studentId.isEmpty() || name.isEmpty() || program.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields !!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getStudentID().equals(studentId)) {
                    JOptionPane.showMessageDialog(this, "Student ID already exists !!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Student student;
            Transcript transcript = new Transcript();

            switch (type) {
                case ("Arts Student"): {
                    student = new ArtsStudent(studentId, name, program, transcript);
                }
                    break;
                case ("Science Student"): {
                    student = new ScienceStudent(studentId, name, program, transcript);
                }
                    break;
                case "Engineering Student": {
                    student = new EngineeringStudent(studentId, name, program, transcript);
                }
                    break;
                default: {
                    student = new ArtsStudent(studentId, name, program, transcript);
                }
            }

            students.add(student);
            studentTableModel.addRow(new Object[] {
                    studentId,
                    name,
                    program,
                    type,
                    "0.00"
            });

            // Clear form
            clearStudentForm();
            updateStudentDropdown();
            updateCourseDropdown();
            updateInstructorDropdown();

            JOptionPane.showMessageDialog(this,
                    "Student added successfully !!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student to update !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String studentId = studentIdField.getText().trim();
            String name = studentNameField.getText().trim();
            String program = studentProgramField.getText().trim();
            String type = (String) studentTypeCombo.getSelectedItem();

            // Validate
            if (studentId.isEmpty() || name.isEmpty() || program.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = students.get(selectedRow);
            student.setStudentID(studentId);
            student.setName(name);
            student.setProgram(program);

            studentTableModel.setValueAt(studentId, selectedRow, 0);
            studentTableModel.setValueAt(name, selectedRow, 1);
            studentTableModel.setValueAt(program, selectedRow, 2);
            studentTableModel.setValueAt(type, selectedRow, 3);

            clearStudentForm();
            updateStudentDropdown();
            updateCourseDropdown();
            updateInstructorDropdown();

            JOptionPane.showMessageDialog(this,
                    "Student updated successfully !!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student to delete !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String studentId = (String) studentTableModel.getValueAt(selectedRow, 0);

        students.remove(studentId);

        studentTableModel.removeRow(selectedRow);

        updateStudentDropdown();
        updateCourseDropdown();
        updateInstructorDropdown();

        JOptionPane.showMessageDialog(this,
                "Student deleted successfully !!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

    }

    private void loadStudentToForm() {
        try {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow >= 0) {
                Student student = students.get(selectedRow);
                studentIdField.setText(student.getStudentID());
                studentNameField.setText(student.getName());
                studentProgramField.setText(student.getProgram());

                String type = student.getStudentType();
                if (type.equals("Arts Student")) {
                    studentTypeCombo.setSelectedIndex(0);
                } else if (type.equals("Science Student")) {
                    studentTypeCombo.setSelectedIndex(1);
                } else if (type.equals("Engineering Student")) {
                    studentTypeCombo.setSelectedIndex(2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearStudentForm() {
        studentIdField.setText("");
        studentNameField.setText("");
        studentProgramField.setText("");
        studentTypeCombo.setSelectedIndex(0);
        studentTable.clearSelection();
    }

    private void viewStudentDetails() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student first !!",
                    "No Student Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Student student = students.get(selectedRow);
            String studentDetails = "<=== STUDENT DETAILS ===>\n\n" +
                    "Student ID: " + student.getStudentID() + "\n" +
                    "Name: " + student.getName() + "\n" +
                    "Program: " + student.getProgram() + "\n" +
                    "Type: " + student.getStudentType() + "\n" +
                    "GPA: " + String.format("%.2f", student.calculateGPA()) + "\n" +
                    "Total Marks: " + student.calculateTotal() + "\n" +
                    "Percentage: " + String.format("%.2f", student.calculatePercentage()) + "%\n" +
                    "Grade: " + student.calculateGrade() + "\n";

            JOptionPane.showMessageDialog(this,
                    studentDetails,
                    "Student Details",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCourse() {
        try {
            String courseCode = courseCodeField.getText().trim();
            String title = courseTitleField.getText().trim();
            String creditHoursString = creditHoursField.getText().trim();
            String instructorName = (String) courseInstructorCombo.getSelectedItem();

            // Validate
            if (courseCode.isEmpty() || title.isEmpty() || creditHoursString.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields !!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double creditHours;
            try {
                creditHours = Double.parseDouble(creditHoursString);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Credit hours must be a Double !!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getCourseCode().equals(courseCode)) {
                    JOptionPane.showMessageDialog(this,
                            "Course code already exists !!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            CourseInstructor instructor = null;
            if (!instructorName.equals("None")) {
                for (int i = 0; i < instructors.size(); i++) {
                    if (instructors.get(i).getName().equals(instructorName)) {
                        instructor = instructors.get(i);
                        break;
                    }
                }
            }

            Course course = new Course(title, courseCode, creditHours, instructor);
            courses.add(course);

            courseTableModel.addRow(new Object[] {
                    courseCode,
                    title,
                    creditHours,
                    instructorName
            });

            clearCourseForm();
            updateStudentDropdown();
            updateCourseDropdown();
            updateInstructorDropdown();

            JOptionPane.showMessageDialog(this,
                    "Course added successfully !!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCourse() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a course to update !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String courseCode = courseCodeField.getText().trim();
            String title = courseTitleField.getText().trim();
            String creditHoursString = creditHoursField.getText().trim();
            String instructorName = (String) courseInstructorCombo.getSelectedItem();

            if (courseCode.isEmpty() || title.isEmpty() || creditHoursString.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields! !",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double creditHours = Double.parseDouble(creditHoursString);

            Course course = courses.get(selectedRow);
            course.setCourseCode(courseCode);
            course.setTitle(title);
            course.setCreditHours(creditHours);

            CourseInstructor instructor = null;

            if (!instructorName.equals("None")) {
                for (int i = 0; i < instructors.size(); i++) {
                    if (instructors.get(i).getName().equals(instructorName)) {
                        instructor = instructors.get(i);
                        break;
                    }
                }
            }
            course.setCourseInstructor(instructor);

            courseTableModel.setValueAt(courseCode, selectedRow, 0);
            courseTableModel.setValueAt(title, selectedRow, 1);
            courseTableModel.setValueAt(creditHours, selectedRow, 2);
            courseTableModel.setValueAt(instructorName, selectedRow, 3);

            clearCourseForm();
            updateStudentDropdown();
            updateCourseDropdown();
            updateInstructorDropdown();

            JOptionPane.showMessageDialog(this,
                    "Course updated successfully !!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCourse() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a course to delete !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String courseCode = (String) courseTableModel.getValueAt(selectedRow, 0);
        courses.remove(courseCode);
        courseTableModel.removeRow(selectedRow);

        clearCourseForm();
        updateStudentDropdown();
        updateCourseDropdown();
        updateInstructorDropdown();

        JOptionPane.showMessageDialog(this,
                "Course deleted successfully !!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadCourseToForm() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow != -1) {
            courseCodeField.setText((String) courseTableModel.getValueAt(selectedRow, 0));
            courseTitleField.setText((String) courseTableModel.getValueAt(selectedRow, 1));
            creditHoursField.setText(courseTableModel.getValueAt(selectedRow, 2).toString());
            courseInstructorCombo.setSelectedItem(courseTableModel.getValueAt(selectedRow, 3));
        }
    }

    private void clearCourseForm() {
        courseCodeField.setText("");
        courseTitleField.setText("");
        creditHoursField.setText("");
        courseInstructorCombo.setSelectedIndex(0);
        courseTable.clearSelection();
    }

    private void viewCourseDetails() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a course first !!",
                    "No Course Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Course course = courses.get(selectedRow);
            String courseDetails = "<=== COURSE DETAILS ===>\n\n" +
                    "Course Code: " + course.getCourseCode() + "\n" +
                    "Title: " + course.getTitle() + "\n" +
                    "Credit Hours: " + course.getCreditHours() + "\n" +
                    "Instructor: "
                    + (course.getCourseInstructor() != null ? course.getCourseInstructor().getName() : "None") + "\n";

            JOptionPane.showMessageDialog(this,
                    courseDetails,
                    "Course Details",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCourseInstructor() {
        try {
            String name = instructorNameField.getText().trim();
            String qualification = instructorQualificationField.getText().trim();
            String program = instructorProgramField.getText().trim();

            if (name.isEmpty() || qualification.isEmpty() || program.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields !!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < instructors.size(); i++) {
                CourseInstructor instructor = instructors.get(i);
                if (instructor.getName().equals(name)) {
                    JOptionPane.showMessageDialog(this,
                            "Instructor already exists !!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            CourseInstructor courseInstructor = new CourseInstructor(name, qualification, program);
            instructors.add(courseInstructor);

            instructorTableModel.addRow(new Object[] {
                    name,
                    qualification,
                    program,
                    "0"
            });

            courseInstructorCombo.addItem(name);

            clearCourseInstructorForm();
            updateStudentDropdown();
            updateCourseDropdown();
            updateInstructorDropdown();

            JOptionPane.showMessageDialog(this,
                    "Instructor added successfully !!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCourseInstructor() {

        int selectedRow = instructorTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select an instructor to update !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String oldName = (String) instructorTableModel.getValueAt(selectedRow, 0);
            String newName = instructorNameField.getText().trim();
            String qualification = instructorQualificationField.getText().trim();
            String program = instructorProgramField.getText().trim();

            // Validate
            if (newName.isEmpty() || qualification.isEmpty() || program.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields !!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!oldName.equals(newName)) {
                for (int i = 0; i < instructors.size(); i++) {
                    if (i != selectedRow && instructors.get(i).getName().equals(newName)) {
                        JOptionPane.showMessageDialog(this,
                                "Another instructor already has this name !!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            CourseInstructor instructor = instructors.get(selectedRow);
            instructor.setName(newName);
            instructor.setQualificaion(qualification);
            instructor.setProgram(program);

            instructorTableModel.setValueAt(newName, selectedRow, 0);
            instructorTableModel.setValueAt(qualification, selectedRow, 1);
            instructorTableModel.setValueAt(program, selectedRow, 2);

            int coursesCount = countInstructorCourses(newName);
            instructorTableModel.setValueAt(coursesCount, selectedRow, 3);

            if (!oldName.equals(newName)) {
                courseInstructorCombo.removeItem(oldName);
                courseInstructorCombo.addItem(newName);

                for (int i = 0; i < courses.size(); i++) {
                    Course course = courses.get(i);
                    if (course.getCourseInstructor() != null &&
                            course.getCourseInstructor().getName().equals(oldName)) {
                        for (int j = 0; j < instructors.size(); j++) {
                            if (instructors.get(j).getName().equals(newName)) {
                                course.setCourseInstructor(instructors.get(j));
                                break;
                            }
                        }

                        for (int row = 0; row < courseTableModel.getRowCount(); row++) {
                            if (courseTableModel.getValueAt(row, 0).equals(course.getCourseCode())) {
                                courseTableModel.setValueAt(newName, row, 3);
                                break;
                            }
                        }
                    }
                }
            }

            clearCourseInstructorForm();
            updateStudentDropdown();
            updateCourseDropdown();
            updateInstructorDropdown();

            JOptionPane.showMessageDialog(this,
                    "Instructor updated successfully !!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int countInstructorCourses(String instructorName) {
        int count = 0;
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (course.getCourseInstructor() != null &&
                    course.getCourseInstructor().getName().equals(instructorName)) {
                count++;
            }
        }
        return count;
    }

    private void deleteCourseInstructor() {
        int selectedRow = instructorTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select an instructor to delete !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = (String) instructorTableModel.getValueAt(selectedRow, 0);
        instructors.remove(name);
        instructorTableModel.removeRow(selectedRow);
        courseInstructorCombo.removeItem(name);

        clearCourseInstructorForm();
        updateStudentDropdown();
        updateCourseDropdown();
        updateInstructorDropdown();

        JOptionPane.showMessageDialog(this,
                "Instructor deleted successfully !!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadInstructorToForm() {
        int selectedRow = instructorTable.getSelectedRow();
        if (selectedRow != -1) {
            instructorNameField.setText((String) instructorTableModel.getValueAt(selectedRow, 0));
            instructorQualificationField.setText((String) instructorTableModel.getValueAt(selectedRow, 1));
            instructorProgramField.setText((String) instructorTableModel.getValueAt(selectedRow, 2));
        }
    }

    private void clearCourseInstructorForm() {
        instructorNameField.setText("");
        instructorQualificationField.setText("");
        instructorProgramField.setText("");
        instructorTable.clearSelection();
    }

    private void viewCourseInstructorDetails() {
        int selectedRow = instructorTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select an instructor first !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            CourseInstructor instructor = instructors.get(selectedRow);

            int coursesTaught = 0;
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                if (course.getCourseInstructor() != null &&
                        course.getCourseInstructor().getName().equals(instructor.getName())) {
                    coursesTaught++;
                }
            }

            String instructorDetails = "<=== INSTRUCTOR DETAILS ===>\n\n" +
                    "Name: " + instructor.getName() + "\n" +
                    "Qualification: " + instructor.getQualificaion() + "\n" +
                    "Program: " + instructor.getProgram() + "\n" +
                    "Courses Teaching: " + coursesTaught + "\n";

            JOptionPane.showMessageDialog(this,
                    instructorDetails,
                    "Course Instructor Details",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addResult() {
        try {
            String studentID = (String) resultStudentCombo.getSelectedItem();
            String courseCode = (String) resultCourseCombo.getSelectedItem();
            String marksString = marksField.getText().trim();

            if (studentID == null || courseCode == null || marksString.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please select student, course and enter marks !!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double marks;
            try {
                marks = Double.parseDouble(marksString);
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this,
                            "Marks must be between 0 and 100 !!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Marks must be a number !!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = null;
            Course course = null;

            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getStudentID().equals(studentID)) {
                    student = students.get(i);
                    break;
                }
            }

            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getCourseCode().equals(courseCode)) {
                    course = courses.get(i);
                    break;
                }
            }

            if (student == null || course == null) {
                JOptionPane.showMessageDialog(this,
                        "Student or course not found !!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < student.getTranscript().getResultEntry().size(); i++) {
                ResultEntry r = student.getTranscript().getResultEntry().get(i);
                if (r.getCourse().getCourseCode().equals(courseCode)) {
                    JOptionPane.showMessageDialog(this,
                            "Result for this course already exists !!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            student.addCourse(course, marks);
            ResultEntry entry = new ResultEntry(course, marks);

            resultTableModel.addRow(new Object[] {
                    student.getStudentID(),
                    student.getName(),
                    course.getCourseCode(),
                    course.getTitle(),
                    marks,
                    entry.getGrade(),
                    String.format("%.2f", entry.getGradePoints())
            });

            for (int row = 0; row < studentTableModel.getRowCount(); row++) {
                if (studentTableModel.getValueAt(row, 0).equals(student.getStudentID())) {
                    studentTableModel.setValueAt(
                            String.format("%.2f", student.calculateGPA()), row, 4);
                    break;
                }
            }

            clearResultForm();
            updateStudentDropdown();
            updateCourseDropdown();
            updateInstructorDropdown();

            JOptionPane.showMessageDialog(this,
                    "Result added successfully !!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearResultForm() {
        resultStudentCombo.setSelectedIndex(0);
        resultCourseCombo.setSelectedIndex(0);
        marksField.setText("");
        resultTable.clearSelection();
    }

    private void updateResult() {
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a result to update !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String studentID = (String) resultTableModel.getValueAt(selectedRow, 0);
            String courseCode = (String) resultTableModel.getValueAt(selectedRow, 2);
            double marks = Double.parseDouble(marksField.getText().trim());

            Student student = null;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getStudentID().equals(studentID)) {
                    student = students.get(i);
                    break;
                }
            }

            if (student == null)
                return;

            for (ResultEntry r : student.getTranscript().getResultEntry()) {
                if (r.getCourse().getCourseCode().equals(courseCode)) {
                    r.setMarksObtained(marks);

                    resultTableModel.setValueAt(marks, selectedRow, 4);
                    resultTableModel.setValueAt(r.getGrade(), selectedRow, 5);
                    resultTableModel.setValueAt(
                            String.format("%.2f", r.getGradePoints()), selectedRow, 6);
                    break;
                }
            }

            for (int row = 0; row < studentTableModel.getRowCount(); row++) {
                if (studentTableModel.getValueAt(row, 0).equals(studentID)) {
                    studentTableModel.setValueAt(
                            String.format("%.2f", student.calculateGPA()), row, 4);
                    break;
                }
            }

            clearResultForm();
            updateStudentDropdown();
            updateCourseDropdown();
            updateInstructorDropdown();

            JOptionPane.showMessageDialog(this,
                    "Result updated successfully !!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error updating result !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteResult() {
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a result to delete !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String studentID = (String) resultTableModel.getValueAt(selectedRow, 0);
        String courseCode = (String) resultTableModel.getValueAt(selectedRow, 2);

        Student student = null;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(studentID)) {
                student = students.get(i);
                break;
            }
        }

        if (student != null) {
            student.getTranscript().getResultEntry()
                    .removeIf(r -> r.getCourse().getCourseCode().equals(courseCode));

            for (int row = 0; row < studentTableModel.getRowCount(); row++) {
                if (studentTableModel.getValueAt(row, 0).equals(studentID)) {
                    studentTableModel.setValueAt(
                            String.format("%.2f", student.calculateGPA()), row, 4);
                    break;
                }
            }
        }

        resultTableModel.removeRow(selectedRow);
        clearResultForm();
        updateStudentDropdown();
        updateCourseDropdown();
        updateInstructorDropdown();

        JOptionPane.showMessageDialog(this,
                "Result deleted successfully !!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewTranscript() {
        try {
            String studentId = (String) resultStudentCombo.getSelectedItem();
            if (studentId == null || studentId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a student !!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = null;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getStudentID().equals(studentId)) {
                    student = students.get(i);
                    break;
                }
            }

            if (student == null) {
                JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String transcript = "";

            transcript += "══════════════════════════════════════════════════════════\n";
            transcript += "                    TRANSCRIPT\n";
            transcript += "══════════════════════════════════════════════════════════\n\n";

            transcript += "Student ID: " + student.getStudentID() + "\n";
            transcript += "Name: " + student.getName() + "\n";
            transcript += "Program: " + student.getProgram() + "\n";
            transcript += "Type: " + student.getStudentType() + "\n\n";

            if (student.getTranscript() != null && !student.getTranscript().getResultEntry().isEmpty()) {

                transcript += String.format("%-15s %-15s %-8s %-8s %-12s\n",
                        "Course Code", "Title", "Marks", "Grade", " Course Grade Points");

                transcript += "───────────────────────────────────────────────────────────────────────────\n";

                for (int i = 0; i < student.getTranscript().getResultEntry().size(); i++) {
                    ResultEntry entry = student.getTranscript().getResultEntry().get(i);
                    Course course = entry.getCourse();

                    transcript += String.format("%-15s %-15s %-8.0f %-8s %-12.2f\n",
                            course.getCourseCode(),
                            course.getTitle().length() > 30
                                    ? course.getTitle().substring(0, 27) + "..."
                                    : course.getTitle(),
                            entry.getMarksObtained(),
                            entry.getGrade(),
                            entry.getGradePoints());
                }

                transcript += "\n───────────────────────────────────────────────────────────────────────────\n";
                transcript += "Semester GPA: " + String.format("%.2f", student.calculateGPA()) + "\n";
                transcript += "Overall Grade: " + student.calculateGrade() + "\n";
                transcript += "Total Credit Hours: " + student.getTranscript().TotalCreditHours() + "\n";

            } else {
                transcript += "No courses registered yet.\n";
            }

            transcript += "══════════════════════════════════════════════════════════\n";

            JTextArea textArea = new JTextArea(transcript);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            textArea.setEditable(false);

            JOptionPane.showMessageDialog(this,
                    textArea,
                    "Student Transcript", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error viewing transcript: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadResultToForm() {
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow != -1) {
            String studentName = (String) resultTableModel.getValueAt(selectedRow, 1);
            String courseCode = (String) resultTableModel.getValueAt(selectedRow, 2);
            Object marksObj = resultTableModel.getValueAt(selectedRow, 4);

            // Set student in combo box
            resultStudentCombo.setSelectedItem(studentName);

            // Set course in combo box
            resultCourseCombo.setSelectedItem(courseCode);

            // Set marks in field
            if (marksObj != null) {
                marksField.setText(marksObj.toString());
            }
        }
    }

    private void calculateGPA() {
        String studentId = (String) resultStudentCombo.getSelectedItem();
        if (studentId == null || studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = null;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(studentId)) {
                student = students.get(i);
                break;
            }
        }

        if (student == null) {
            JOptionPane.showMessageDialog(this,
                    "Student not found !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        double gpa = student.calculateGPA();
        JOptionPane.showMessageDialog(this,
                "Semester GPA of Student  " + student.getName() + " : " + String.format("%.2f", gpa),
                "GPA Calculation",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // ==================== HELPER METHODS ====================
    private void setupEventHandlers() {
        // Setup comboboxes
        updateStudentDropdown();
        updateCourseDropdown();
        updateInstructorDropdown();
    }

    private void updateStudentDropdown() {
        resultStudentCombo.removeAllItems();
        reportStudentCombo.removeAllItems();
        PdfReportStudentCombo.removeAllItems();
        for (int i = 0; i < students.size(); i++) {
            resultStudentCombo.addItem(students.get(i).getStudentID());
            reportStudentCombo.addItem(students.get(i).getStudentID());
            PdfReportStudentCombo.addItem(students.get(i).getStudentID());
        }
    }

    private void updateCourseDropdown() {
        courseInstructorCombo.removeAllItems();
        reportCourseCombo.removeAllItems();
        PdfReportCourseCombo.removeAllItems();
        for (int i = 0; i < courses.size(); i++) {
            resultCourseCombo.addItem(courses.get(i).getCourseCode());
            reportCourseCombo.addItem(courses.get(i).getCourseCode());
            PdfReportCourseCombo.addItem(courses.get(i).getCourseCode());
        }
    }

    private void updateInstructorDropdown() {
        courseInstructorCombo.removeAllItems();
        PdfReportInstructorCombo.removeAllItems();
        for (int i = 0; i < instructors.size(); i++) {
            courseInstructorCombo.addItem(instructors.get(i).getName());
            PdfReportInstructorCombo.addItem(instructors.get(i).getName());
        }
    }

    private void generateSingleStudentReport() {
        try {
            // Get selected student from reports combo
            String studentName = (String) reportStudentCombo.getSelectedItem();
            if (studentName == null || studentName.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please select a student from the dropdown!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Find the student
            Student student = null;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getName().equals(studentName)) {
                    student = students.get(i);
                    break;
                }
            }

            if (student == null) {
                JOptionPane.showMessageDialog(this,
                        "Student not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Build student report
            String report = "══════════════════════════════════════════════════════════\n" +
                    "               STUDENT DETAILED REPORT\n" +
                    "══════════════════════════════════════════════════════════\n\n" +

                    "STUDENT INFORMATION:\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    "Student ID:      " + student.getStudentID() + "\n" +
                    "Name:            " + student.getName() + "\n" +
                    "Program:         " + student.getProgram() + "\n" +
                    "Type:            " + student.getStudentType() + "\n\n" +

                    "ACADEMIC PERFORMANCE:\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    "Semester GPA:        " + String.format("%.2f", student.calculateGPA()) + "\n" +
                    "Percentage:          " + String.format("%.2f", student.calculatePercentage()) + "%\n" +
                    "Overall Grade:       " + student.calculateGrade() + "\n\n";

            // Add course results if available
            if (student.getTranscript() != null && !student.getTranscript().getResultEntry().isEmpty()) {
                report += "COURSE RESULTS:\n";
                report += "──────────────────────────────────────────────────────────\n";
                report += String.format("%-12s %-25s %-8s %-8s %-12s\n",
                        "Code", "Course", "Credits", "Marks", "Grade");
                report += "─────────────────────────────────────────────────────────────────────────────────────\n";

                int totalCourses = student.getTranscript().getResultEntry().size();
                double totalCreditHours = 0;

                for (int i = 0; i < student.getTranscript().getResultEntry().size(); i++) {
                    ResultEntry entry = student.getTranscript().getResultEntry().get(i);
                    Course course = entry.getCourse();
                    double marks = entry.getMarksObtained();
                    String grade = entry.getGrade();
                    double credits = course.getCreditHours();

                    report += String.format("%-12s %-25s %-8.1f %-8.0f %-12s\n",
                            course.getCourseCode(),
                            course.getTitle().length() > 25 ? course.getTitle().substring(0, 22) + "..."
                                    : course.getTitle(),
                            credits,
                            marks,
                            grade);

                    totalCreditHours += credits;
                }

                report += "\nSUMMARY:\n";
                report += "──────────────────────────────────────────────────────────\n";
                report += "Total Courses:           " + totalCourses + "\n";
                report += "Total Credit Hours:      " + String.format("%.1f", totalCreditHours) + "\n";

            } else {
                report += "No courses registered for this semester.\n";
            }

            report += "\n══════════════════════════════════════════════════════════\n" +
                    "             END OF STUDENT REPORT\n" +
                    "══════════════════════════════════════════════════════════\n";

            // Display report
            JTextArea textArea = new JTextArea(report);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            textArea.setEditable(false);

            JOptionPane.showMessageDialog(this,
                    textArea,
                    "Student Report: " + studentName,
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error generating report: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateSingleCourseReport() {
        try {
            String courseCode = (String) reportCourseCombo.getSelectedItem();
            if (courseCode == null || courseCode.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please select a course!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Find the course
            Course course = null;
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getCourseCode().equals(courseCode)) {
                    course = courses.get(i);
                    break;
                }
            }

            if (course == null) {
                JOptionPane.showMessageDialog(this,
                        "Course not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Count students who took this course
            int totalStudents = 0;

            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (student.getTranscript() != null && student.getTranscript().getResultEntry() != null) {
                    for (int j = 0; j < student.getTranscript().getResultEntry().size(); j++) {
                        ResultEntry entry = student.getTranscript().getResultEntry().get(j);
                        if (entry.getCourse().getCourseCode().equals(courseCode)) {
                            totalStudents++;
                            break;
                        }
                    }
                }
            }

            // Build simple report
            String report = 
            "══════════════════════════════════════════════════════════\n" +
                    "               COURSE BASIC REPORT\n" +
                    "══════════════════════════════════════════════════════════\n\n" +

                    "COURSE INFORMATION:\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    "Course Code:     " + course.getCourseCode() + "\n" +
                    "Title:           " + course.getTitle() + "\n" +
                    "Credit Hours:    " + course.getCreditHours() + "\n" +
                    "Instructor:      " +
                    (course.getCourseInstructor() != null ? course.getCourseInstructor().getName() : "None")
                    + "\n\n" +

                    "COURSE STATISTICS:\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    "Total Students:  " + totalStudents + "\n" +
                    "Total Marks:     " + "100" + "\n" +
                    "Passing Marks:   " + "50" + "\n" +
                    "\n══════════════════════════════════════════════════════════\n" +
                    "             END OF COURSE REPORT\n" +
                    "══════════════════════════════════════════════════════════\n";

            // Display report
            JTextArea textArea = new JTextArea(report);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            textArea.setEditable(false);

            JOptionPane.showMessageDialog(this,
                    textArea,
                    "Course Report: " + courseCode,
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadDataFromFiles() {
        try {
            String dataFolder = "DataStorage/";

            int studentsLoaded = 0;
            int coursesLoaded = 0;
            int instructorsLoaded = 0;

            RecordList<CourseInstructor> loadedInstructors = instructorStore
                    .loadToRecordList(dataFolder + "instructors");
            if (loadedInstructors != null && loadedInstructors.size() > 0) {
                instructors = loadedInstructors;
                instructorsLoaded = instructors.size();
                refreshInstructorTable();

                updateStudentDropdown();
                updateCourseDropdown();
                updateInstructorDropdown();
            }

            RecordList<Student> loadedStudents = studentStore.loadToRecordList(dataFolder + "students");
            if (loadedStudents != null && loadedStudents.size() > 0) {
                students = loadedStudents;
                studentsLoaded = students.size();
                refreshStudentTable();
            }

            RecordList<Course> loadedCourses = courseStore.loadToRecordList(dataFolder + "courses");
            if (loadedCourses != null && loadedCourses.size() > 0) {
                courses = loadedCourses;
                coursesLoaded = courses.size();
                refreshCourseTable();
                updateStudentDropdown();
                updateCourseDropdown();
                updateInstructorDropdown();
            }

            refreshResultTable();

            int resultsLoaded = 0;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getTranscript() != null &&
                        students.get(i).getTranscript().getResultEntry() != null) {
                    resultsLoaded += students.get(i).getTranscript().getResultEntry().size();
                }
            }

            String message = "Data loaded successfully!\n" +
                    "Students: " + studentsLoaded + "\n" +
                    "Courses: " + coursesLoaded + "\n" +
                    "Instructors: " + instructorsLoaded + "\n" +
                    "Results: " + resultsLoaded;
            JOptionPane.showMessageDialog(this,
                    message,
                    "Load Complete",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading data: " + e.getMessage(),
                    "Load Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadDataFromFilesWithoutDialogueBox() {
        try {
            String dataFolder = "DataStorage/";

            int studentsLoaded = 0;
            int coursesLoaded = 0;
            int instructorsLoaded = 0;

            RecordList<CourseInstructor> loadedInstructors = instructorStore
                    .loadToRecordList(dataFolder + "instructors");
            if (loadedInstructors != null && loadedInstructors.size() > 0) {
                instructors = loadedInstructors;
                instructorsLoaded = instructors.size();
                refreshInstructorTable();
            }

            RecordList<Student> loadedStudents = studentStore.loadToRecordList(dataFolder + "students");
            if (loadedStudents != null && loadedStudents.size() > 0) {
                students = loadedStudents;
                studentsLoaded = students.size();
                refreshStudentTable();
            }

            RecordList<Course> loadedCourses = courseStore.loadToRecordList(dataFolder + "courses");
            if (loadedCourses != null && loadedCourses.size() > 0) {
                courses = loadedCourses;
                coursesLoaded = courses.size();
                refreshCourseTable();
            }

            refreshResultTable();
            updateStudentDropdown();
            updateCourseDropdown();
            updateInstructorDropdown();

            int resultsLoaded = 0;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getTranscript() != null &&
                        students.get(i).getTranscript().getResultEntry() != null) {
                    resultsLoaded += students.get(i).getTranscript().getResultEntry().size();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading data: " + e.getMessage(),
                    "Load Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveAllData() {
        try {
            String dataFolder = "DataStorage/";

            int resultCount = 0;
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                if (s.getTranscript() != null && s.getTranscript().getResultEntry() != null) {
                    resultCount += s.getTranscript().getResultEntry().size();
                }
            }

            studentStore.saveToFile(dataFolder + "students", students);
            courseStore.saveToFile(dataFolder + "courses", courses);
            instructorStore.saveToFile(dataFolder + "instructors", instructors);

            String info = "Data Saved Successfully!\n\n" +
                    "Total Students: " + students.size() + "\n" +
                    "Total Courses: " + courses.size() + "\n" +
                    "Total Instructors: " + instructors.size() + "\n" +
                    "Total Result Entries: " + resultCount;

            JOptionPane.showMessageDialog(this, info, "Save Complete", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshStudentTable() {
        studentTableModel.setRowCount(0);

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            studentTableModel.addRow(new Object[] {
                    student.getStudentID(),
                    student.getName(),
                    student.getProgram(),
                    student.getStudentType(),
                    String.format("%.2f", student.calculateGPA())
            });
        }
    }

    private void refreshCourseTable() {
        courseTableModel.setRowCount(0);

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            String instructorName = (course.getCourseInstructor() != null)
                    ? course.getCourseInstructor().getName()
                    : "None";
            courseTableModel.addRow(new Object[] {
                    course.getCourseCode(),
                    course.getTitle(),
                    course.getCreditHours(),
                    instructorName
            });
        }
    }

    private void refreshInstructorTable() {
        instructorTableModel.setRowCount(0);

        for (int i = 0; i < instructors.size(); i++) {
            CourseInstructor instructor = instructors.get(i);
            int coursesCount = countInstructorCourses(instructor.getName());
            instructorTableModel.addRow(new Object[] {
                    instructor.getName(),
                    instructor.getQualificaion(),
                    instructor.getProgram(),
                    coursesCount
            });
        }
    }

    private void refreshResultTable() {
        resultTableModel.setRowCount(0);

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            if (student.getTranscript() != null && student.getTranscript().getResultEntry() != null) {
                for (int j = 0; j < student.getTranscript().getResultEntry().size(); j++) {
                    ResultEntry entry = student.getTranscript().getResultEntry().get(j);
                    Course course = entry.getCourse();

                    resultTableModel.addRow(new Object[] {
                            student.getStudentID(),
                            student.getName(),
                            course.getCourseCode(),
                            course.getTitle(),
                            entry.getMarksObtained(),
                            entry.getGrade(),
                            String.format("%.2f", entry.getGradePoints())
                    });
                }
            }
        }
    }

    private void displayStatistics() {
        try {
            int totalResultEntries = 0;
            int studentsWithResults = 0;

            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (student.getTranscript() != null && student.getTranscript().getResultEntry() != null) {
                    int studentResults = student.getTranscript().getResultEntry().size();
                    if (studentResults > 0) {
                        studentsWithResults++;
                        totalResultEntries += studentResults;
                    }
                }
            }

            int artsStudents = 0;
            int scienceStudents = 0;
            int engineeringStudents = 0;

            for (int i = 0; i < students.size(); i++) {
                String type = students.get(i).getStudentType();
                if (type.equals("Arts Student")) {
                    artsStudents++;
                } else if (type.equals("Science Student")) {
                    scienceStudents++;
                } else if (type.equals("Engineering Student")) {
                    engineeringStudents++;
                }
            }

            int coursesWithInstructor = 0;
            int coursesWithoutInstructor = 0;

            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getCourseInstructor() != null) {
                    coursesWithInstructor++;
                } else {
                    coursesWithoutInstructor++;
                }
            }

            String statsMessage = "══════════════════════════════════════════════════════════\n" +
                    "                SYSTEM OVERVIEW\n" +
                    "══════════════════════════════════════════════════════════\n\n" +

                    "OVERALL COUNTS :\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    String.format("Total Students:        %3d\n", students.size()) +
                    String.format("Total Courses:         %3d\n", courses.size()) +
                    String.format("Total Instructors:     %3d\n", instructors.size()) +
                    String.format("Total Result Entries:  %3d\n", totalResultEntries) +
                    "\n" +

                    "STUDENT DISTRIBUTION :\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    String.format("Arts Students:         %3d\n", artsStudents)
                    +
                    String.format("Science Students:      %3d\n", scienceStudents)
                    +
                    String.format("Engineering Students:  %3d\n", engineeringStudents)
                    +
                    "\n" +

                    "COURSE INFORMATION :\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    String.format("Courses with Instructor:    %3d\n", coursesWithInstructor) +
                    String.format("Courses without Instructor: %3d\n", coursesWithoutInstructor) +
                    String.format("Passing Marks of a Course : %3f\n", ResultCalculator.passMarks) +
                    "\n" +

                    "ACADEMIC STATISTICS :\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    String.format("Students with Results:  %3d\n", studentsWithResults) +
                    "══════════════════════════════════════════════════════════\n" +
                    "   Generated on: " + new java.util.Date() + "\n" +
                    "══════════════════════════════════════════════════════════";

            JTextArea textArea = new JTextArea(statsMessage);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            textArea.setEditable(false);

            JOptionPane.showMessageDialog(this,
                    textArea,
                    "System Statistics",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error displaying statistics: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void generateandsendPDFViaGmail (String CheckField , String Type, String toEmail) {

        String messageText = "";
        if (Type.equals("Student")) {

            Student student = null;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getStudentID().equals(CheckField)) {
                    student = students.get(i);
                    break;
                }
            }

            if (student == null) {
                JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            messageText = "";

            messageText += "══════════════════════════════════════════════════════════\n";
            messageText += "                   RESULT CARD\n";
            messageText += "══════════════════════════════════════════════════════════\n\n";

            messageText += "Student ID: " + student.getStudentID() + "\n";
            messageText += "Name: " + student.getName() + "\n";
            messageText += "Program: " + student.getProgram() + "\n";
            messageText += "Type: " + student.getStudentType() + "\n\n";

            if (student.getTranscript() != null && !student.getTranscript().getResultEntry().isEmpty()) {

                messageText += String.format("%-15s %-15s %-8s %-8s %-12s\n",
                        "Course Code", "Title", "Marks", "Grade", " Course Grade Points");

                messageText += "───────────────────────────────────────────────────────────────────────────\n";

                for (int i = 0; i < student.getTranscript().getResultEntry().size(); i++) {
                    ResultEntry entry = student.getTranscript().getResultEntry().get(i);
                    Course course = entry.getCourse();

                    messageText += String.format("%-15s %-15s %-8.0f %-8s %-12.2f\n",
                            course.getCourseCode(),
                            course.getTitle().length() > 30
                                    ? course.getTitle().substring(0, 27) + "..."
                                    : course.getTitle(),
                            entry.getMarksObtained(),
                            entry.getGrade(),
                            entry.getGradePoints());
                }

                messageText += "\n───────────────────────────────────────────────────────────────────────────\n";
                messageText += "Semester GPA: " + String.format("%.2f", student.calculateGPA()) + "\n";
                messageText += "Overall Grade: " + student.calculateGrade() + "\n";
                messageText += "Total Credit Hours: " + student.getTranscript().TotalCreditHours() + "\n";

            } else {
                messageText += "No courses registered yet.\n";
            }

            messageText += "══════════════════════════════════════════════════════════\n";

        }
        else if (Type.equals("Course")) {

            Course course = null;
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getCourseCode().equals(CheckField)) {
                    course = courses.get(i);
                    break;
                }
            }

            if (course == null) {
                JOptionPane.showMessageDialog(this,
                        "Course not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Count students who took this course
            int totalStudents = 0;

            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (student.getTranscript() != null && student.getTranscript().getResultEntry() != null) {
                    for (int j = 0; j < student.getTranscript().getResultEntry().size(); j++) {
                        ResultEntry entry = student.getTranscript().getResultEntry().get(j);
                        if (entry.getCourse().getCourseCode().equals(CheckField)) {
                            totalStudents++;
                            break;
                        }
                    }
                }
            }

            // Build simple report
            messageText = "══════════════════════════════════════════════════════════\n" +
                    "                 COURSE  REPORT\n" +
                    "══════════════════════════════════════════════════════════\n\n" +

                    "COURSE INFORMATION:\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    "Course Code:     " + course.getCourseCode() + "\n" +
                    "Title:           " + course.getTitle() + "\n" +
                    "Credit Hours:    " + course.getCreditHours() + "\n" +
                    "Instructor:      " +
                    (course.getCourseInstructor() != null ? course.getCourseInstructor().getName() : "None")
                    + "\n\n" +

                    "COURSE STATISTICS:\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    "Total Students:  " + totalStudents + "\n" +
                    "Total Marks:     " + "100" + "\n" +
                    "Passing Marks:   " + "50" + "\n" +
                    "\n══════════════════════════════════════════════════════════\n" +
                    "             END OF COURSE REPORT\n" +
                    "══════════════════════════════════════════════════════════\n";

        }
        else if (Type.equals("Instructor")) {

            CourseInstructor instructor = null;
            for (int i = 0; i < instructors.size(); i++) {
                if (instructors.get(i).getName().equals(CheckField)) {
                    instructor = instructors.get(i);
                    break;
                }
            }
            if (instructor == null) {
                JOptionPane.showMessageDialog(this,
                        "Course Instructor not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int coursesTaught = 0;
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                if (course.getCourseInstructor() != null &&
                        course.getCourseInstructor().getName().equals(instructor.getName())) {
                    coursesTaught++;
                }
            }

            messageText = "<=== INSTRUCTOR DETAILS ===>\n\n" +
                    "Name: " + instructor.getName() + "\n" +
                    "Qualification: " + instructor.getQualificaion() + "\n" +
                    "Program: " + instructor.getProgram() + "\n" +
                    "Courses Teaching: " + coursesTaught + "\n";
        }
        else if (Type.equals("Statistics")) {

            int totalResultEntries = 0;
            int studentsWithResults = 0;

            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (student.getTranscript() != null && student.getTranscript().getResultEntry() != null) {
                    int studentResults = student.getTranscript().getResultEntry().size();
                    if (studentResults > 0) {
                        studentsWithResults++;
                        totalResultEntries += studentResults;
                    }
                }
            }

            int artsStudents = 0;
            int scienceStudents = 0;
            int engineeringStudents = 0;

            for (int i = 0; i < students.size(); i++) {
                String type = students.get(i).getStudentType();
                if (type.equals("Arts Student")) {
                    artsStudents++;
                } else if (type.equals("Science Student")) {
                    scienceStudents++;
                } else if (type.equals("Engineering Student")) {
                    engineeringStudents++;
                }
            }

            int coursesWithInstructor = 0;
            int coursesWithoutInstructor = 0;

            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getCourseInstructor() != null) {
                    coursesWithInstructor++;
                } else {
                    coursesWithoutInstructor++;
                }
            }

            messageText = "══════════════════════════════════════════════════════════\n" +
                    "                SYSTEM OVERVIEW\n" +
                    "══════════════════════════════════════════════════════════\n\n" +

                    "OVERALL COUNTS :\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    String.format("Total Students:        %3d\n", students.size()) +
                    String.format("Total Courses:         %3d\n", courses.size()) +
                    String.format("Total Instructors:     %3d\n", instructors.size()) +
                    String.format("Total Result Entries:  %3d\n", totalResultEntries) +
                    "\n" +

                    "STUDENT DISTRIBUTION :\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    String.format("Arts Students:         %3d\n", artsStudents)
                    +
                    String.format("Science Students:      %3d\n", scienceStudents)
                    +
                    String.format("Engineering Students:  %3d\n", engineeringStudents)
                    +
                    "\n" +

                    "COURSE INFORMATION :\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    String.format("Courses with Instructor:    %3d\n", coursesWithInstructor) +
                    String.format("Courses without Instructor: %3d\n", coursesWithoutInstructor) +
                    String.format("Passing Marks of a Course : %3f\n", ResultCalculator.passMarks) +
                    "\n" +

                    "ACADEMIC STATISTICS :\n" +
                    "──────────────────────────────────────────────────────────\n" +
                    String.format("Students with Results:  %3d\n", studentsWithResults) +
                    "══════════════════════════════════════════════════════════\n" +
                    "   Generated on: " + new java.util.Date() + "\n" +
                    "══════════════════════════════════════════════════════════";
        }

        String fromEmail = System.getenv("GMAIL_APP");
        String appPassword = System.getenv("GMAIL_APP_PASSWORD");

        if (fromEmail == null || appPassword == null) {
            JOptionPane.showMessageDialog(this,
                    "Email credentials not found in environment variables!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String subject = "PDF of " + Type + " by RMS Result Managment System";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        jakarta.mail.Authenticator authenticator = new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(fromEmail, appPassword);
            }
        };

        Session session = Session.getInstance(props, authenticator);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            JOptionPane.showMessageDialog(this,
                    "Email sent successfully !!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            return;

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to send email !!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}