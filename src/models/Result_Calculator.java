package models;

public interface Result_Calculator {
    
    // Data Members Encapsulates
    static final double passMarks = 50;

    // Abstract Methods
    public double calculateTotal();
    public double calculatePercentage();
    public String calculateGrade();
}
