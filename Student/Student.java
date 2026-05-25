package Student;
import java.io.Serializable;
import Course.Course;
import ResultCalculator.ResultCalculator;
import ResultEntry.ResultEntry;
import Transcript.Transcript;

public abstract class Student implements Serializable, ResultCalculator {

    // Data Members Encapsulates
    private String studentID = "";
    private String name = "";
    private String program = "";
    private Transcript transcript;

    // Static Data Member
    static int totalStudents = 0;

    // Setter Method
    public void setStudentID(String a) {
        this.studentID = a;
    }
    public void setName(String b) {
        this.name = b;
    }
    public void setProgram(String c) {
        this.program = c;
    }
    public void setTranscript(Transcript d) {
        this.transcript = d;
    }
    public static void setTotalStudents(int e) {
        totalStudents = e;
    }

    // Getter Method
    public String getStudentID() {
        return this.studentID;
    }
    public String getName() {
        return this.name;
    }
    public String getProgram() {
        return this.program;
    }
    public Transcript getTranscript() {
        return this.transcript;
    }
    public static int getTotalStudents() {
        return totalStudents;
    }

    // Argument constructor method With Authentaction
    public Student(String a, String b, String c, Transcript d) {
        this.studentID = a;
        this.name = b;
        this.program = c;
        this.transcript = d;
        totalStudents++;
    }

    // Zero Argument constructor method
    public Student() {
        this.studentID = "";
        this.name = "";
        this.program = "";
        this.transcript = new Transcript();
    }

    // Method to Add Course and Marks to Transcript
    public void addCourse(Course course, double marks) {
        ResultEntry resultEntry = new ResultEntry(course, marks);
        transcript.addResultEntry(resultEntry);
    }

    // Implemented Methods from ResultCalculator Interface
    public double calculateGPA() {
        return transcript.getGPA();
    }

    // Method to Display Student Details (Extra method to complete class arcitecture)
    public void Dispaly() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Program: " + program);
        System.out.println("GPA: " + String.format("%.2f", calculateGPA()));
        System.out.println("Total Marks: " + calculateTotal());
        System.out.println("Percentage: " + String.format("%.2f", calculatePercentage()) + "%");
        System.out.println("Grade: " + calculateGrade());

        System.out.println("\nCourse Results:");
        for (int i = 0; i < transcript.getResultEntry().size(); i++) {
            System.out.println(transcript.getResultEntry().get(i).getCourse() + ": "
                    + transcript.getResultEntry().get(i).getMarksObtained() +
                    " (" + transcript.getResultEntry().get(i).getGrade() + ")");
        }
    }

    public String displayResults() {
        String result = "";
        for (int i = 0; i < transcript.getResultEntry().size(); i++) {
            result += transcript.getResultEntry().get(i).getCourse()
                    + ": "
                    + transcript.getResultEntry().get(i).getMarksObtained()
                    + transcript.getResultEntry().get(i).getGrade() + ", ";
        }
        return "Student ID: " + studentID + ", "
                + "Name: " + name + ", "
                + "Program: " + program + ", "
                + "GPA: " + String.format("%.2f", calculateGPA()) + ", "
                + "Total Marks: " + calculateTotal() + ", "
                + "Percentage: " + String.format("%.2f", calculatePercentage()) + "%" + ", "
                + "Grade: " + calculateGrade() + ", "
                + "[Course Results: "
                + result
                + "]";
    }

    // Abstract Method to Get Student Type
    public abstract String getStudentType();

    // Abstract Methods from ResultCalculator Interface
    public abstract double calculateTotal();

    // Abstract Methods from ResultCalculator Interface
    public abstract double calculatePercentage();

    // Abstract Methods from ResultCalculator Interface
    public abstract String calculateGrade();

}