package ui;

import utils.*;
import models.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Course_UI extends JPanel {

    private JTextField courseCodeField;
    private JTextField courseTitleField;
    private JTextField creditHoursField;

    public Course_UI() {
        createCoursePanel();
    }

    public void createCoursePanel() {
        this.setLayout(new BorderLayout(20, 20));
        this.setBorder(new Round_Panel_Border_Top_Left(15, new Color(240, 242, 245)));
        this.setOpaque(false);
        this.setBackground(new Color(240, 242, 245));

        JPanel headerPanel = UI_Styles.createHeaderPanel("Course Catalog");

        JPanel formPanel = UI_Styles.createFormPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(UI_Styles.createLabel("Course Code:"), gbc);
        gbc.gridx = 1;
        courseCodeField = UI_Styles.createTextField();
        formPanel.add(courseCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(UI_Styles.createLabel("Title:"), gbc);
        gbc.gridx = 1;
        courseTitleField = UI_Styles.createTextField();
        formPanel.add(courseTitleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(UI_Styles.createLabel("Credit Hours:"), gbc);
        gbc.gridx = 1;
        creditHoursField = UI_Styles.createTextField();
        formPanel.add(creditHoursField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JComboBox<String> instructorCombo = new JComboBox<>();
        instructorCombo.addItem("None");
        UI_Styles.styleComboBox(instructorCombo);
        RMS_Result_Management_System_UI.course_Instructor_Combo = instructorCombo;
        formPanel.add(UI_Styles.createLabel("Instructor:"), gbc);
        gbc.gridx = 1;
        formPanel.add(instructorCombo, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton addBtn = UI_Styles.createActionButton("Add Course", new Color(155, 89, 182), 120);
        JButton updateBtn = UI_Styles.createActionButton("Update", new Color(241, 196, 15), 120);
        JButton deleteBtn = UI_Styles.createActionButton("Delete", new Color(231, 76, 60), 120);
        JButton clearBtn = UI_Styles.createActionButton("Clear", new Color(149, 165, 166), 120);
        JButton viewDetailsBtn = UI_Styles.createActionButton("View Details", new Color(52, 152, 219), 120);

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
        gbc.insets = new Insets(15, 0, 0, 0);
        formPanel.add(buttonPanel, gbc);

        String[] course = { "Course Code", "Title", "Credit Hours", "Instructor" };
        RMS_Result_Management_System_UI.course_Table_Model = new DefaultTableModel(course, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        RMS_Result_Management_System_UI.course_Table = UI_Styles
                .createStyledTable(
                        RMS_Result_Management_System_UI.course_Table_Model);
        RMS_Result_Management_System_UI.course_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        RMS_Result_Management_System_UI.course_Table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadCourseToForm();
            }
        });

        JScrollPane tableScroll = new JScrollPane(RMS_Result_Management_System_UI.course_Table);
        UI_Styles.styleTableScrollPane(tableScroll);

        JPanel topPanel = new JPanel(new BorderLayout(0, 20));
        topPanel.setBackground(new Color(240, 242, 245));
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);

        UI_Styles.makeFocusStealerRecursive(this);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(tableScroll, BorderLayout.CENTER);
    }

    private void addCourse() {
        try {
            String courseCode = courseCodeField.getText().trim();
            String title = courseTitleField.getText().trim();
            String creditHoursString = creditHoursField.getText().trim();
            String instructorName = (String) RMS_Result_Management_System_UI.course_Instructor_Combo.getSelectedItem();

            if (courseCode.isEmpty() || title.isEmpty() || creditHoursString.isEmpty()) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please fill all fields !!");
                return;
            }

            double creditHours;
            try {
                creditHours = Double.parseDouble(creditHoursString);
            } catch (NumberFormatException e) {    
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Credits must be a number !!");
                return;
            }

            for (int i = 0; i < RMS_Result_Management_System_UI.courses.size(); i++) {
                if (RMS_Result_Management_System_UI.courses.get(i).getCourseCode().equals(courseCode)) {
                    UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Course code already exists !!");
                    return;
                }
            }

            Course_Instructor instructor = RMS_Result_Management_System_UI.instructors.search(instructorName);
            Course course = new Course(courseCode, title, creditHours, instructor);
            RMS_Result_Management_System_UI.courses.add(course);

            RMS_Result_Management_System_UI.course_Table_Model.addRow(new Object[] {
                    courseCode,
                    title,
                    creditHours,
                    instructorName
            });

            clearCourseForm();
            Refresh_All.refresh();
            UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame, "Course added successfully !!");

        } catch (Exception e) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error while adding course !!");
        }
    }

    private void updateCourse() {
        int selectedRow = RMS_Result_Management_System_UI.course_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Select a course to update !!");
            return;
        }

        try {
            String courseCode = courseCodeField.getText().trim();
            String title = courseTitleField.getText().trim();
            String creditHoursString = creditHoursField.getText().trim();
            String instructorName = (String) RMS_Result_Management_System_UI.course_Instructor_Combo.getSelectedItem();

            if (courseCode.isEmpty() || title.isEmpty() || creditHoursString.isEmpty() || instructorName == null) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please fill all fields !!");
                return;
            }

            Course oldCourse = RMS_Result_Management_System_UI.courses.get(selectedRow);
            String oldCourseCode = oldCourse.getCourseCode();

            if (!oldCourseCode.equals(courseCode)) {
                for (int i = 0; i < RMS_Result_Management_System_UI.courses.size(); i++) {
                    if (i != selectedRow &&
                            RMS_Result_Management_System_UI.courses.get(i).getCourseCode()
                                    .equalsIgnoreCase(courseCode)) {
                        UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Course code already exists !!");
                        return;
                    }
                }
            }

            double creditHours;
            try {
                creditHours = Double.parseDouble(creditHoursString);
                if (creditHours <= 0) {
                    UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Credit hours must be positive !!");
                    return;
                }
            } catch (NumberFormatException e) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Credit hours must be a number !!");
                return;
            }
            
            Course_Instructor instructor = RMS_Result_Management_System_UI.instructors.search(instructorName);

            if (instructor == null) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Instructor not found !!");
                return;
            }

            // Update the course
            oldCourse.setCourseCode(courseCode);
            oldCourse.setTitle(title);
            oldCourse.setCreditHours(creditHours);
            oldCourse.setCourseInstructor(instructor);
            updateAllStudentResultEntries(oldCourseCode, oldCourse);

            clearCourseForm();
            Refresh_All.refresh();

            UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame, "Course updated successfully !!");

        } catch (Exception e) {
            e.printStackTrace();
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error updating course !!");
        }
    }

    private void updateAllStudentResultEntries(String oldCourseCode, Course updatedCourse) {
        for ( int i = 0; i <  RMS_Result_Management_System_UI.students.size(); i++) {
            Student student = RMS_Result_Management_System_UI.students.get(i);
            boolean updated = false;

            for (Result_Entry resultEntry : student.getTranscript().getResultEntry()) {
                if (resultEntry.getCourse().getCourseCode().equals(oldCourseCode)) {
                    resultEntry.setCourse(updatedCourse);
                    updated = true;
    
                }
            }
            if (updated) {
                student.calculateGPA();
            }
        }
    }

    private void deleteCourse() {
        int selectedRow = RMS_Result_Management_System_UI.course_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please select a course to delete !!");
            return;
        }

        UI_Styles.deleteOptions(RMS_Result_Management_System_UI.mainFrame,
                "Are you sure to delete this course?", option -> {
                    if (option.equals("confirm")) {
                        try {
                            String courseCode = (String) RMS_Result_Management_System_UI.course_Table_Model
                                    .getValueAt(selectedRow, 0);
                            boolean removed = RMS_Result_Management_System_UI.courses.remove(courseCode);

                            for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
                                Student student = RMS_Result_Management_System_UI.students.get(i);
                                if (student.getTranscript() != null
                                        && student.getTranscript().getResultEntry() != null) {
                                    student.getTranscript().getResultEntry()
                                            .removeIf(entry -> entry.getCourse().getCourseCode().equals(courseCode));
                                }
                            }

                            if (removed) {
                                RMS_Result_Management_System_UI.course_Table_Model.removeRow(selectedRow);

                                Refresh_All.refresh();

                                clearCourseForm();
                                Refresh_All.refresh();
                                UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame,
                                        "Course deleted successfully !!");
                            }

                        } catch (Exception e) {
                            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame,
                                    "Error while deleting course !!");
                        }
                    }
                });
    }

    private void loadCourseToForm() {
        int selectedRow = RMS_Result_Management_System_UI.course_Table.getSelectedRow();
        if (selectedRow != -1) {
            courseCodeField.setText((String) RMS_Result_Management_System_UI.course_Table_Model.getValueAt(selectedRow, 0));
            courseTitleField.setText((String) RMS_Result_Management_System_UI.course_Table_Model.getValueAt(selectedRow, 1));
            creditHoursField.setText(
                    RMS_Result_Management_System_UI.course_Table_Model.getValueAt(selectedRow, 2).toString());
            RMS_Result_Management_System_UI.course_Instructor_Combo.setSelectedItem(
                    RMS_Result_Management_System_UI.course_Table_Model.getValueAt(selectedRow, 3));
        }
    }

    private void clearCourseForm() {
        courseCodeField.setText("");
        courseTitleField.setText("");
        creditHoursField.setText("");
        RMS_Result_Management_System_UI.course_Instructor_Combo.setSelectedIndex(0);
        RMS_Result_Management_System_UI.course_Table.clearSelection();
    }

    private void viewCourseDetails() {
        int selectedRow = RMS_Result_Management_System_UI.course_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please select a course first !!");
            return;
        }

        try {
            Course course = RMS_Result_Management_System_UI.courses.get(selectedRow);

            UI_Styles.showInformation(
                    RMS_Result_Management_System_UI.mainFrame, utils.Html_Files.getCourseDetails(course), "Course Details");

        } catch (Exception e) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error while showing details !!");
        }
    }
}
