Machine Learning Project README

List of Classes

PredictorModel: This class represents the machine learning model used for prediction. It includes methods for training the model, predicting verdict label probabilities, and evaluating model accuracy.

EntrepreneurPredictorGUI: This class implements a graphical user interface (GUI) for interacting with the predictor model. It allows users to input attributes and predict whether an individual is likely to become an entrepreneur.

EntrepreneurPredictorNaiveBayes: Provides a method for making predictions using the Naive Bayes algorithm. It reads data from a CSV file, trains the model, makes predictions, and displays the results using JOptionPane.

TrainingData: Responsible for reading training data from a CSV file and storing it in a list of string arrays.


Core Functionality
The core functionality of this project includes:

Training the machine learning model using provided training data.
Predicting verdict label probabilities based on input attributes.
Evaluating the accuracy of the trained model using testing data.
Providing a GUI interface for users to interact with the predictor model.
Graphical User Interface (GUI) for user interaction.
Splitting of data into training and testing sets for model evaluation.
Implementing the Naive Bayes algorithm for prediction.


Future Improvements
If more time were available I would add in more error checking especially for user input to make sure users don't misspell or type in wrong inputs.

