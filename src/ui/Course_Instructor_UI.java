package ui;

import models.*;
import utils.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Course_Instructor_UI extends JPanel {

    private JTextField instructorNameField;
    private JTextField instructorQualificationField;
    private JTextField instructorProgramField;
    
    public Course_Instructor_UI() {
        createInstructorPanel();
    }

    public void createInstructorPanel() {
        this.setLayout(new BorderLayout(20, 20));
        this.setBorder(new Round_Panel_Border_Top_Left(15, new Color(240, 242, 245)));
        this.setOpaque(false);
        this.setBackground(new Color(240, 242, 245));

        JPanel headerPanel = UI_Styles.createHeaderPanel("Instructor Directory");

        JPanel formPanel = UI_Styles.createFormPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(UI_Styles.createLabel("Name:"), gbc);
        gbc.gridx = 1;
        instructorNameField = UI_Styles.createTextField();
        formPanel.add(instructorNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(UI_Styles.createLabel("Qualification:"), gbc);
        gbc.gridx = 1;
        instructorQualificationField = UI_Styles.createTextField();
        formPanel.add(instructorQualificationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(UI_Styles.createLabel("Program:"), gbc);
        gbc.gridx = 1;
        instructorProgramField = UI_Styles.createTextField();
        formPanel.add(instructorProgramField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.setBackground(Color.WHITE);

        JButton addBtn = UI_Styles.createActionButton("Add Instructor", new Color(241, 196, 15), 120);
        JButton updateBtn = UI_Styles.createActionButton("Update", new Color(52, 152, 219), 120);
        JButton deleteBtn = UI_Styles.createActionButton("Delete", new Color(231, 76, 60), 120);
        JButton clearBtn = UI_Styles.createActionButton("Clear", new Color(149, 165, 166), 120);
        JButton viewDetailsBtn = UI_Styles.createActionButton("View Details", new Color(155, 89, 182), 120);

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
        gbc.insets = new Insets(15, 0, 0, 0);
        formPanel.add(buttonPanel, gbc);

        String[] instructor = { "Name", "Qualification", "Program", "Courses Taught" };
        RMS_Result_Management_System_UI.instructor_Table_Model = new DefaultTableModel(instructor, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        RMS_Result_Management_System_UI.instructor_Table = UI_Styles
                .createStyledTable(RMS_Result_Management_System_UI.instructor_Table_Model);
        RMS_Result_Management_System_UI.instructor_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        RMS_Result_Management_System_UI.instructor_Table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadInstructorToForm();
            }
        });

        JScrollPane tableScroll = new JScrollPane(RMS_Result_Management_System_UI.instructor_Table);
        UI_Styles.styleTableScrollPane(tableScroll);

        JPanel topPanel = new JPanel(new BorderLayout(0, 20));
        topPanel.setBackground(new Color(240, 242, 245));
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        UI_Styles.makeFocusStealerRecursive(this);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(tableScroll, BorderLayout.CENTER);
    }

    private void addCourseInstructor() {
        try {
            String name = instructorNameField.getText().trim();
            String qualification = instructorQualificationField.getText().trim();
            String program = instructorProgramField.getText().trim();

            if (name.isEmpty() || qualification.isEmpty() || program.isEmpty()) {
                
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please fill all fields !");
                return;
            }

            for (int i = 0; i < RMS_Result_Management_System_UI.instructors.size(); i++) {
                Course_Instructor instructor = RMS_Result_Management_System_UI.instructors.get(i);
                if (instructor.getName().equals(name)) {
                    
                    UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Instructor already exists !!");
                    return;
                }
            }

            Course_Instructor courseInstructor = new Course_Instructor(name, qualification, program, 0);
            RMS_Result_Management_System_UI.instructors.add(courseInstructor);

            RMS_Result_Management_System_UI.instructor_Table_Model.addRow(new Object[] {
                    name,
                    qualification,
                    program,
                    courseInstructor.getCoursesTaught()
            });

            clearCourseInstructorForm();
            Refresh_All.refresh();
            UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame, "Instructor added successfully !!");

        } catch (Exception e) {
            
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error While adding instructor !!");
        }
    }

    private void updateCourseInstructor() {

        int selectedRow = RMS_Result_Management_System_UI.instructor_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please select an instructor to update !!");
            return;
        }

        try {
            String name = instructorNameField.getText().trim();
            String qualification = instructorQualificationField.getText().trim();
            String program = instructorProgramField.getText().trim();

            if (name.isEmpty() || qualification.isEmpty() || program.isEmpty()) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please fill all fields !!");
                return;
            }

            Course_Instructor instructor = RMS_Result_Management_System_UI.instructors.get(selectedRow);
            String oldName = instructor.getName();

            if (!name.equals(oldName)) {
                for (int i = 0; i < RMS_Result_Management_System_UI.instructors.size(); i++) {
                    if (i == selectedRow)
                        continue;

                    Course_Instructor existingInstructor = RMS_Result_Management_System_UI.instructors.get(i);
                    if (existingInstructor.getName().equals(name)) {
                        UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Another instructor already has this name!");
                        return;
                    }
                }
            }

            instructor.setName(name);
            instructor.setQualificaion(qualification);
            instructor.setProgram(program);
            // instructor.setCoursesTaught(instructor.getCoursesTaught());

            RMS_Result_Management_System_UI.instructor_Table_Model.setValueAt(name, selectedRow, 0);
            RMS_Result_Management_System_UI.instructor_Table_Model.setValueAt(qualification, selectedRow, 1);
            RMS_Result_Management_System_UI.instructor_Table_Model.setValueAt(program, selectedRow, 2);
            RMS_Result_Management_System_UI.instructor_Table_Model.setValueAt(
                    instructor.getCoursesTaught(), selectedRow, 3);

            clearCourseInstructorForm();
            Refresh_All.refresh();

            UI_Styles.showNotification(
                        RMS_Result_Management_System_UI.mainFrame, "Instructor updated successfully !!");

        } catch (Exception e) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error while updating instructor !!");
            
        }
    }

    private void deleteCourseInstructor() {
        int selectedRow = RMS_Result_Management_System_UI.instructor_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(
                        RMS_Result_Management_System_UI.mainFrame, "Please select an instructor to delete !!");
            return;
        }

        UI_Styles.deleteOptions(RMS_Result_Management_System_UI.mainFrame,
                "Are you sure to delete this instructor?", option -> {
                    if (option.equals("confirm")) {
                        try {

                            String name = (String) RMS_Result_Management_System_UI.instructor_Table_Model
                                    .getValueAt(selectedRow, 0);

                            for (int i = 0; i < RMS_Result_Management_System_UI.courses.size(); i++) {
                                Course c = RMS_Result_Management_System_UI.courses.get(i);
                                if (c.getCourseInstructor() != null &&
                                        c.getCourseInstructor().getName().equals(name)) {
                                    c.setCourseInstructor(null);
                                }
                            }

                            boolean removed = RMS_Result_Management_System_UI.instructors.remove(name);

                            if (removed) {
                                RMS_Result_Management_System_UI.instructor_Table_Model.removeRow(selectedRow);
                                clearCourseInstructorForm();
                                Refresh_All.refresh();
                                UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame,
                                        "Instructor deleted successfully !!");
                            }

                        } catch (Exception e) {
                            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame,
                                    "Error while deleting instructor !!");
                        }
                    }
                    else {
                        return;
                    }
                });
        
    }

    private void loadInstructorToForm() {
        int selectedRow = RMS_Result_Management_System_UI.instructor_Table.getSelectedRow();
        if (selectedRow != -1) {
            instructorNameField.setText((String) RMS_Result_Management_System_UI.instructor_Table_Model.getValueAt(selectedRow, 0));
            instructorQualificationField.setText((String) RMS_Result_Management_System_UI.instructor_Table_Model.getValueAt(selectedRow, 1));
            instructorProgramField.setText((String) RMS_Result_Management_System_UI.instructor_Table_Model.getValueAt(selectedRow, 2));
        }
    }

    private void clearCourseInstructorForm() {
        instructorNameField.setText("");
        instructorQualificationField.setText("");
        instructorProgramField.setText("");
        RMS_Result_Management_System_UI.instructor_Table.clearSelection();
    }

    private void viewCourseInstructorDetails() {
        int selectedRow = RMS_Result_Management_System_UI.instructor_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please select an instructor first !!");
            return;
        }

        try {
            Course_Instructor instructor = RMS_Result_Management_System_UI.instructors.get(selectedRow);
            UI_Styles.showInformation(
                    RMS_Result_Management_System_UI.mainFrame, utils.Html_Files.getInstructorDetails(instructor), "Instructor Details");

        } catch (Exception e) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error While Displaying Details !!");
            
            return;
        }
    }
}
