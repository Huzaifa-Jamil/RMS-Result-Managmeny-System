package utils;
import models.*;
import ui.RMS_Result_Management_System_UI;

public class Html_Files {

    public static String showAboutSystem() {
        String aboutInformation = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f8fafc; margin: 20px; color: #2d3748; }"+
                ".header { text-align: center; margin-bottom: 30px; padding-bottom: 20px; border-bottom: 2px solid #e2e8f0; }"+
                ".header h1 { color: #4a5568; font-size: 28px; margin: 0; }" +
                ".header p { color: #718096; font-size: 14px; margin-top: 5px; }" +
                ".section { margin-bottom: 30px; background: white; padding: 20px; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.05); }"+
                ".section-title { color: #4a5568; font-size: 20px; font-weight: bold; margin-bottom: 15px; padding-bottom: 10px; border-bottom: 1px solid #e2e8f0; }"+
                ".feature-item { display: flex; align-items: flex-start; margin-bottom: 15px; padding: 10px; border-radius: 6px; transition: background-color 0.2s; }"+
                ".feature-item:hover { background-color: #f7fafc; }" +
                ".feature-icon { font-size: 18px; margin-right: 12px; min-width: 24px; }" +
                ".feature-text { flex: 1; }" +
                ".feature-title { font-weight: 600; color: #2d3748; margin-bottom: 3px; }" +
                ".feature-desc { color: #718096; font-size: 13px; }" +
                ".tech-stack { display: flex; flex-wrap: wrap; gap: 10px; margin-top: 20px; }" +
                ".tech-item { background: #e2e8f0; padding: 8px 15px; border-radius: 20px; font-size: 12px; font-weight: 500; color: #4a5568; }"+
                ".team-member { background: #edf2f7; padding: 15px; border-radius: 8px; margin-bottom: 10px; }" +
                ".member-name { font-weight: 600; color: #2d3748; margin-bottom: 5px; }" +
                ".member-role { color: #718096; font-size: 13px; }" +
                ".version { background: #d7e1f0; padding: 12px; border-radius: 8px; text-align: center; margin-top: 20px; }"+
                ".version-text { font-weight: 500; color: #2448e6; }" +
                ".highlight { color: #4a5568; font-weight: 600; }" +
                ".ai-badge { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 8px 15px; border-radius: 20px; font-size: 12px; font-weight: 600; display: inline-block; margin-left: 10px; }"+
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='header'>" +
                "<h1>📚 RMS - Result Management System</h1>" +
                "<p>✦ Academic Excellence, Simplified & Automated</p>" +
                "</div>" +
                "<div class='section'>" +
                "<div class='section-title'>🌟 Core Modules</div>" +
                "<div class='feature-item'>" +
                "<div class='feature-icon'>👨‍🎓</div>" +
                "<div class='feature-text'>" +
                "<div class='feature-title'>Student Management</div>" +
                "<div class='feature-desc'>Comprehensive student records management for students with full CRUD operations</div>"+
                "</div></div>" +
                "<div class='feature-item'>" +
                "<div class='feature-icon'>📚</div>" +
                "<div class='feature-text'>" +
                "<div class='feature-title'>Course Management</div>" +
                "<div class='feature-desc'>Complete course catalogue with credit hours and lot more</div>"+
                "</div></div>" +
                "<div class='feature-item'>" +
                "<div class='feature-icon'>👨‍🏫</div>" +
                "<div class='feature-text'>" +
                "<div class='feature-title'>Faculty Management</div>" +
                "<div class='feature-desc'>Assign faculty members to courses and manage instructor profiles with qualification tracking</div>"+
                "</div></div>" +
                "<div class='feature-item'>" +
                "<div class='feature-icon'>📊</div>" +
                "<div class='feature-text'>" +
                "<div class='feature-title'>Results Management</div>" +
                "<div class='feature-desc'>Result processing, grade calculation, transcript generation, and academic standing determination</div>"+
                "</div></div>" +
                "<div class='feature-item'>" +
                "<div class='feature-icon'>🎯</div>" +
                "<div class='feature-text'>" +
                "<div class='feature-title'>Grading System</div>" +
                "<div class='feature-desc'>Comprehensive grading with GPA calculation and grade point conversion</div>"+
                "</div></div>" +
                "<div class='feature-item'>" +
                "<div class='feature-icon'>💾</div>" +
                "<div class='feature-text'>" +
                "<div class='feature-title'>Data Management</div>" +
                "<div class='feature-desc'>Data persistence with automatic backup, and comprehensive refresh tools</div>"+
                "</div></div>" +
                "</div>" +
                "<div class='section'>" +
                "<div class='section-title'>✨ Key Features</div>" +
                "<div class='feature-item'>" +
                "<div class='feature-icon'>⚡</div>" +
                "<div class='feature-text'>" +
                "<div class='feature-title'>KeyBoard ShortCuts</div>" +
                "<div class='feature-desc'>Load Data, Save Data, Refresh and Overview About system using shortcuts</div>"+
                "</div></div>" +
                "<div class='feature-item'>" +
                "<div class='feature-icon'>📈</div>" +
                "<div class='feature-text'>" +
                "<div class='feature-title'>Real-time Analytics</div>" +
                "<div class='feature-desc'>Refresh GPA and other statistics instantly</div>"+
                "</div></div>" +
                "<div class='feature-item'>" +
                "<div class='feature-icon'>🔒</div>" +
                "<div class='feature-text'>" +
                "<div class='feature-title'>Data Integrity</div>" +
                "<div class='feature-desc'>Validation rules and duplicate prevention</div>"+
                "</div></div>" +
                "<div class='feature-item'>" +
                "<div class='feature-icon'>🔄</div>" +
                "<div class='feature-text'>" +
                "<div class='feature-title'>Gmail Integration</div>" +
                "<div class='feature-desc'>Feature of Gmailing HTML reports is also present</div>"+
                "</div></div>" +
                "</div>" +
                "<div class='section'>" +
                "<div class='section-title'>🛠️ Technical Stack</div>" +
                "<div class='tech-stack'>" +
                "<div class='tech-item'>Java Swing UI</div>" +
                "<div class='tech-item'>Object-Oriented Design</div>" +
                "<div class='tech-item'>File I/O Operations</div>" +
                "<div class='tech-item'>HTML/CSS Styling (Dialogue Boxes)</div>" +
                "<div class='tech-item'>Exception Handling</div>" +
                "</div>" +
                "</div>" +
                "<div class='section'>" +
                "<div class='section-title'>👥 Development Team</div>" +
                "<div class='team-member'>" +
                        "<div class='member-name'>"  +
                        "M. Huzaifa Jamil"+
                        "</div>" +
                        "<div class='member-role'>Lead Developer & System Architect</div>" +
                        "<div class='member-link'>" +
                        "<br> <a href='mailto:huzaifajamil.ke24@gmail.com'>Email: huzaifajamil.ke24@gmail.com</a> <br>"
                        +
                        "<a href='https://www.linkedin.com/in/huzaifa-jamil-in'>LinkedIn: https://www.linkedin.com/in/huzaifa-jamil-in</a>"
                        +
                        "</div>" +
                "</div>" +
                "<div class='team-member'>"+
                "<div class='member-name'>Academic Development</div>" +
                "<div class='member-role'>As a Semester Project of Object Oriented Programming</div>" +
                "</div>" +
                "</div>" +
                "<div class='version'>" +
                "<div class='version-text'>Version 2.0 | © 2026 RMS - Result Management System | All Rights Reserved</div>"+
                "</div>" +
                "</body>" +
                "</html>";
                return aboutInformation;
    }

