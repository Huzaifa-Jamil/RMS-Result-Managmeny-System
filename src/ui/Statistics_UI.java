package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utils.*;

public class Statistics_UI extends JPanel {

    public Statistics_UI() {
        createStatisticsPanel();
    }

    public void createStatisticsPanel() {
        this.setLayout(new BorderLayout(20, 20));
        this.setBorder(new Round_Panel_Border_Top_Left(15, new Color(240, 242, 245)));
        this.setOpaque(false);
        this.setBackground(new Color(240, 242, 245));

        JPanel headerPanel = UI_Styles.createHeaderPanel("Analytics Dashboard");

        JPanel statsPanel = new JPanel(new GridBagLayout());
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 20, 0, 20);
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel displayPanel = new JPanel(new GridLayout(4, 3, 20, 20));
        displayPanel.setBackground(Color.WHITE);
        displayPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        RMS_Result_Management_System_UI.total_Students_Statisticts.setText("0");
        RMS_Result_Management_System_UI.total_Arts_Students_Statisticts.setText("0");
        RMS_Result_Management_System_UI.total_Science_Students_Statisticts.setText("0");
        RMS_Result_Management_System_UI.total_Engineering_Students_Statisticts.setText("0");
        RMS_Result_Management_System_UI.total_Courses_Statisticts.setText("0");
        RMS_Result_Management_System_UI.total_Instructors_Statisticts.setText("0");
        RMS_Result_Management_System_UI.total_Results_Statisticts.setText("0");
        RMS_Result_Management_System_UI.students_With_Results_Statisticts.setText("0");
        RMS_Result_Management_System_UI.students_Without_Results_Statisticts.setText("0");
        RMS_Result_Management_System_UI.Instructors_With_Courses_Statisticts.setText("0");
        RMS_Result_Management_System_UI.Instructors_Without_Courses_Statisticts.setText("0");

        displayPanel.add(UI_Styles.createStatCard("Students", 
                RMS_Result_Management_System_UI.total_Students_Statisticts, 
                        new Color(74, 144, 226)));
        displayPanel.add(UI_Styles.createStatCard("Arts Students",
                RMS_Result_Management_System_UI.total_Arts_Students_Statisticts,
                        new Color(46, 204, 113)));
        displayPanel.add(UI_Styles.createStatCard("Science Students",
                RMS_Result_Management_System_UI.total_Science_Students_Statisticts,
            new Color(155, 89, 182)));
        displayPanel.add(UI_Styles.createStatCard("Engineering Students",
                RMS_Result_Management_System_UI.total_Engineering_Students_Statisticts,
                        new Color(241, 196, 15)));
        displayPanel.add(UI_Styles.createStatCard("Courses", 
                RMS_Result_Management_System_UI.total_Courses_Statisticts, 
                        new Color(230, 126, 34)));
        displayPanel.add(UI_Styles.createStatCard("Instructors", 
                RMS_Result_Management_System_UI.total_Instructors_Statisticts, 
                        new Color(231, 76, 60)));
        displayPanel.add(UI_Styles.createStatCard("Results", 
                RMS_Result_Management_System_UI.total_Results_Statisticts, 
                        new Color(52, 152, 219)));
        displayPanel.add(UI_Styles.createStatCard("Students with Results",
                RMS_Result_Management_System_UI.students_With_Results_Statisticts,
                        new Color(26, 188, 156)));
        displayPanel.add(UI_Styles.createStatCard("Students without Results",
                RMS_Result_Management_System_UI.students_Without_Results_Statisticts,
                        new Color(149, 165, 166)));
        displayPanel.add(UI_Styles.createStatCard("Instructors with Courses", 
                RMS_Result_Management_System_UI.Instructors_With_Courses_Statisticts,
                        new Color(142, 68, 173)));
        displayPanel.add(UI_Styles.createStatCard("Instructors without Courses",
                RMS_Result_Management_System_UI.Instructors_Without_Courses_Statisticts,
                        new Color(39, 174, 96)));
            displayPanel.add(UI_Styles.createStatCard("Passing Marks",
                    RMS_Result_Management_System_UI.passing_Marks_Statisticts,
                            new Color(22, 160, 133)));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(30, 0, 0, 0));

        JButton viewStatsBtn = UI_Styles.createActionButton("Detail Statistics", new Color(231, 76, 60), 140);

        viewStatsBtn.addActionListener(e -> displayStatistics());

        buttonPanel.add(viewStatsBtn);

        gbc.gridx = 0;
        gbc.gridy = 0;
        statsPanel.add(displayPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0); 
        statsPanel.add(buttonPanel, gbc);

        UI_Styles.makeFocusStealerRecursive(this);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(statsPanel, BorderLayout.CENTER);
    }

    private void displayStatistics() {
        try {
            UI_Styles.showInformation(
                            RMS_Result_Management_System_UI.mainFrame, Html_Files.getStatisticsDetails(), "Analytics Overview");

        } catch (Exception e) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error displaying statistics !!");
        }
    }
}