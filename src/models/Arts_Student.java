package models;

public class Arts_Student extends Student {
    // Data Members Encapsulates
    // No Data Members

    // Setter Method
    
    // Getters Method
    
    // Argument constructor method With Authentaction
    public Arts_Student(String a, String b, String c, Transcript d) {
        super(a, b, c, d);
    }

    // Zero Argument constructor method
    public Arts_Student() {
        super();
    }

    // Method to Get Student Type
    public String getStudentType() {
        return "Arts Student";
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