    public static String getStudentDetails(Student student) {

        String currentDate = new java.text.SimpleDateFormat("dd MMMM yyyy, hh:mm a").format(new java.util.Date());
        Transcript transcript = student.getTranscript();
        int totalSubjects = (transcript != null && transcript.getResultEntry() != null)
                ? transcript.getResultEntry().size()
                : 0;
        double totalMarks = student.calculateTotal();
        String studentDetails = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f0f2f5; margin: 0; padding: 30px; color: #1a202c; }"+
                ".container { max-width: 800px; margin: 0 auto; background: white; border-radius: 20px; box-shadow: 0 12px 30px rgba(0,0,0,0.08); overflow: hidden; }"+
                ".header { background: linear-gradient(135deg, #1e3a8a 0%, #3b82f6 100%); padding: 30px 20px 15px 20px; " +
                "text-align: center; color: #2d3748; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }" +
                ".header h1 { margin: 0; font-size: 28px; letter-spacing: 2px; text-transform: uppercase; font-weight: 800; }"+
                ".header p { margin: 10px 0 0 0; font-size: 14px; opacity: 0.8; font-style: italic; }" +
                ".card { padding: 10px, 30px, 30px, 30px ; }" +
                ".section-title { color: #1e3a8a; font-size: 16px; font-weight: 700; margin-top: 30px; margin-bottom: 20px; "+
                "padding-bottom: 8px; border-bottom: 3px solid #e2e8f0; text-transform: uppercase; }" +
                ".details-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 20px; }" +
                ".detail-item { padding: 5px 10px; }" +
                ".detail-label { font-size: 13px; color: #718096; font-weight: 600; text-transform: uppercase; margin-bottom: 4px; }"+
                ".detail-value { font-size: 16px; color: #2d3748; font-weight: 600; }" +
                ".highlight-box { background: #f8fafc; padding: 25px; border-radius: 15px; border-left: 5px solid #3b82f6; margin: 20px 0; }"+
                ".highlight-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 15px; text-align: center; }"+
                ".highlight-value { font-size: 32px; font-weight: 800; color: #1e3a8a; }" +
                ".courses-table { width: 100%; border-collapse: separate; border-spacing: 0 8px; margin-top: 10px; }" +
                ".courses-table th { color: #718096; font-size: 12px; text-transform: uppercase; padding: 12px; text-align: left; }"+
                ".courses-table tr { background-color: #f8fafc; }" +
                ".courses-table td { padding: 15px; border-top: 1px solid #f1f5f9; border-bottom: 1px solid #f1f5f9; }"+
                ".courses-table td:first-child { border-left: 1px solid #f1f5f9; border-radius: 10px 0 0 10px; color:#3b82f6; font-weight:bold; }"+
                ".courses-table td:last-child { border-right: 1px solid #f1f5f9; border-radius: 0 10px 10px 0; font-weight: 700; }"+
                ".version { background: #d7e1f0; padding: 12px; border-radius: 8px; text-align: center; margin-top: 20px; }" +
                ".version-text { font-weight: bold; color: #4865e7; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<div class='header'>" +
                "<h1>STUDENT ACADEMIC RECORD</h1>" +
                "<p>Official Academic Details & Performance Summary</p>" +
                "</div>" +
                "<div class='card'>" +
                "<div class='section-title'>Student Information</div>" +
                "<div class='details-grid'>" +
                "<div class='detail-item'><div class='detail-label'>Student ID</div><div class='detail-value'>"+
                student.getStudentID() + "</div></div>" +
                "<div class='detail-item'><div class='detail-label'>Full Name</div><div class='detail-value'>"+
                student.getName() + "</div></div>" +
                "<div class='detail-item'><div class='detail-label'>Program</div><div class='detail-value'>"+
                student.getProgram() + "</div></div>" +
                "<div class='detail-item'><div class='detail-label'>Type</div><div class='detail-value'>"+ 
                student.getStudentType() + "</div></div>" +
                "</div>" +
                "<div class='section-title'>Academic Performance</div>" +
                "<div class='highlight-box'>" +
                "<div class='highlight-grid'>" +
                "<div><div class='detail-label'>Cumulative GPA</div><div class='highlight-value'>"+
                String.format("%.2f", student.calculateGPA()) + "</div></div>" +
                "<div><div class='detail-label'>Overall Grade</div><div class='highlight-value'>"+
                student.calculateGrade() + "</div></div>" +
                "</div>" +
                "</div>" +
                "<div class='section-title'>Details</div>" +
                "<div class='details-grid'>" +
                "<div class='detail-item'><div class='detail-label'>Total Marks</div><div class='detail-value'>"+
                (totalMarks == 0 ? "N/A" : String.format("%.0f", totalMarks) + " / " + (totalSubjects * 100))+
                "</div></div>" +
                "<div class='detail-item'><div class='detail-label'>Percentage</div><div class='detail-value'>"+
                (totalMarks == 0 ? "N/A" : String.format("%.2f", student.calculatePercentage()) + "%")+
                "</div></div>" +
                "<div class='detail-item'><div class='detail-label'>Credit Hours</div><div class='detail-value'>"+
                (totalMarks == 0 ? "N/A" : student.getTranscript().TotalCreditHours()) + "</div></div>" +
                "<div class='detail-item'><div class='detail-label'>Status</div><div class='detail-value'>"+
                (totalMarks == 0 ? "N/A" : (student.calculateGPA() >= 2.0 ? "PASSING" : "PROBATION")) + "</div></div>"+
                "</div>" +
                "<div class='section-title'>Courses</div>";
                if (transcript != null && transcript.getResultEntry() != null && !transcript.getResultEntry().isEmpty()) {
                        studentDetails += "<table class='courses-table'><thead><tr><th>Code</th><th>Course Title</th><th>Score</th><th>Grade</th></tr></thead><tbody>";
                        for (Result_Entry entry : transcript.getResultEntry()) {
                                studentDetails += "<tr>" +
                                                "<td>" + entry.getCourse().getCourseCode() + "</td>" +
                                                "<td>" + entry.getCourse().getTitle() + "</td>" +
                                                "<td>" + entry.getMarksObtained() + "</td>" +
                                                "<td>" + entry.getGrade() + "</td>" +
                                                "</tr>";
                        }
                        studentDetails += "</tbody></table>";
                } else {
                        studentDetails += "<div style='text-align:center; padding:20px; color:#94a3b8;'>No course results available</div>";
                }
                studentDetails += "</div>" +
                "<div class='version'>" +
                "<div class='version-text'>" +
                "<strong>Issued on " + currentDate + " | System Verified Record<br>" +
                "Version 2.0 | © 2026 RMS - Result Management System | All Rights Reserved </strong>"+
                "</div>" +
                "</div></body></html>";
                return studentDetails;
        }

