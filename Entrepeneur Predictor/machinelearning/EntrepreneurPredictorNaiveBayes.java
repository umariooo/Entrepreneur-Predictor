package machinelearning;
import java.util.Map;
import javax.swing.JOptionPane;

public class EntrepreneurPredictorNaiveBayes {

	public static void predict(String[] userConditions) {
	    String csvFilePath = "C:\\Users\\Umar Imtiaz\\eclipse-workspace\\test\\src\\dataset.csv"; // main CSV file  smaller csv file =databusiness.csv
	    TrainingData trainingData = new TrainingData(csvFilePath);
	    PredictorModel predictorModel = new PredictorModel();
	    predictorModel.trainModel(trainingData.getData());

	    // Make the prediction using Naive Bayes algorithm
	    Map<String, Double> probabilities = predictorModel.predict(userConditions);

	    // Determine the predicted class
	    String predictedClass = probabilities.get("Yes") > probabilities.get("No") ? "Yes" : "No";

	    // Display the prediction and probabilities
	    StringBuilder output = new StringBuilder();
	    output.append("Prediction: Will the student become an entrepreneur? ").append(predictedClass).append("\n");
	    output.append("Probabilities:\n");
	    output.append("Yes: ").append(String.format("%.2f", probabilities.get("Yes"))).append("\n");
	    output.append("No: ").append(String.format("%.2f", probabilities.get("No"))).append("\n");

	    JOptionPane.showMessageDialog(null, output.toString());
	}

}
