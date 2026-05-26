package models;
import java.io.Serializable;
import java.util.ArrayList;

public class Transcript implements Serializable {

    // Data Members Encapsulates
    private ArrayList<Result_Entry> resultEntry;

    // Argument constructor method
    public Transcript(ArrayList<Result_Entry> a) {
        this.resultEntry = a;
    }

    // Zero Argument constructor method
    public Transcript() {
        this.resultEntry = new ArrayList<>();
    }

    // Setter Method
    public void setResultEntry(ArrayList<Result_Entry> a) {
        this.resultEntry = a;
    }

    // Getter Method
    public ArrayList<Result_Entry> getResultEntry() {
        return this.resultEntry;
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

        if (totalCreditHours == 0.0) {
            return 0.0;
        }
        return totalQualityPoints / totalCreditHours;
    }

    // Method to Add Result Entry
    public void addResultEntry(Result_Entry a) {
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
