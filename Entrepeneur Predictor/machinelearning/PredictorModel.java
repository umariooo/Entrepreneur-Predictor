package machinelearning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PredictorModel {
    // Store the counts of each verdict label in the training data
    private final Map<String, Integer> verdictCounts;
    // Store the counts of each attribute value for each verdict label in the training data
    private final Map<String, Map<String, Integer>> attributeCounts;

    // Constructor to initialize verdictCounts and attributeCounts maps
    public PredictorModel() {
        this.verdictCounts = new HashMap<>();
        this.attributeCounts = new HashMap<>();
    }

    // Train the model using the provided training data
    public void trainModel(List<String[]> allData) {
        // Split the data into training and testing sets
        int dataSize = allData.size();
        int trainSize = (int) (dataSize * 0.7); // 70% of data for training
        List<String[]> trainingData = allData.subList(0, trainSize);
        List<String[]> testingData = allData.subList(trainSize, dataSize);

        // Train the model using the training data
        for (String[] instance : trainingData) {
            // Extract the verdict label from the training instance
            String label = instance[6];
            // Increment the count for the verdict label
            verdictCounts.put(label, verdictCounts.getOrDefault(label, 0) + 1);
            // Iterate over the attributes of the training instance
            for (int i = 0; i < instance.length - 1; i++) {
                // Extract the attribute value
                String attribute = instance[i];
                // Increment the count for the attribute value associated with the verdict label
                attributeCounts.putIfAbsent(attribute, new HashMap<>());
                Map<String, Integer> counts = attributeCounts.get(attribute);
                counts.put(label, counts.getOrDefault(label, 0) + 1);
            }
        }

        // Evaluate the model using the testing data
        int correctPredictions = 0;
        for (String[] instance : testingData) {
            String[] attributes = new String[instance.length - 1];
            System.arraycopy(instance, 0, attributes, 0, instance.length - 1);
            Map<String, Double> probabilities = predict(attributes);
            String predictedVerdict = probabilities.get("Yes") > probabilities.get("No") ? "Yes" : "No";
            String actualVerdict = instance[instance.length - 1];
            if (predictedVerdict.equals(actualVerdict)) {
                correctPredictions++;
            }
        }
        double accuracy = (double) correctPredictions / testingData.size() * 100;
        System.out.println("Model accuracy: " + accuracy + "%");
    }

    // Predict the verdict label probabilities for the given input attributes
    public Map<String, Double> predict(String[] attributes) {
        Map<String, Double> probabilities = new HashMap<>();
        for (String label : verdictCounts.keySet()) {
            // Initialize probability with prior probability of the verdict label
            double probability = Math.log((double) verdictCounts.get(label) / getTotalTrainingDataCount());
            // Calculate conditional probability for each attribute value
            for (String attribute : attributes) {
                // Retrieve counts of attribute value for the given verdict label
                Map<String, Integer> counts = attributeCounts.getOrDefault(attribute, new HashMap<>());
                int count = counts.getOrDefault(label, 0);
                // Update probability using Naive Bayes formula
                probability += Math.log((double) (count + 1) / (verdictCounts.get(label) + counts.size()));
            }
            // Store the probability for the verdict label
            probabilities.put(label, Math.exp(probability)); // Convert log probability back to regular probability
        }
        return probabilities;
    }


    // Calculate the total count of instances in the training data
    private int getTotalTrainingDataCount() {
        return attributeCounts.size();
    }
}
