package ui;

import models.*;
import utils.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Result_UI extends JPanel {
    
    private JTextField marksField;

    public Result_UI() {
        createResultPanel();
    }

    public void createResultPanel() {
        this.setLayout(new BorderLayout(20, 20));
        this.setBorder(new Round_Panel_Border_Top_Left(15, new Color(240, 242, 245)));
        this.setOpaque(false);
        this.setBackground(new Color(240, 242, 245));
        JPanel headerPanel = UI_Styles.createHeaderPanel("Results Records");

        JPanel formPanel = UI_Styles.createFormPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(UI_Styles.createLabel("Student:"), gbc);
        gbc.gridx = 1;
        RMS_Result_Management_System_UI.result_Student_Combo = new JComboBox<>();
        UI_Styles.styleComboBox(RMS_Result_Management_System_UI.result_Student_Combo);
        formPanel.add(RMS_Result_Management_System_UI.result_Student_Combo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(UI_Styles.createLabel("Course:"), gbc);
        gbc.gridx = 1;
        RMS_Result_Management_System_UI.result_Course_Combo = new JComboBox<>();
        UI_Styles.styleComboBox(RMS_Result_Management_System_UI.result_Course_Combo);
        formPanel.add(RMS_Result_Management_System_UI.result_Course_Combo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(UI_Styles.createLabel("Marks (0-100):"), gbc);
        gbc.gridx = 1;
        marksField = UI_Styles.createTextField();
        formPanel.add(marksField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBackground(Color.WHITE);

        JButton addBtn = UI_Styles.createActionButton("Add Result", new Color(46, 204, 113), 120);
        JButton updateBtn = UI_Styles.createActionButton("Update", new Color(241, 196, 15), 120);
        JButton deleteBtn = UI_Styles.createActionButton("Delete", new Color(231, 76, 60), 120);
        JButton clearBtn = UI_Styles.createActionButton("Clear", new Color(149, 165, 166), 120);
        JButton viewTranscriptBtn = UI_Styles.createActionButton("View Transcript", new Color(155, 89, 182), 120);
    
        addBtn.addActionListener(e -> addResult());
        updateBtn.addActionListener(e -> updateResult());
        deleteBtn.addActionListener(e -> deleteResult());
        clearBtn.addActionListener(e -> clearResultForm());
        viewTranscriptBtn.addActionListener(e -> viewTranscript());

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(viewTranscriptBtn);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 0, 0);
        formPanel.add(buttonPanel, gbc);

        String[] result = { "Student ID", "Student Name", "Course Code", "Course Title", "Marks", "Grade",
                "Grade Points" };
        RMS_Result_Management_System_UI.result_Table_Model = new DefaultTableModel(result, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        RMS_Result_Management_System_UI.result_Table = UI_Styles.createStyledTable(RMS_Result_Management_System_UI.result_Table_Model);
        RMS_Result_Management_System_UI.result_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        RMS_Result_Management_System_UI.result_Table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadResultToForm();
            }
        });

        JScrollPane tableScroll = new JScrollPane(RMS_Result_Management_System_UI.result_Table);
        UI_Styles.styleTableScrollPane(tableScroll);

        JPanel topPanel = new JPanel(new BorderLayout(0, 20));
        topPanel.setBackground(new Color(240, 242, 245));
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);

        UI_Styles.makeFocusStealerRecursive(this);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(tableScroll, BorderLayout.CENTER);
    }

    private void addResult() {
        try {
            String studentID = (String) RMS_Result_Management_System_UI.result_Student_Combo.getSelectedItem();
            String courseCode = (String) RMS_Result_Management_System_UI.result_Course_Combo.getSelectedItem();
            String marksString = marksField.getText().trim();

            if (studentID == null || courseCode == null || marksString.isEmpty()) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please fill all fields !!");
                return;
            }

            if (studentID.equals("None") && courseCode.equals("None")) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "No Registered Students And Courses Found !!");
                return;
            }

            if (studentID.equals("None")) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "No Registered Students Found !!");
                return;
            }

            if (courseCode.equals("None")) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "No Registered Courses Found !!");
                return;
            }

            double marks;
            try {
                marks = Double.parseDouble(marksString);
                if (marks < 0 || marks > 100) {
                    UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Marks must be between 0 and 100 !!");
                    return;
                }
            } catch (NumberFormatException e) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Marks must be a number !!");
                return;
            }

            Student student = RMS_Result_Management_System_UI.students.search(studentID);
            Course course = RMS_Result_Management_System_UI.courses.search(courseCode);

            if (student == null || course == null) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Student or course not found !!");
                return;
            }

            for (int i = 0; i < student.getTranscript().getResultEntry().size(); i++) {
                Result_Entry r = student.getTranscript().getResultEntry().get(i);
                if (r.getCourse().getCourseCode().equals(courseCode)) {
                    UI_Styles.showError(
                                RMS_Result_Management_System_UI.mainFrame, "Course Result already exists !!");
                    return;
                }
            }

            student.addCourse(course, marks);
            Result_Entry entry = student.getTranscript().getResultEntry()
                    .get(student.getTranscript().getResultEntry().size() - 1);

            RMS_Result_Management_System_UI.result_Table_Model.addRow(new Object[] {
                    student.getStudentID(),
                    student.getName(),
                    course.getCourseCode(),
                    course.getTitle(),
                    marks,
                    entry.getGrade(),
                    String.format("%.2f", entry.getGradePoints())
            });

            clearResultForm();
            Refresh_All.refresh();

            UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame, "Result added successfully !!");
        } catch (Exception e) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error while Adding Result !!");
        }
    }

    private void clearResultForm() {
        RMS_Result_Management_System_UI.result_Student_Combo.setSelectedIndex(0);
        RMS_Result_Management_System_UI.result_Course_Combo.setSelectedIndex(0);
        marksField.setText("");
        RMS_Result_Management_System_UI.result_Table.clearSelection();
    }

    private void updateResult() {

        int selectedRow = RMS_Result_Management_System_UI.result_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please select a result to update !!");
            return;
        }

        try {
            String oldStudentID = (String) RMS_Result_Management_System_UI.result_Table_Model.getValueAt(selectedRow,
                    0);
            String oldCourseCode = (String) RMS_Result_Management_System_UI.result_Table_Model.getValueAt(selectedRow,
                    2);

            String newStudentID = (String) RMS_Result_Management_System_UI.result_Student_Combo.getSelectedItem();
            String newCourseCode = (String) RMS_Result_Management_System_UI.result_Course_Combo.getSelectedItem();
            String marksString = marksField.getText().trim();

            if (newStudentID == null || newCourseCode == null || marksString.isEmpty()) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please fill all fields !!");
                return;
            }

            double marks;
            try {
                marks = Double.parseDouble(marksString);
                if (marks < 0 || marks > 100) {
                    UI_Styles.showError(
                            RMS_Result_Management_System_UI.mainFrame, "Marks must be between 0 and 100 !!");
                    return;
                }
            } catch (NumberFormatException e) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Marks must be a number !!");
                return;
            }

            Student oldStudent = RMS_Result_Management_System_UI.students.search(oldStudentID);
            Student newStudent = RMS_Result_Management_System_UI.students.search(newStudentID);
            Course newCourse = RMS_Result_Management_System_UI.courses.search(newCourseCode);

            if (oldStudent == null || newStudent == null || newCourse == null) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Student or course not found !!");
                return;
            }

            oldStudent.getTranscript().getResultEntry()
                    .removeIf(r -> r.getCourse().getCourseCode().equals(oldCourseCode));

            for (Result_Entry r : newStudent.getTranscript().getResultEntry()) {
                if (r.getCourse().getCourseCode().equals(newCourseCode)) {
                    UI_Styles.showError(
                            RMS_Result_Management_System_UI.mainFrame, "Result already exists for this course !!");
                    return;
                }
            }

            newStudent.addCourse(newCourse, marks);
            // Result_Entry entry = newStudent.getTranscript().getResultEntry()
            //         .get(newStudent.getTranscript().getResultEntry().size() - 1);

            // CUI_Result_Management_System_UI.result_Table_Model.setValueAt(newStudent.getStudentID(), selectedRow, 0);
            // CUI_Result_Management_System_UI.result_Table_Model.setValueAt(newStudent.getName(), selectedRow, 1);
            // CUI_Result_Management_System_UI.result_Table_Model.setValueAt(newCourse.getCourseCode(), selectedRow, 2);
            // CUI_Result_Management_System_UI.result_Table_Model.setValueAt(newCourse.getTitle(), selectedRow, 3);
            // CUI_Result_Management_System_UI.result_Table_Model.setValueAt(marks, selectedRow, 4);
            // CUI_Result_Management_System_UI.result_Table_Model.setValueAt(entry.getGrade(), selectedRow, 5);
            // CUI_Result_Management_System_UI.result_Table_Model.setValueAt(
            //         String.format("%.2f", entry.getGradePoints()), selectedRow, 6);

            clearResultForm();
            Refresh_All.refresh();

            UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame, "Result updated successfully !!");

        } catch (Exception e) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error updating result !!");
        }
    }

    private void deleteResult() {
        int selectedRow = RMS_Result_Management_System_UI.result_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please select a result to delete !!");
            return;
        }

        UI_Styles.deleteOptions(RMS_Result_Management_System_UI.mainFrame,
                "Are you sure to delete this entry?", option -> {
                    if (option.equals("confirm")) {
                        try {
                            String studentID = (String) RMS_Result_Management_System_UI.result_Table_Model
                                    .getValueAt(selectedRow, 0);
                            String courseCode = (String) RMS_Result_Management_System_UI.result_Table_Model
                                    .getValueAt(selectedRow, 2);

                            Student student = RMS_Result_Management_System_UI.students.search(studentID);
                            Course course = RMS_Result_Management_System_UI.courses.search(courseCode);

                            if (student == null || course == null) {
                                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame,
                                        "Student or course not found !!");
                                return;
                            }

                            student.getTranscript().getResultEntry()
                                    .removeIf(r -> r.getCourse().getCourseCode().equals(courseCode));

                            RMS_Result_Management_System_UI.result_Table_Model.removeRow(selectedRow);
                            clearResultForm();
                            Refresh_All.refresh();

                            UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame,
                                    "Result deleted successfully !!");
                        } catch (Exception e) {
                            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame,
                                    "Error while deleting course !!");
                        }
                    }
                });
    }

    private void viewTranscript() {
        
        String studentId = (String) RMS_Result_Management_System_UI.result_Student_Combo.getSelectedItem();
        if (studentId == null || studentId.isEmpty() || studentId.equals("None")) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please select a student !!");
            return;
        }

        Student student = RMS_Result_Management_System_UI.students.search(studentId);

        if (student == null) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Student not found !!");
            return;
        }
            
        try {
            UI_Styles.showInformation(
                    RMS_Result_Management_System_UI.mainFrame, Html_Files.getStudentDetails(student), "Student Result");
        } catch (Exception e) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error while showing Result !!");
            
        }
    }

    private void loadResultToForm() {
        int selectedRow = RMS_Result_Management_System_UI.result_Table.getSelectedRow();
        if (selectedRow != -1) {
            String studentID = (String) RMS_Result_Management_System_UI.result_Table_Model.getValueAt(selectedRow, 0);
            String courseCode = (String) RMS_Result_Management_System_UI.result_Table_Model.getValueAt(selectedRow, 2);
            Object marksObj = RMS_Result_Management_System_UI.result_Table_Model.getValueAt(selectedRow, 4);
            RMS_Result_Management_System_UI.result_Student_Combo.setSelectedItem(studentID);
            RMS_Result_Management_System_UI.result_Course_Combo.setSelectedItem(courseCode);

            if (marksObj != null) {
                marksField.setText(marksObj.toString());
            }
        }
    }
}