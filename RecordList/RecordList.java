package RecordList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecordList<T> implements Serializable {

    // Data Members
    private ArrayList<T> items;

    // Non Argument Constructor
    public RecordList() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public boolean remove(String id) {
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);

            if (item instanceof Student.Student) {
                Student.Student student = (Student.Student) item;
                if (student.getStudentID().equals(id)) {
                    items.remove(i);
                    return true;
                }
            }
            else if (item instanceof Course.Course) {
                Course.Course course = (Course.Course) item;
                if (course.getCourseCode().equals(id)) {
                    items.remove(i);
                    return true;
                }
            }
            else if (item instanceof CourseInstructor.CourseInstructor) {
                CourseInstructor.CourseInstructor instructor = (CourseInstructor.CourseInstructor) item;
                if (instructor.getName().equals(id)) {
                    items.remove(i);
                    return true;
                }
            }
            else if (item instanceof ResultEntry.ResultEntry) {
                ResultEntry.ResultEntry resultEntry = (ResultEntry.ResultEntry) item;
                if (resultEntry.getCourse().getCourseCode().equals(id)) {
                    items.remove(i);
                    return true;
                }
            }
            else if (item instanceof Transcript.Transcript) {
                
            }
        }
        return false;
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public T get(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public List<T> search(String searchText) {
        List<T> results = new ArrayList<>();
        searchText = searchText.toLowerCase();

        for (T item : items) {
            if (item instanceof Student.Student) {
                Student.Student student = (Student.Student) item;
                if (student.getStudentID().toLowerCase().contains(searchText) ||
                        student.getName().toLowerCase().contains(searchText) ||
                        student.getProgram().toLowerCase().contains(searchText)) {
                    results.add(item);
                }
            } else if (item instanceof Course.Course) {
                Course.Course course = (Course.Course) item;
                if (course.getCourseCode().toLowerCase().contains(searchText) ||
                        course.getTitle().toLowerCase().contains(searchText)) {
                    results.add(item);
                }
            } else if (item instanceof CourseInstructor.CourseInstructor) {
                CourseInstructor.CourseInstructor instructor = (CourseInstructor.CourseInstructor) item;
                if (instructor.getName().toLowerCase().contains(searchText) ||
                        instructor.getQualificaion().toLowerCase().contains(searchText) ||
                        instructor.getProgram().toLowerCase().contains(searchText)) {
                    results.add(item);
                }
            }
        }
        return results;
    }

    public String toString() {
        return "RecordList{" +
                "items=" + items +
                ", size=" + items.size() +
                '}';
    }
}
