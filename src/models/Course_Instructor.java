package models;

import java.io.Serializable;
import ui.RMS_Result_Management_System_UI;

public class Course_Instructor implements Serializable{
    
    // Data Members Encapsulates
    private String name = "";
    private String qualificaion = "";
    private String program = "";
    private int courseTaught = 0;
    
    // Setter Method
    public void setName(String a) {
        this.name = a;
    }
    public void setQualificaion(String b) {
       this.qualificaion = b;
    }
    public void setProgram(String c) {
        this.program = c;
    }
    public void setCoursesTaught(int coursesTaught) {
        this.courseTaught = coursesTaught;
    }

    // Getter Method
    public String getName() {
        return this.name;  
    }
    public String getQualificaion() {
        return this.qualificaion;
    }
    public String getProgram() {
        return this.program;
    }
    public int getCoursesTaught() {
        int count = 0;
        for (int i = 0; i < RMS_Result_Management_System_UI.courses.size(); i++) {
            Course course = RMS_Result_Management_System_UI.courses.get(i);
            if (course.getCourseInstructor() != null &&
                    course.getCourseInstructor().getName().equals(this.name)) {
                count++;
            }
        }
        this.courseTaught = count;
        return this.courseTaught;
    }

    // Argument constructor method
    public Course_Instructor(String a, String b, String c, int d) {
        this.name = a;
        this.qualificaion = b;
        this.program = c;
        this.courseTaught = d;
    }

    // Zero Argument constructor method
    public Course_Instructor() {
        this.name = "";
        this.qualificaion = "";
        this.program = "";
        this.courseTaught = 0;
    }
    
    public String toString() {
        return "\n\033[33m<===== \"Course Instructor Details \" =====>\033[0m" +
               "\n\033[32m=> Name = \"" + name + "\"" +
               "\n=> Qualificaion = \"" + qualificaion + "\"" +
               "\n=> Program = \"" + program + "\"" +
               "\n<================================>\033[0m\n";

    }
}
