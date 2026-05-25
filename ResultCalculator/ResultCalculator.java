package ResultCalculator;

public interface ResultCalculator {
    
    // Data Members Encapsulates
    static final double passMarks = 50;

    // Abstract Methods
    public double calculateTotal();
    public double calculatePercentage();
    public String calculateGrade();
}
