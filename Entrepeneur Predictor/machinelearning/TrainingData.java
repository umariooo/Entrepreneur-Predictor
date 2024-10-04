package machinelearning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainingData {
    private final List<String[]> data;

    // Constructor to initialize TrainingData object by reading data from a CSV file
    public TrainingData(String filePath) {
        this.data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Split the line by comma to get individual values
                String[] row = line.split(",");
                // Add the values to the list as a new instance
                data.add(row);
            }
        } catch (IOException e) {
            // Print stack trace if an IOException occurs while reading the file
            e.printStackTrace();
        }
    }

    // Method to retrieve the training data
    public List<String[]> getData() {
        return data;
    }
}