        public static String getCourseDetails(Course course) {
                String currentDate = new java.text.SimpleDateFormat("dd MMMM yyyy, hh:mm a")
                                .format(new java.util.Date());

                String instructorName = (course.getCourseInstructor() != null &&
                                course.getCourseInstructor().getName() != null) ? course.getCourseInstructor().getName()
                                                : "Not Assigned";
                String instructorQualification = (course.getCourseInstructor() != null &&
                                course.getCourseInstructor().getName() != null)
                                                ? course.getCourseInstructor().getQualificaion()
                                                : "N/A";
                String instructorProgram = (course.getCourseInstructor() != null &&
                                course.getCourseInstructor().getName() != null)
                                                ? course.getCourseInstructor().getProgram()
                                                : "N/A";

                int totalStudents = 0;
                for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
                        Student student = RMS_Result_Management_System_UI.students.get(i);
                        if (student.getTranscript() != null && student.getTranscript().getResultEntry() != null) {
                                for (int j = 0; j < student.getTranscript().getResultEntry().size(); j++) {
                                        Result_Entry entry = student.getTranscript().getResultEntry().get(j);
                                        if (entry.getCourse().getCourseCode().equals(course.getCourseCode())) {
                                                totalStudents++;
                                                break;
                                        }
                                }
                        }
                }

