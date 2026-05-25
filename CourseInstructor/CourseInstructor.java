package CourseInstructor;
import java.io.Serializable;

public class CourseInstructor implements Serializable{
    
    // Data Members Encapsulates
    private String name = "";
    private String qualificaion = "";
    private String program = "";

    // Static Data Member
    static int totalCourseInstructor = 0;
    
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
    public static void setTotalCourseInstructor(int d) {
        totalCourseInstructor = d;
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
    public static int getTotalCourseInstructor() {
        return totalCourseInstructor;
    }

    // Argument constructor method
    public CourseInstructor(String a, String b, String c) {
        this.name = a;
        this.qualificaion = b; 
        this.program = c;
        totalCourseInstructor++;
    }

    // Zero Argument constructor method
    public CourseInstructor() {
        this.name = "";
        this.qualificaion = "";
        this.program = "";
        totalCourseInstructor++;
    }

    // toString Mehtod to Display Data Members (Extra method to complete class arcitecture)
    public String toString() {
        return "\n\033[33m<===== \"Course Instructor Details \" =====>\033[0m" +
               "\n\033[32m=> Name = \"" + name + "\"" +
               "\n=> Qualificaion = \"" + qualificaion + "\"" +
               "\n=> Program = \"" + program + "\"" +
               "\n<================================>\033[0m\n";

    }
}
