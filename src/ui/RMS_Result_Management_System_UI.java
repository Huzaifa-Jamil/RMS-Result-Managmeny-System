package ui;

import models.*;
import utils.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class RMS_Result_Management_System_UI extends JFrame {

    public static JComboBox<String> student_Type_Combo;
    public static JComboBox<String> course_Instructor_Combo;
    public static JComboBox<String> result_Student_Combo;
    public static JComboBox<String> result_Course_Combo;
    public static JComboBox<String> pdf_Report_Student_Combo;
    public static JComboBox<String> pdf_Report_Course_Combo;
    public static JComboBox<String> pdf_Report_Instructor_Combo;
    public static JTable student_Table;
    public static JTable course_Table;
    public static JTable instructor_Table;
    public static JTable result_Table;
    public static DefaultTableModel student_Table_Model;
    public static DefaultTableModel course_Table_Model;
    public static DefaultTableModel instructor_Table_Model;
    public static DefaultTableModel result_Table_Model;
    public static JLabel total_Students_Statisticts = new JLabel();
    public static JLabel total_Science_Students_Statisticts = new JLabel();
    public static JLabel total_Arts_Students_Statisticts = new JLabel();
    public static JLabel total_Engineering_Students_Statisticts = new JLabel();
    public static JLabel total_Courses_Statisticts = new JLabel();
    public static JLabel total_Instructors_Statisticts = new JLabel();
    public static JLabel total_Results_Statisticts = new JLabel();
    public static JLabel students_With_Results_Statisticts = new JLabel();
    public static JLabel students_Without_Results_Statisticts = new JLabel();
    public static JLabel Instructors_With_Courses_Statisticts = new JLabel();
    public static JLabel Instructors_Without_Courses_Statisticts = new JLabel();
    public static JLabel passing_Marks_Statisticts = new JLabel();
    public static Data_Store<Student> student_Store = new Data_Store<>();
    public static Data_Store<Course> course_Store = new Data_Store<>();
    public static Data_Store<Course_Instructor> instructor_Store = new Data_Store<>();
    public static Record_List<Student> students = new Record_List<>();
    public static Record_List<Course> courses = new Record_List<>();
    public static Record_List<Course_Instructor> instructors = new Record_List<>();

    public static JFrame mainFrame;
    public JTabbedPane tabbedPane;
    public JPanel controlPanel;
    public AI_UI aiPanel;
    public JPanel titlePanel;
    public JPanel sideBar;
    private Dimension lastUserSize = null;
    private boolean firstMinimizeDone = false;

    public RMS_Result_Management_System_UI() {
        mainFrame = this;
        initialize_GUI();
        Data_Peristance.load_Data_From_DataBase();
        Refresh_All.refresh();
    }

    public void initialize_GUI() {

        UIManager.put("ToolTip.background", new Color(0, 0, 0, 0));
        javax.swing.UIManager.put("ToolTip.foreground", Color.WHITE);
        javax.swing.UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("ToolTip.border", BorderFactory.createEmptyBorder());
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == JFrame.NORMAL) {
                    if (!firstMinimizeDone) {
                        setSize(1300, 700);
                        setLocationRelativeTo(null);
                        firstMinimizeDone = true;
                    } else if (lastUserSize != null) {
                        setSize(lastUserSize);
                    }
                }
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (getExtendedState() == JFrame.NORMAL) {
                    lastUserSize = getSize();
                }
            }
        });

        this.setTitle("RMS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 400));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logo.png"));
        this.setIconImage(icon.getImage());

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setOpaque(true);
        tabbedPane.setBorder(new Round_Tab_Border(15, new Color(224, 224, 224)));

        aiPanel = new AI_UI();

        tabbedPane.addTab("", new Student_UI());
        tabbedPane.addTab("", new Course_UI());
        tabbedPane.addTab("", new Course_Instructor_UI());
        tabbedPane.addTab("", new Result_UI());
        tabbedPane.addTab("", new Statistics_UI());
        tabbedPane.addTab("", new Report_UI());
        tabbedPane.addTab("", aiPanel);
        tabbedPane.addTab("", new Settings_UI());

        tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
                return 0;
            }

            @Override
            protected int calculateTabAreaWidth(int tabPlacement, int vertRunCount, int maxTabWidth) {
                return 0;
            }

            @Override
            protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
            }

            @Override
            protected Insets getTabAreaInsets(int tabPlacement) {
                return new Insets(0, 0, 0, 0);
            }

            @Override
            protected Insets getContentBorderInsets(int tabPlacement) {
                return new Insets(0, 0, 0, 0);
            }
        });

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 6) {
                // aiPanel.resetUI();
                aiPanel.requestFocusInWindow();
                if (aiPanel.welcomeShowed == false) {
                    aiPanel.showWelcomeMessage();
                }
            } else {
                Refresh_All.refresh();
                this.requestFocusInWindow();
            }
        });

        titlePanel = new Top_Panel_UI();
        
        sideBar = new Side_Icon_Bar_UI(tabbedPane);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(sideBar, BorderLayout.WEST);
        this.add(tabbedPane, BorderLayout.CENTER);

        setupKeyboardShortcuts();
        
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    private void setupKeyboardShortcuts() {
        JComponent contentPane = (JComponent) this.getContentPane();
        int menuMask = java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        
        // Ctrl + L: Load Data
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_L, menuMask), "loadData");
        contentPane.getActionMap().put("loadData", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Data_Peristance.load_Data_From_DataBase();
            }
        });
        
        // Ctrl + S: Save Data
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_S, menuMask), "saveData");
        contentPane.getActionMap().put("saveData", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Data_Peristance.save_All_Data_To_DataBase();
            }
        });
        
        // Ctrl + E: Exit Application
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_E, menuMask), "exitApplication");
        contentPane.getActionMap().put("exitApplication", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System_Controls_UI.exitFromSystem();
            }
        });
        
        // Ctrl + R: Refresh
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_R, menuMask), "refresh");
        contentPane.getActionMap().put("refresh", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Refresh_All.refresh();
                UI_Styles.showSideNotifications(mainFrame,"System refreshed");
                
            }
        });
        
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_A, menuMask), "showAbout");
        contentPane.getActionMap().put("showAbout", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_Styles.showInformation(mainFrame, Html_Files.showAboutSystem(), "About RMS System");
            }
        });
    }
}