package models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Record_List<T> implements Serializable {

    // Data Members
    private ArrayList<T> items;

    // Non Argument Constructor
    public Record_List() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public boolean remove(String id) {
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);

            if (item instanceof Student) {
                Student student = (Student) item;
                if (student.getStudentID().equals(id)) {
                    items.remove(i);
                    return true;
                }
            } else if (item instanceof Course) {
                Course course = (Course) item;
                if (course.getCourseCode().equals(id)) {
                    items.remove(i);
                    return true;
                }
            } else if (item instanceof Course_Instructor) {
                Course_Instructor instructor = (Course_Instructor) item;
                if (instructor.getName().equals(id)) {
                    items.remove(i);
                    return true;
                }
            } else if (item instanceof Result_Entry) {
                Result_Entry resultEntry = (Result_Entry) item;
                if (resultEntry.getCourse().getCourseCode().equals(id)) {
                    items.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public void set(int index, T item) {
        if (index >= 0 && index < items.size()) {
            items.set(index, item);
        }
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

    public T search(String searchText) {
        searchText = searchText.trim();

        for (T item : items) {
            if (item instanceof Student) {
                Student student = (Student) item;
                if (student.getStudentID().equalsIgnoreCase(searchText) ||
                        student.getName().equalsIgnoreCase(searchText)) {
                    return item;
                }
            } else if (item instanceof Course) {
                Course course = (Course) item;
                if (course.getCourseCode().equalsIgnoreCase(searchText)) {
                    return item;
                }
            } else if (item instanceof Course_Instructor) {
                Course_Instructor instructor = (Course_Instructor) item;
                if (instructor.getName().equalsIgnoreCase(searchText)) {
                    return item;
                }
            }
        }
        return null;
    }
}
