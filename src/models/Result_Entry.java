package models;
import java.io.Serializable;

public class Result_Entry implements Serializable {

    // Data Members Encapsulates
    private Course course;
    private double marksObtained = 0.0;

    // Setter Method
    public void setCourse(Course a) {
        this.course = a;
    }
    public void setMarksObtained(double b) {
        this.marksObtained = b;
    }

    // Getter Method
    public Course getCourse() {
        return this.course;
    }
    public double getMarksObtained() {
        return this.marksObtained;
    }

    // Argument constructor method
    public Result_Entry(Course a, double b) {
        this.course = a;
        this.marksObtained = b;
    }

    // Zero Argument constructor method
    public Result_Entry() {
        this.course = new Course();
        this.marksObtained = 0.0;
    }

    // Method to Calculate Grade Points
    public double getGradePoints() {
        if (marksObtained >= 85) {
            return 4.00;
        } else if (marksObtained >= 80) {
            return 3.66;
        } else if (marksObtained >= 75) {
            return 3.33;
        } else if (marksObtained >= 71) {
            return 3.00;
        } else if (marksObtained >= 68) {
            return 2.66;
        } else if (marksObtained >= 64) {
            return 2.33;
        } else if (marksObtained >= 61) {
            return 2.00;
        } else if (marksObtained >= 58) {
            return 1.66;
        } else if (marksObtained >= 54) {
            return 1.30;
        } else if (marksObtained >= 50) {
            return 1.00;
        } else {
            return 0.0;
        }
    }

    // Method to Calculate Grade
    public String getGrade() {
        if (marksObtained >= 85) {
            return "A";
        } else if (marksObtained >= 80) {
            return "A-";
        } else if (marksObtained >= 75) {
            return "B+";
        } else if (marksObtained >= 71) {
            return "B";
        } else if (marksObtained >= 68) {
            return "B-";
        } else if (marksObtained >= 64) {
            return "C+";
        } else if (marksObtained >= 61) {
            return "C";
        } else if (marksObtained >= 58) {
            return "C-";
        } else if (marksObtained >= 54) {
            return "D+";
        } else if (marksObtained >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    // toString Mehtod to Display Data Members (Extra method to complete class arcitecture)
    public String toString() {
        return "\n<===== \"Result Entry Details \" =====>" +
                "\n=> Course = \"" + this.course.toString() + "\"" +
                "\n=> Marks Obtained = \"" + this.marksObtained + "\"" +
                "\n<================================>\n";

    }
}