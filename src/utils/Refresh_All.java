package utils;

import models.*;
import ui.*;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class Refresh_All {

    public static void refresh() {
        SwingUtilities.invokeLater(() -> {
        update_Student_Combo();
        update_Course_Combo();
        update_Instructor_Combo();
        refresh_Student_Table();
        refresh_Course_Table();
        refresh_Instructor_Table();
        refresh_Result_Table();
        refresh_Statistics();
    });
    }

    private static void update_Student_Combo() {
        RMS_Result_Management_System_UI.pdf_Report_Student_Combo.removeAllItems();
        RMS_Result_Management_System_UI.result_Student_Combo.removeAllItems();
        

        for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
            String id = RMS_Result_Management_System_UI.students.get(i).getStudentID();
            RMS_Result_Management_System_UI.pdf_Report_Student_Combo.addItem(id);
            RMS_Result_Management_System_UI.result_Student_Combo.addItem(id);
        }

        if (RMS_Result_Management_System_UI.result_Student_Combo.getItemCount() == 0) {
            RMS_Result_Management_System_UI.result_Student_Combo.addItem("None");
        }
        if (RMS_Result_Management_System_UI.pdf_Report_Student_Combo.getItemCount() == 0) {
            RMS_Result_Management_System_UI.pdf_Report_Student_Combo.addItem("None");
        }
    }

    private static void update_Course_Combo() {
        RMS_Result_Management_System_UI.result_Course_Combo.removeAllItems();
        RMS_Result_Management_System_UI.pdf_Report_Course_Combo.removeAllItems();

        for (int i = 0; i < RMS_Result_Management_System_UI.courses.size(); i++) {
            String code = RMS_Result_Management_System_UI.courses.get(i).getCourseCode();
            RMS_Result_Management_System_UI.result_Course_Combo.addItem(code);
            RMS_Result_Management_System_UI.pdf_Report_Course_Combo.addItem(code);
        }

        if (RMS_Result_Management_System_UI.result_Course_Combo.getItemCount() == 0) {
            RMS_Result_Management_System_UI.result_Course_Combo.addItem("None");
        }
        if (RMS_Result_Management_System_UI.pdf_Report_Course_Combo.getItemCount() == 0) {
            RMS_Result_Management_System_UI.pdf_Report_Course_Combo.addItem("None");
        }
    }

    private static void update_Instructor_Combo() {
        RMS_Result_Management_System_UI.course_Instructor_Combo.removeAllItems();
        RMS_Result_Management_System_UI.pdf_Report_Instructor_Combo.removeAllItems();

        for (int i = 0; i < RMS_Result_Management_System_UI.instructors.size(); i++) {
            String name = RMS_Result_Management_System_UI.instructors.get(i).getName();
            RMS_Result_Management_System_UI.course_Instructor_Combo.addItem(name);
            RMS_Result_Management_System_UI.pdf_Report_Instructor_Combo.addItem(name);
        }

        if (RMS_Result_Management_System_UI.course_Instructor_Combo.getItemCount() == 0) {
            RMS_Result_Management_System_UI.course_Instructor_Combo.addItem("None");
        }
        if (RMS_Result_Management_System_UI.pdf_Report_Instructor_Combo.getItemCount() == 0) {
            RMS_Result_Management_System_UI.pdf_Report_Instructor_Combo.addItem("None");
        }
    }

    private static void refresh_Student_Table() {
        DefaultTableModel model = RMS_Result_Management_System_UI.student_Table_Model;
        model.setRowCount(0);
        for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
            Student s = RMS_Result_Management_System_UI.students.get(i);
            model.addRow(new Object[] { s.getStudentID(), s.getName(), s.getProgram(), s.getStudentType(),
                    String.format("%.2f", s.calculateGPA()) });
        }
    }

    private static void refresh_Course_Table() {
        DefaultTableModel model = RMS_Result_Management_System_UI.course_Table_Model;
        model.setRowCount(0);
        for (int i = 0; i < RMS_Result_Management_System_UI.courses.size(); i++) {
            Course c = RMS_Result_Management_System_UI.courses.get(i);
            String instructor = (c.getCourseInstructor() != null) ? c.getCourseInstructor().getName() : "None";
            model.addRow(new Object[] { c.getCourseCode(), c.getTitle(), c.getCreditHours(), instructor });
        }
    }

    private static void refresh_Instructor_Table() {
        DefaultTableModel model = RMS_Result_Management_System_UI.instructor_Table_Model;
        model.setRowCount(0);
        for (int i = 0; i < RMS_Result_Management_System_UI.instructors.size(); i++) {
            Course_Instructor ins = RMS_Result_Management_System_UI.instructors.get(i);
            model.addRow(new Object[] { ins.getName(), ins.getQualificaion(), ins.getProgram(), ins.getCoursesTaught() });
        }
    }

    private static void refresh_Result_Table() {
        DefaultTableModel model = RMS_Result_Management_System_UI.result_Table_Model;
        model.setRowCount(0);
        for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
            Student s = RMS_Result_Management_System_UI.students.get(i);
            if (s.getTranscript() != null && s.getTranscript().getResultEntry() != null) {
                for (Result_Entry entry : s.getTranscript().getResultEntry()) {
                    model.addRow(new Object[] {
                            s.getStudentID(), s.getName(), entry.getCourse().getCourseCode(),
                            entry.getCourse().getTitle(), entry.getMarksObtained(),
                            entry.getGrade(), String.format("%.2f", entry.getGradePoints())
                    });
                }
            }
        }
    }

    public static void refresh_Statistics() {

        int totalResultEntries = 0;
        int studentsWithResult = 0;
        int studentWithoutResult = 0;
        int instructorsWithCourses = 0;
        int instructorsWithoutCourses = 0;
        int artsStudents = 0;
        int scienceStudents = 0;
        int engineeringStudents = 0;

        for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
            Student student = RMS_Result_Management_System_UI.students.get(i);
            if (student.getTranscript() != null &&
                    student.getTranscript().getResultEntry() != null &&
                    student.getTranscript().getResultEntry().size() > 0) {

                studentsWithResult++;
                totalResultEntries += student.getTranscript().getResultEntry().size();

            } else {
                studentWithoutResult++;
            }
        }

        for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
            String type = RMS_Result_Management_System_UI.students.get(i).getStudentType();
            if (type.equals("Arts Student")) {
                artsStudents++;
            } else if (type.equals("Science Student")) {
                scienceStudents++;
            } else if (type.equals("Engineering Student")) {
                engineeringStudents++;
            }
        }

        for (int i = 0; i < RMS_Result_Management_System_UI.instructors.size(); i++) {
            Course_Instructor ins = RMS_Result_Management_System_UI.instructors.get(i);
            if (ins.getCoursesTaught() > 0) {
                instructorsWithCourses++;
            } else {
                instructorsWithoutCourses++;
            }
        }

        RMS_Result_Management_System_UI.total_Students_Statisticts.setText(
            String.valueOf(RMS_Result_Management_System_UI.students.size()));
        RMS_Result_Management_System_UI.total_Arts_Students_Statisticts.setText(String.valueOf(artsStudents));
        RMS_Result_Management_System_UI.total_Science_Students_Statisticts.setText(String.valueOf(scienceStudents));
        RMS_Result_Management_System_UI.total_Engineering_Students_Statisticts.setText(String.valueOf(engineeringStudents));
        RMS_Result_Management_System_UI.total_Courses_Statisticts.setText(
                String.valueOf(RMS_Result_Management_System_UI.courses.size()));
        RMS_Result_Management_System_UI.total_Instructors_Statisticts.setText(
                String.valueOf(RMS_Result_Management_System_UI.instructors.size()));
        RMS_Result_Management_System_UI.total_Results_Statisticts.setText(String.valueOf(totalResultEntries));
        RMS_Result_Management_System_UI.students_With_Results_Statisticts.setText(String.valueOf(studentsWithResult));
        RMS_Result_Management_System_UI.students_Without_Results_Statisticts.setText(String.valueOf(
                studentWithoutResult));
        RMS_Result_Management_System_UI.Instructors_With_Courses_Statisticts.setText(String.valueOf(
                instructorsWithCourses));
        RMS_Result_Management_System_UI.Instructors_Without_Courses_Statisticts
                .setText(String.valueOf(instructorsWithoutCourses));

        RMS_Result_Management_System_UI.passing_Marks_Statisticts.setText(String.valueOf(
                (int)(Result_Calculator.passMarks)));
    }
}
