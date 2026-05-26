package models;
import java.io.*;
import java.util.List;

public class Data_Store<T> {

    public boolean saveToFile(String fileName, List<T> items) {
        if (!fileName.endsWith(".dat")) {
            fileName += ".dat";
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(items);
            // System.out.println("Data saved successfully to: " + fileName);
            return true;

        } catch (IOException e) {
            // System.err.println("Error saving data to file : " + e.getMessage());
            return false;
        }
    }

    public boolean saveToFile(String fileName, Record_List<T> recordList) {
        return saveToFile(fileName, recordList.getAll());
    }

    public List<T> loadFromFile(String fileName) {
        if (!fileName.endsWith(".dat")) {
            fileName += ".dat";
        }

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File does not exist: " + fileName);
            return null;
        }

        try (FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            List<T> items = (List<T>) objectIn.readObject();
            // System.out.println("Data loaded successfully from: " + fileName);
            // System.out.println("Loaded " + items.size() + " items.");
            return items;

        } catch (IOException | ClassNotFoundException e) {
            // System.err.println("Error loading data from file: " + e.getMessage());
            return null;
        }
    }

    public Record_List<T> loadToRecordList(String fileName) {
        List<T> items = loadFromFile(fileName);
        Record_List<T> recordList = new Record_List<>();

        if (items != null) {
            for (T item : items) {
                recordList.add(item);
            }
        }
        return recordList;
    }
}