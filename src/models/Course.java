package models;
import java.io.Serializable;

public class Course implements Serializable {
    
    // Data Members Encapsulates
    private String title = "";
    private String courseCode = "";
    private double creditHours = 0.0;
    private Course_Instructor courseInstructor;


    // Setter Method
    public void setTitle(String a) {
        this.title = a;
    }
    public void setCourseCode(String b) {
        this.courseCode = b;
    }
    public void setCreditHours(double c) {
        this.creditHours = c;
    }
    public void setCourseInstructor(Course_Instructor d) {
        this.courseInstructor = d;
    }

    // Getter Method
    public String getTitle() {
        return this.title;
    }
    public String getCourseCode() {
        return this.courseCode;
    }
    public double getCreditHours() {
        return this.creditHours;
    }
    public Course_Instructor getCourseInstructor() {
        return this.courseInstructor;
    }

    // Argument constructor method
    public Course(String a, String b, double c, Course_Instructor d) {
        this.title = a;
        this.courseCode = b; 
        this.creditHours = c;
        this.courseInstructor = d;
    }

    // Zero Argument constructor method
    public Course() {
        this.title = "";
        this.courseCode = "";
        this.creditHours = 0.0;
        this.courseInstructor = new Course_Instructor();
    }

    // Method to Display Course Details (Extra Method to Complete class Arcitecture)
    public void displayCourseDetails() {
        System.out.println("Title: " + title);
        System.out.println("Course Code: " + courseCode);
        System.out.println("Credit Hours: " + creditHours);
        System.out.println("Instructor: " + courseInstructor);
    }

    // toString Mehtod to Display Data Members (Extr a Method to Complete class Arcitecture)
    public String toString() {
        return "\n<===== \"Course Details \" =====>" +
                "\n=> Title = \"" + this.title + "\"" +
                "\n=> Course Code = \"" + this.courseCode + "\"" +
                "\n=> Credit Hours = \"" + this.creditHours + "\"" +
                "\n=> Course Instructor = \"" + this.courseInstructor.toString() + "\"" +
                "\n<================================>\n";
    }
}
