package Transcript;
import java.io.Serializable;
import java.util.ArrayList;
import ResultEntry.ResultEntry;

public class Transcript implements Serializable {

    // Data Members Encapsulates
    private ArrayList<ResultEntry> resultEntry;

    // Static Data Member
    static int totalTranscript = 0;

    // Argument constructor method
    public Transcript(ArrayList<ResultEntry> a) {
        this.resultEntry = a;
        totalTranscript++;
    }

    // Zero Argument constructor method
    public Transcript() {
        this.resultEntry = new ArrayList<>();
        totalTranscript++;
    }

    // Setter Method
    public void setResultEntry(ArrayList<ResultEntry> a) {
        this.resultEntry = a;
    }
    public static void setTotalTranscript(int b) {
        totalTranscript = b;
    }

    // Getter Method
    public ArrayList<ResultEntry> getResultEntry() {
        return this.resultEntry;
    }
    public static int getTotalTranscript() {
        return totalTranscript;
    }

    // Method to Calculate Total Marks
    public double getTotalMarks() {
        double total = 0.0;
        for (int i = 0; i < resultEntry.size(); i++) {
            total += resultEntry.get(i).getMarksObtained();
        }
        return total;
    }

    // Method to Calculate GPA
    public double getGPA() {

        if (resultEntry.size() == 0) {
            return 0.0;
        }

        double gradePointsOfEachCourse = 0.0;
        double creditHoursOfEachCourse = 0;
        double gradePointInToCreditHours = 0.0;
        double totalQualityPoints = 0.0;
        double totalCreditHours = 0.0;

        for (int i = 0; i < resultEntry.size(); i++) {
            gradePointsOfEachCourse = resultEntry.get(i).getGradePoints();
            creditHoursOfEachCourse = resultEntry.get(i).getCourse().getCreditHours();
            gradePointInToCreditHours = gradePointsOfEachCourse * creditHoursOfEachCourse;
            totalQualityPoints += gradePointInToCreditHours;
            totalCreditHours += creditHoursOfEachCourse;

        }
        return totalQualityPoints / totalCreditHours;
    }

    // Method to Add Result Entry
    public void addResultEntry(ResultEntry a) {
        resultEntry.add(a);
    }

    // Method to Calculate Total Credit Hours
    public int TotalCreditHours() {
        int total = 0;
        for (int i = 0; i < resultEntry.size(); i++) {
            total += resultEntry.get(i).getCourse().getCreditHours();
        }
        return total;
    }

    // toString Mehtod to Display Data Members (extra method for completing class arcitecture)
    public String toString() {
        return "\n<===== \"Transcript Details\" =====>" +
                "\n=> Result Entries = \"" + resultEntry.toString() + "\"" +
                "\n<================================>\n";
    }
}
