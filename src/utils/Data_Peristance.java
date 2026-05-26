package utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import ui.*;
import models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Data_Peristance {

    public static void load_Data_From_DataBase() {
        try {
            String dataFolder = "data/Storage/";

            java.io.File dataDir = new java.io.File(dataFolder);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            Record_List<Course_Instructor> loaded_Instructors = RMS_Result_Management_System_UI.instructor_Store
                    .loadToRecordList(dataFolder + "instructors");
            if (loaded_Instructors != null) {
                RMS_Result_Management_System_UI.instructors = loaded_Instructors;

            }

            Record_List<Student> loaded_Students = RMS_Result_Management_System_UI.student_Store.loadToRecordList(dataFolder + "students");
            if (loaded_Students != null) {
                RMS_Result_Management_System_UI.students = loaded_Students;
            }

            Record_List<Course> loaded_Courses = RMS_Result_Management_System_UI.course_Store.loadToRecordList(dataFolder + "courses");
            if (loaded_Courses != null) {
                RMS_Result_Management_System_UI.courses = loaded_Courses;
            }
            Refresh_All.refresh();

            UI_Styles.showSideNotifications(RMS_Result_Management_System_UI.mainFrame, "Data Loaded Successfully");

        } catch (Exception e) {
            UI_Styles.showSideErrors(
                    RMS_Result_Management_System_UI.mainFrame, "Error Loading Data");
        }
    }


    public static void save_All_Data_To_DataBase() {
        try {
            String dataFolder = "data/Storage/";

            java.io.File dataDir = new java.io.File(dataFolder);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            RMS_Result_Management_System_UI.student_Store.saveToFile(dataFolder + "students", 
                    RMS_Result_Management_System_UI.students);
            RMS_Result_Management_System_UI.course_Store.saveToFile(dataFolder + "courses", 
                    RMS_Result_Management_System_UI.courses);
            RMS_Result_Management_System_UI.instructor_Store.saveToFile(dataFolder + "instructors", 
                    RMS_Result_Management_System_UI.instructors);

            UI_Styles.showSideNotifications(
                    RMS_Result_Management_System_UI.mainFrame, "Data Saved Successfully");

        } catch (Exception e) {
            UI_Styles.showSideErrors(
                    RMS_Result_Management_System_UI.mainFrame, "Error Saving Data");
        }
    }

    public static void save_Data_From_DataBase_to_Json() {
        try {
            String dataFolder = "data/Storage/";
            String path = "context.json";

            java.io.File dataDir = new java.io.File(dataFolder);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            Refresh_All.refresh();

            Record_List<Course_Instructor> instructors = RMS_Result_Management_System_UI.instructors;
            Record_List<Student> students = RMS_Result_Management_System_UI.students;
            Record_List<Course> courses = RMS_Result_Management_System_UI.courses;
            
            Context_Window_Echo_AI context = new Context_Window_Echo_AI(students, courses, instructors);

            Gson json = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

            try {
                FileWriter writer = new FileWriter(dataFolder + path, false);
                json.toJson(context, writer);
                // to write the final characters to json
                writer.flush();
            }
            catch (IOException e){
                 UI_Styles.showSideErrors(
                         RMS_Result_Management_System_UI.mainFrame, "Error saving data for Echo AI");
            }

        } catch (Exception e) {
            UI_Styles.showSideErrors(
                    RMS_Result_Management_System_UI.mainFrame, "Error saving data for Echo AI");
        }
    }
}
