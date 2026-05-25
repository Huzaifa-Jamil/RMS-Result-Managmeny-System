package Student;
import Transcript.Transcript;

public class EngineeringStudent extends Student {

    // Data Members Encapsulates
    // No Data Members

    // Static Data Member
    static int totalEngineeringStudents = 0;

    // Setter Method
    public static void setTotalEngineeringStudents(int a) {
        totalEngineeringStudents = a;
    }

    // Getter Method
    public static int getTotalEngineeringStudents() {
        return totalEngineeringStudents;
    }

    // Argument constructor method With Authentaction
    public EngineeringStudent(String a, String b, String c, Transcript d) {
        super(a, b, c, d);
        totalEngineeringStudents++;
    }

    // Zero Argument constructor method
    public EngineeringStudent() {
        super();
        totalEngineeringStudents++;
    }

    // Method to Get Student Type
    public String getStudentType() {
        return "Engineering Student";
    }

    // Method to Calculate Total Marks
    public double calculateTotal() {
        return getTranscript().getTotalMarks();
    }

    // Abstract Method Implementations
    public double calculatePercentage() {
        int totalCourses = getTranscript().getResultEntry().size();
        if (totalCourses == 0) {
            return 0.0;
        }

        double totalPossibleMarks = totalCourses * 100.0;
        double obtainedMarks = getTranscript().getTotalMarks();

        return ((obtainedMarks / totalPossibleMarks) * 100.0);
    }

    // Method to Calculate Grade
    public String calculateGrade() {
        if (getTranscript().getResultEntry().isEmpty()) {
            return "N/A";
        }
        double GPA = getTranscript().getGPA();
        if (GPA >= 4.00) {
            return "A";
        } else if (GPA >= 3.66) {
            return "A-";
        } else if (GPA >= 3.33) {
            return "B+";
        } else if (GPA >= 3.00) {
            return "B";
        } else if (GPA >= 2.66) {
            return "B-";
        } else if (GPA >= 2.33) {
            return "C+";
        } else if (GPA >= 2.00) {
            return "C";
        } else if (GPA >= 1.66) {
            return "C-";
        } else if (GPA >= 1.30) {
            return "D+";
        } else if (GPA >= 1.00) {
            return "D";
        } else {
            return "F";
        }
    }
}