                String courseDetails = "<html>" +
                                "<head>" +
                                "<style>" +
                                "body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f0f2f5; margin: 0; padding: 30px; color: #1a202c; }"
                                +
                                ".container { max-width: 800px; margin: 0 auto; background: white; border-radius: 20px; box-shadow: 0 12px 30px rgba(0,0,0,0.08); overflow: hidden; }"
                                +
                                ".header { background: linear-gradient(135deg, #1e3a8a 0%, #3b82f6 100%); padding: 30px 20px 15px 20px; text-align: center; color: black; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }"
                                +
                                ".header h1 { margin: 0; font-size: 28px; letter-spacing: 2px; text-transform: uppercase; font-weight: 800; }"
                                +
                                ".header p { margin: 10px 0 0 0; font-size: 14px; opacity: 0.8; font-style: italic; }" +
                                ".card { padding: 10px 30px 30px 30px; }" +
                                ".section-title { color: #1e3a8a; font-size: 16px; font-weight: 700; margin-top: 30px; margin-bottom: 20px; padding-bottom: 8px; border-bottom: 3px solid #e2e8f0; text-transform: uppercase; }"
                                +
                                ".details-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 20px; }" +
                                ".detail-item { padding: 5px 10px; }" +
                                ".detail-label { font-size: 13px; color: #718096; font-weight: 600; text-transform: uppercase; margin-bottom: 4px; }"
                                +
                                ".detail-value { font-size: 16px; color: #2d3748; font-weight: 600; }" +
                                ".highlight-box { background: #f8fafc; padding: 25px; border-radius: 15px; border-left: 5px solid #3b82f6; margin: 20px 0; }"
                                +
                                ".highlight-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 15px; text-align: center; }"
                                +
                                ".highlight-value { font-size: 32px; font-weight: 800; color: #1e3a8a; }" +
                                ".students-table { width: 100%; border-collapse: separate; border-spacing: 0 8px; margin-top: 10px; }"
                                +
                                ".students-table th { color: #718096; font-size: 12px; text-transform: uppercase; padding: 12px; text-align: left; }"
                                +
                                ".students-table tr { background-color: #f8fafc; }" +
                                ".students-table td { padding: 15px; border-top: 1px solid #f1f5f9; border-bottom: 1px solid #f1f5f9; }"
                                +
                                ".students-table td:first-child { border-left: 1px solid #f1f5f9; border-radius: 10px 0 0 10px; color:#3b82f6; font-weight:bold; }"
                                +
                                ".students-table td:last-child { border-right: 1px solid #f1f5f9; border-radius: 0 10px 10px 0; font-weight: 700; }"
                                +
                                ".version { background: #d7e1f0; padding: 12px; border-radius: 8px; text-align: center; margin-top: 20px; }"
                                +
                                ".version-text { font-weight: bold; color: #4865e7; }" +
                                "</style>" +
                                "</head>" +
                                "<body>" +
                                "<div class='container'>" +
                                "<div class='header'>" +
                                "<h1>COURSE ACADEMIC RECORD</h1>" +
                                "<p>Official Course Details & Performance Summary</p>" +
                                "</div>" +
                                "<div class='card'>" +

