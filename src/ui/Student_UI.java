package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import models.*;
import utils.*;

public class Student_UI extends JPanel {

    private JTextField studentIdField;
    private JTextField studentNameField;
    private JTextField studentProgramField;

    public Student_UI() {
        createStudentPanel();
    }

    public void createStudentPanel() {
        this.setLayout(new BorderLayout(20, 20));
        this.setBorder(new Round_Panel_Border_Top_Left(15, new Color(240, 242, 245)));
        this.setOpaque(false);
        this.setBackground(new Color(240, 242, 245));

        JPanel headerPanel = UI_Styles.createHeaderPanel("Student Management");

        JPanel formPanel = UI_Styles.createFormPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0: Student ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(UI_Styles.createLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        studentIdField = UI_Styles.createTextField();
        formPanel.add(studentIdField, gbc);

        // Row 1: Full Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(UI_Styles.createLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        studentNameField = UI_Styles.createTextField();
        formPanel.add(studentNameField, gbc);

        // Row 2: Degree Program
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(UI_Styles.createLabel("Program:"), gbc);
        gbc.gridx = 1;
        studentProgramField = UI_Styles.createTextField();
        formPanel.add(studentProgramField, gbc);

        // Row 3: Category
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(UI_Styles.createLabel("Category:"), gbc);
        gbc.gridx = 1;
        RMS_Result_Management_System_UI.student_Type_Combo = new JComboBox<>(
                new String[] { "Arts Student", "Science Student", "Engineering Student" });
        UI_Styles.styleComboBox(RMS_Result_Management_System_UI.student_Type_Combo);
        formPanel.add(RMS_Result_Management_System_UI.student_Type_Combo, gbc);

        // Row 4: Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBackground(Color.WHITE);

        JButton addBtn = UI_Styles.createActionButton("Add Student", new Color(37, 211, 102), 125);
        JButton updateBtn = UI_Styles.createActionButton("Update", new Color(52, 152, 219), 125);
        JButton deleteBtn = UI_Styles.createActionButton("Delete", new Color(231, 76, 60), 125);
        JButton clearBtn = UI_Styles.createActionButton("Clear", new Color(149, 165, 166), 125);
        JButton viewButton = UI_Styles.createActionButton("View Details", new Color(155, 89, 182), 125);

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(viewButton);

        // Set Up Listeners
        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        clearBtn.addActionListener(e -> clearStudentForm());
        viewButton.addActionListener(e -> viewStudentDetails());

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 0, 0);
        formPanel.add(buttonPanel, gbc);

        // 4. TABLE SECTION
        String[] studentColumns = { "Student ID", "Name", "Program", "Type", "GPA" };
        RMS_Result_Management_System_UI.student_Table_Model = new DefaultTableModel(studentColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        RMS_Result_Management_System_UI.student_Table = UI_Styles
                .createStyledTable(RMS_Result_Management_System_UI.student_Table_Model);
        JScrollPane tableScroll = new JScrollPane(RMS_Result_Management_System_UI.student_Table);
        UI_Styles.styleTableScrollPane(tableScroll);

        RMS_Result_Management_System_UI.student_Table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())
                loadStudentToForm();
        });

        JPanel topPanel = new JPanel(new BorderLayout(0, 20));
        topPanel.setBackground(new Color(240, 242, 245));
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);

        UI_Styles.makeFocusStealerRecursive(this);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(tableScroll, BorderLayout.CENTER);
    }

    public void addStudent() {
        try {
            String studentId = studentIdField.getText().trim();
            String name = studentNameField.getText().trim();
            String program = studentProgramField.getText().trim();
            String type = (String) RMS_Result_Management_System_UI.student_Type_Combo.getSelectedItem();

            if (studentId.isEmpty() || name.isEmpty() || program.isEmpty()) {
                
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please fill all fields !!");
                return;
            }

            for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
                if (RMS_Result_Management_System_UI.students.get(i).getStudentID().equals(studentId)) {    
                    UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Student ID already exists !!");
                    return;
                }
            }

            Student student;
            Transcript transcript = new Transcript();

            switch (type) {
                case ("Arts Student"): {
                    student = new Arts_Student(studentId, name, program, transcript);
                }
                    break;
                case ("Science Student"): {
                    student = new Science_Student(studentId, name, program, transcript);
                }
                    break;
                case "Engineering Student": {
                    student = new Engineering_Student(studentId, name, program, transcript);
                }
                    break;
                default: {
                    student = new Arts_Student(studentId, name, program, transcript);
                }
            }

            RMS_Result_Management_System_UI.students.add(student);
            RMS_Result_Management_System_UI.student_Table_Model.addRow(new Object[] {
                    studentId,
                    name,
                    program,
                    type,
                    student.calculateGPA()
            });

            clearStudentForm();
            Refresh_All.refresh();

            UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame, "Student added successfully !!");

        } catch (Exception e) {    
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error While saving Student !!");
        }
    }

    private void updateStudent() {
        int selectedRow = RMS_Result_Management_System_UI.student_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Select a student to update !!");
            return;
        }

        try {
            String studentId = studentIdField.getText().trim();
            String name = studentNameField.getText().trim();
            String program = studentProgramField.getText().trim();
            String type = (String) RMS_Result_Management_System_UI.student_Type_Combo.getSelectedItem();

            if (studentId.isEmpty() || name.isEmpty() || program.isEmpty()) {
                UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please fill all fields !!");
                return;
                
            }
            
            for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
                if (i != selectedRow
                        && RMS_Result_Management_System_UI.students.get(i).getStudentID().equals(studentId)) {
                    UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Student ID already exists !!");
                    return;
                }
            }

            Student oldStudent = RMS_Result_Management_System_UI.students.get(selectedRow);
            Transcript existingTranscript = oldStudent.getTranscript();

            Student updatedStudent;
            switch (type) {
                case "Science Student":
                    updatedStudent = new Science_Student(studentId, name, program, existingTranscript);
                    break;
                case "Engineering Student":
                    updatedStudent = new Engineering_Student(studentId, name, program, existingTranscript);
                    break;
                default:
                    updatedStudent = new Arts_Student(studentId, name, program, existingTranscript);
                    break;
            }

            RMS_Result_Management_System_UI.students.set(selectedRow, updatedStudent);

            RMS_Result_Management_System_UI.student_Table_Model.setValueAt(studentId, selectedRow, 0);
            RMS_Result_Management_System_UI.student_Table_Model.setValueAt(name, selectedRow, 1);
            RMS_Result_Management_System_UI.student_Table_Model.setValueAt(program, selectedRow, 2);
            RMS_Result_Management_System_UI.student_Table_Model.setValueAt(type, selectedRow, 3);

            clearStudentForm();
            Refresh_All.refresh();

            UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame, "Student updated successfully !!");

        } catch (Exception e) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error while updating student !!");
        }
    }

    private void deleteStudent() {
        int selectedRow = RMS_Result_Management_System_UI.student_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Select a student to delete !!");
            return;
        }

        UI_Styles.deleteOptions(RMS_Result_Management_System_UI.mainFrame,
                "Are you sure to delete this student?", option -> {
                    if (option.equals("confirm")) {
                        try {
                            String studentId = (String) RMS_Result_Management_System_UI.student_Table_Model
                                    .getValueAt(selectedRow, 0);
                            boolean removed = RMS_Result_Management_System_UI.students.remove(studentId);

                            if (removed) {
                                RMS_Result_Management_System_UI.student_Table_Model.removeRow(selectedRow);

                                clearStudentForm();
                                Refresh_All.refresh();
                                UI_Styles.showNotification(RMS_Result_Management_System_UI.mainFrame,
                                        "Student deleted successfully !!");
                            }
                        } catch (Exception e) {
                            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame,
                                    "Error while deleting student!!");
                        }
                    }
                });
    }

    private void loadStudentToForm() {
        try {
            int selectedRow = RMS_Result_Management_System_UI.student_Table.getSelectedRow();
            if (selectedRow >= 0) {
                Student student = RMS_Result_Management_System_UI.students.get(selectedRow);
                studentIdField.setText(student.getStudentID());
                studentNameField.setText(student.getName());
                studentProgramField.setText(student.getProgram());

                String type = student.getStudentType();
                if (type.equals("Arts Student")) {
                    RMS_Result_Management_System_UI.student_Type_Combo.setSelectedIndex(0);
                } else if (type.equals("Science Student")) {
                    RMS_Result_Management_System_UI.student_Type_Combo.setSelectedIndex(1);
                } else if (type.equals("Engineering Student")) {
                    RMS_Result_Management_System_UI.student_Type_Combo.setSelectedIndex(2);
                }
            }
        } catch (Exception e) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error while Loading student !!");
        }
    }

    private void clearStudentForm() {
        studentIdField.setText("");
        studentNameField.setText("");
        studentProgramField.setText("");
        RMS_Result_Management_System_UI.student_Type_Combo.setSelectedIndex(0);
        RMS_Result_Management_System_UI.student_Table.clearSelection();
    }

    private void viewStudentDetails() {
        int selectedRow = RMS_Result_Management_System_UI.student_Table.getSelectedRow();
        if (selectedRow == -1) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Please select a student first !!");
            return;
        }

        try {
            Student student = RMS_Result_Management_System_UI.students.get(selectedRow);
            UI_Styles.showInformation(
                    RMS_Result_Management_System_UI.mainFrame, Html_Files.getStudentDetails(student), "Student Details");
        } catch (Exception e) {
            UI_Styles.showError(RMS_Result_Management_System_UI.mainFrame, "Error while showing Details !!");
        }
    }
}