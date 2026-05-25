package DataStore;
import RecordList.RecordList;
import java.io.*;
import java.util.List;

public class DataStore<T> {

    public boolean saveToFile(String fileName, List<T> items) {
        if (!fileName.endsWith(".dat")) {
            fileName += ".dat";
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(items);
            System.out.println("Data saved successfully to: " + fileName);
            return true;

        } catch (IOException e) {
            System.err.println("Error saving data to file : " + e.getMessage());
            return false;
        }
    }

    public boolean saveToFile(String fileName, RecordList<T> recordList) {
        return saveToFile(fileName, recordList.getAll());
    }

    public List<T> loadFromFile(String fileName) {
        if (!fileName.endsWith(".dat")) {
            fileName += ".dat";
        }

        File file = new File(fileName);

        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File does not exist: " + fileName);
                return new java.util.ArrayList<>();
            }

            if (file.length() == 0) {
                System.out.println("File is empty: " + fileName);
                return new java.util.ArrayList<>();
            }


            FileInputStream fileIn = new FileInputStream(fileName);

            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            List<T> items = (List<T>) objectIn.readObject();
            System.out.println("Data loaded successfully from: " + fileName);
            System.out.println("Loaded " + items.size() + " items.");
            return items;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    public RecordList<T> loadToRecordList(String fileName) {
        List<T> items = loadFromFile(fileName);
        RecordList<T> recordList = new RecordList<>();

        if (items != null) {
            for (T item : items) {
                recordList.add(item);
            }
        }

        return recordList;
    }

    // Check if file exists but this method is not used actually
    public boolean fileExists(String fileName) {
        if (!fileName.endsWith(".dat")) {
            fileName += ".dat";
        }
        return new File(fileName).exists();
    }

    // This method is not used actually but added for completeness
    public boolean deleteFile(String fileName) {
        if (!fileName.endsWith(".dat")) {
            fileName += ".dat";
        }

        File file = new File(fileName);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}