                                "<div class='section-title'>Course Information</div>" +
                                "<div class='details-grid'>" +
                                "<div class='detail-item'><div class='detail-label'>Course Code</div><div class='detail-value'>"
                                + esc(course.getCourseCode()) + "</div></div>" +
                                "<div class='detail-item'><div class='detail-label'>Course Title</div><div class='detail-value'>"
                                + esc(course.getTitle()) + "</div></div>" +
                                "<div class='detail-item'><div class='detail-label'>Credit Hours</div><div class='detail-value'>"
                                + course.getCreditHours() + "</div></div>" +
                                "<div class='detail-item'><div class='detail-label'>Status</div><div class='detail-value'>"
                                + (course.getCourseInstructor() != null ? "ACTIVE" : "PENDING") + "</div></div>" +
                                "</div>" +

                                "<div class='section-title'>Instructor Information</div>" +
                                "<div class='highlight-box'>" +
                                "<div class='highlight-grid'>" +
                                "<div><div class='detail-label'>Instructor Name</div><div class='highlight-value' style='font-size:20px;'>"
                                + esc(instructorName) + "</div></div>" +
                                "<div><div class='detail-label'>Total Students</div><div class='highlight-value'>"
                                + totalStudents + "</div></div>" +
                                "</div>" +
                                "</div>" +

                                "<div class='section-title'>Instructor Details</div>" +
                                "<div class='details-grid'>" +
                                "<div class='detail-item'><div class='detail-label'>Qualification</div><div class='detail-value'>"
                                + esc(instructorQualification) + "</div></div>" +
                                "</div>" +

                                "<div class='section-title'>Enrolled Students</div>";

                boolean hasStudents = false;
                String tableRows = "";
                for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
                        Student student = RMS_Result_Management_System_UI.students.get(i);
                        if (student.getTranscript() != null && student.getTranscript().getResultEntry() != null) {
                                for (int j = 0; j < student.getTranscript().getResultEntry().size(); j++) {
                                        Result_Entry entry = student.getTranscript().getResultEntry().get(j);
                                        if (entry.getCourse().getCourseCode().equals(course.getCourseCode())) {
                                                hasStudents = true;
                                                tableRows += "<tr>" +
                                                                "<td>" + esc(student.getStudentID()) + "</td>" +
                                                                "<td>" + esc(student.getName()) + "</td>" +
                                                                "<td>" + entry.getMarksObtained() + "</td>" +
                                                                "<td>" + entry.getGrade() + "</td>" +
                                                                "</tr>";
                                                break;
                                        }
                                }
                        }
                }

                if (hasStudents) {
                        courseDetails += "<table class='students-table'><thead><tr><th>Student ID</th><th>Name</th><th>Score</th><th>Grade</th></tr></thead><tbody>"
                                        +
                                        tableRows + "</tbody></table>";
                } else {
                        courseDetails += "<div style='text-align:center; padding:20px; color:#94a3b8;'>No students enrolled in this course</div>";
                }

                courseDetails += "</div>" +
                                "<div class='version'>" +
                                "<div class='version-text'>" +
                                "<strong>Issued on " + currentDate + " | System Verified Record<br>" +
                                "Version 2.0 | © 2026 RMS - Result Management System | All Rights Reserved</strong>"
                                +
                                "</div>" +
                                "</div></body></html>";

                return courseDetails;
        }

        public static String getInstructorDetails(Course_Instructor instructor) {
                
                String currentDate = new java.text.SimpleDateFormat("dd MMMM yyyy, hh:mm a").format(new java.util.Date());
                String instructorDetails = "<html>" +
                        "<head>" +
                        "<style>" +
                        "body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f0f2f5; margin: 0; padding: 30px; color: #1a202c; }"+
                        ".container { max-width: 800px; margin: 0 auto; background: white; border-radius: 20px; box-shadow: 0 12px 30px rgba(0,0,0,0.08); overflow: hidden; }"+
                        ".header { background: linear-gradient(135deg, #1e3a8a 0%, #3b82f6 100%); padding: 30px 20px 15px 20px; " +
                        "text-align: center; color: #2d3748; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }" +
                        ".header h1 { margin: 0; font-size: 28px; letter-spacing: 2px; text-transform: uppercase; font-weight: 800; }"+
                        ".header p { margin: 10px 0 0 0; font-size: 14px; opacity: 0.8; font-style: italic; }" +
                        ".card { padding: 10px, 30px, 30px, 30px ; }" +
                        ".section-title { color: #1e3a8a; font-size: 16px; font-weight: 700; margin-top: 30px; margin-bottom: 20px; " +
                        "padding-bottom: 8px; border-bottom: 3px solid #e2e8f0; text-transform: uppercase; }" +
                        ".details-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 20px; }" +
                        ".detail-item { padding: 5px 10px; }" +
                        ".detail-label { font-size: 13px; color: #718096; font-weight: 600; text-transform: uppercase; margin-bottom: 4px; }"+
                        ".detail-value { font-size: 16px; color: #2d3748; font-weight: 600; }" +
                        ".highlight-box { background: #f8fafc; padding: 25px; border-radius: 15px; border-left: 5px solid #3b82f6; margin: 20px 0; }"+
                        ".highlight-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 15px; text-align: center; }"+
                        ".highlight-value { font-size: 32px; font-weight: 800; color: #1e3a8a; }" +
                        ".version { background: #d7e1f0; padding: 12px; border-radius: 8px; text-align: center; margin-top: 20px; }"
                        +
                        ".version-text { font-weight: bold; color: #4865e7; }" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class='container'>" +
                        "<div class='header'>" +
                        "<h1>INSTRUCTOR RECORD</h1>" +
                        "<p>Official Details Of Course Instructors</p>" +
                        "</div>" +
                        "<div class='card'>" +
                        "<div class='section-title'> Instructor Information</div>" +
                        "<div class='details-grid'>" +
                        "<div class='detail-item'><div class='detail-label'>Name</div><div class='detail-value'>"
                        + instructor.getName() + "</div></div>" +
                        "<div class='detail-item'><div class='detail-label'>Qualification</div><div class='detail-value'>"
                        + instructor.getQualificaion() + "</div></div>" +
                        "<div class='detail-item'><div class='detail-label'>Program</div><div class='detail-value'>"
                        + instructor.getProgram()+ "</div></div>" +
                        "</div>" +
                        "<div class='section-title'>Courses Information</div>" +
                        "<div class='highlight-box'>" +
                        "<div class='highlight-grid'>" +
                        "<div><div class='detail-label'>Course Teaching</div><div class='highlight-value'>"
                        +  instructor.getCoursesTaught() + "</div></div>" +
                        "</div>" +
                        "</div>" +
                        "<div class='section-title'>Details</div>" +
                        "<div class='details-grid'>" +
                        "<div class='detail-item'><div class='detail-label'>Status</div><div class='detail-value'>"
                        + (instructor.getCoursesTaught() != 0 ? "ACTIVE (Course Assigned)" : "NO COURSE ASSIGNED YET")                                
                        + "</div></div>" +
                        "</div>" +
                        "</div>" +
                        "<div class='version'>" +
                        "<div class='version-text'>"+
                        "<strong>Issued on " + currentDate + " | System Verified Record<br>" +
                        "Version 2.0 | © 2026 RMS - Result Management System | Powered by Echo AI | All Rights Reserved </strong>" +
                        "</div>" +
                        "</div></body></html>";
                return instructorDetails;
        }

        public static String getStatisticsDetails() {
                String currentDate = new java.text.SimpleDateFormat("dd MMMM yyyy, hh:mm a")
                                .format(new java.util.Date());

                int totalStudents = RMS_Result_Management_System_UI.students.size();
                int totalCourses = RMS_Result_Management_System_UI.courses.size();
                int totalInstructors = RMS_Result_Management_System_UI.instructors.size();
                int totalResults = 0;
                int artsStudents = 0;
                int scienceStudents = 0;
                int engStudents = 0;
                int withResults = 0;
                int withoutResults = 0;

                for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
                        Student s = RMS_Result_Management_System_UI.students.get(i);
                        String type = s.getStudentType().toLowerCase();
                        if (type.contains("arts"))
                                artsStudents++;
                        else if (type.contains("science"))
                                scienceStudents++;
                        else if (type.contains("engineering"))
                                engStudents++;

                        boolean hasResults = s.getTranscript() != null &&
                                        s.getTranscript().getResultEntry() != null &&
                                        !s.getTranscript().getResultEntry().isEmpty();
                        if (hasResults) {
                                withResults++;
                                totalResults += s.getTranscript().getResultEntry().size();
                        } else {
                                withoutResults++;
                        }
                }

                int instructorsWithCourses = 0;
                int instructorsWithoutCourses = 0;
                for (int i = 0; i < RMS_Result_Management_System_UI.instructors.size(); i++) {
                        Course_Instructor inst = RMS_Result_Management_System_UI.instructors.get(i);
                        boolean hasCourse = false;
                        for (int j = 0; j < RMS_Result_Management_System_UI.courses.size(); j++) {
                                Course c = RMS_Result_Management_System_UI.courses.get(j);
                                if (c.getCourseInstructor() != null &&
                                                c.getCourseInstructor().getName().equals(inst.getName())) {
                                        hasCourse = true;
                                        break;
                                }
                        }
                        if (hasCourse)
                                instructorsWithCourses++;
                        else
                                instructorsWithoutCourses++;
                }

                String studentRows = "";
                for (int i = 0; i < RMS_Result_Management_System_UI.students.size(); i++) {
                        Student s = RMS_Result_Management_System_UI.students.get(i);
                        boolean hasResults = s.getTranscript() != null &&
                                        s.getTranscript().getResultEntry() != null &&
                                        !s.getTranscript().getResultEntry().isEmpty();
                        studentRows += "<tr>" +
                                        "<td>" + esc(s.getStudentID()) + "</td>" +
                                        "<td>" + esc(s.getName()) + "</td>" +
                                        "<td>" + esc(s.getStudentType()) + "</td>" +
                                        "<td>" + String.format("%.2f", s.calculateGPA()) + "</td>" +
                                        "<td>" + (hasResults ? "YES" : "NO") + "</td>" +
                                        "</tr>";
                }

                String statsDetails = "<html>" +
                                "<head>" +
                                "<style>" +
                                "body { font-family: 'Segoe UI', Tahoma, sans-serif; background-color: #f0f2f5; margin: 0; padding: 30px; color: #1a202c; }"
                                +
                                ".container { max-width: 800px; margin: 0 auto; background: white; border-radius: 20px; box-shadow: 0 12px 30px rgba(0,0,0,0.08); overflow: hidden; }"
                                +
                                ".header { background: linear-gradient(135deg, #1e3a8a 0%, #3b82f6 100%); padding: 30px 20px 15px 20px; text-align: center; color: black; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }"
                                +
                                ".header h1 { margin: 0; font-size: 28px; letter-spacing: 2px; text-transform: uppercase; font-weight: 800; }"
                                +
                                ".header p { margin: 10px 0 0 0; font-size: 14px; opacity: 0.8; font-style: italic; }" +
                                ".card { padding: 10px 30px 30px 30px; }" +
                                ".section-title { color: #1e3a8a; font-size: 16px; font-weight: 700; margin-top: 30px; margin-bottom: 20px; padding-bottom: 8px; border-bottom: 3px solid #e2e8f0; text-transform: uppercase; }"
                                +
                                ".details-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 20px; }" +
                                ".detail-item { padding: 5px 10px; }" +
                                ".detail-label { font-size: 13px; color: #718096; font-weight: 600; text-transform: uppercase; margin-bottom: 4px; }"
                                +
                                ".detail-value { font-size: 16px; color: #2d3748; font-weight: 600; }" +
                                ".highlight-box { background: #f8fafc; padding: 25px; border-radius: 15px; border-left: 5px solid #3b82f6; margin: 20px 0; }"
                                +
                                ".highlight-grid { display: grid; grid-template-columns: repeat(3, 1fr); gap: 15px; text-align: center; }"
                                +
                                ".highlight-value { font-size: 32px; font-weight: 800; color: #1e3a8a; }" +
                                ".table { width: 100%; border-collapse: separate; border-spacing: 0 8px; margin-top: 10px; }"
                                +
                                ".table th { color: #718096; font-size: 12px; text-transform: uppercase; padding: 12px; text-align: left; }"
                                +
                                ".table tr { background-color: #f8fafc; }" +
                                ".table td { padding: 15px; border-top: 1px solid #f1f5f9; border-bottom: 1px solid #f1f5f9; }"
                                +
                                ".table td:first-child { border-left: 1px solid #f1f5f9; border-radius: 10px 0 0 10px; color:#3b82f6; font-weight:bold; }"
                                +
                                ".table td:last-child { border-right: 1px solid #f1f5f9; border-radius: 0 10px 10px 0; font-weight: 700; }"
                                +
                                ".version { background: #d7e1f0; padding: 12px; border-radius: 8px; text-align: center; margin-top: 20px; }"
                                +
                                ".version-text { font-weight: bold; color: #4865e7; }" +
                                "</style>" +
                                "</head>" +
                                "<body>" +
                                "<div class='container'>" +
                                "<div class='header'>" +
                                "<h1>SYSTEM STATISTICS REPORT</h1>" +
                                "<p>Official System Overview & Analytics Summary</p>" +
                                "</div>" +
                                "<div class='card'>" +

                                "<div class='section-title'>Overall Counts</div>" +
                                "<div class='highlight-box'>" +
                                "<div class='highlight-grid'>" +
                                "<div><div class='detail-label'>Total Students</div><div class='highlight-value'>"
                                + totalStudents + "</div></div>" +
                                "<div><div class='detail-label'>Total Courses</div><div class='highlight-value'>"
                                + totalCourses + "</div></div>" +
                                "<div><div class='detail-label'>Total Instructors</div><div class='highlight-value'>"
                                + totalInstructors + "</div></div>" +
                                "</div>" +
                                "</div>" +

                                "<div class='section-title'>Student Distribution</div>" +
                                "<div class='details-grid'>" +
                                "<div class='detail-item'><div class='detail-label'>Arts Students</div><div class='detail-value'>"
                                + artsStudents + "</div></div>" +
                                "<div class='detail-item'><div class='detail-label'>Science Students</div><div class='detail-value'>"
                                + scienceStudents + "</div></div>" +
                                "<div class='detail-item'><div class='detail-label'>Engineering Students</div><div class='detail-value'>"
                                + engStudents + "</div></div>" +
                                "<div class='detail-item'><div class='detail-label'>Total Results</div><div class='detail-value'>"
                                + totalResults + "</div></div>" +
                                "</div>" +

                                "<div class='section-title'>Academic Statistics</div>" +
                                "<div class='details-grid'>" +
                                "<div class='detail-item'><div class='detail-label'>Students with Results</div><div class='detail-value'>"
                                + withResults + "</div></div>" +
                                "<div class='detail-item'><div class='detail-label'>Students without Results</div><div class='detail-value'>"
                                + withoutResults + "</div></div>" +
                                "<div class='detail-item'><div class='detail-label'>Instructors with Courses</div><div class='detail-value'>"
                                + instructorsWithCourses + "</div></div>" +
                                "<div class='detail-item'><div class='detail-label'>Instructors without Courses</div><div class='detail-value'>"
                                + instructorsWithoutCourses + "</div></div>" +
                                "</div>" +

                                "<div class='section-title'>All Students</div>";

                if (!studentRows.isEmpty()) {
                        statsDetails += "<table class='table'><thead><tr><th>ID</th><th>Name</th><th>Type</th><th>GPA</th><th>Has Results</th></tr></thead><tbody>"
                                        +
                                        studentRows + "</tbody></table>";
                } else {
                        statsDetails += "<div style='text-align:center; padding:20px; color:#94a3b8;'>No students found</div>";
                }

                statsDetails += "</div>" +
                                "<div class='version'>" +
                                "<div class='version-text'>" +
                                "<strong>Issued on " + currentDate + " | System Verified Record<br>" +
                                "Version 2.0 | © 2026 RMS - Result Management System | All Rights Reserved</strong>"
                                +
                                "</div>" +
                                "</div></body></html>";

                return statsDetails;
        }

        public static String esc(String s) {
                if (s == null)
                        return "";
                return s.replace("&", "&amp;")
                                .replace("<", "&lt;")
                                .replace(">", "&gt;")
                                .replace("\"", "&quot;")
                                .replace("'", "&apos;");
        }
}