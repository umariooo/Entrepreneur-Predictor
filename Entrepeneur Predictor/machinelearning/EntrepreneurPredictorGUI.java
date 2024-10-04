//Author: Umar Imtiaz C22376443
//Date: 19/04/24
//Program Description: OOP JAVA Assignment option 4. Aim is to use naive bayes classifier to predict if	
//					   student will become an entrepreneur or not. Program contains GUI and trains on 70% of dataset
//                     the remaining data is tested on. The data is read from a csv file.


package machinelearning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrepreneurPredictorGUI extends JFrame {
    private static final String[] LABELS = {"Student (Yes/No): ", "Gender (Male/Female): ", "Parent/Guardian (Own business/No own business): ",
            "Part-time job (Yes/No): ", "Urban or rural address (Urban/Rural): ", "Studies business subjects (Yes/No): "};

    private JTextField[] textFields;
    private JButton predictButton;

    public EntrepreneurPredictorGUI() {
        setTitle("Entrepreneur Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        createFormPanel();
        createPredictButton();
        pack();
        setLocationRelativeTo(null); // Center the window
    }

    private void createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(LABELS.length, 2, 5, 5));
        textFields = new JTextField[LABELS.length];
        for (int i = 0; i < LABELS.length; i++) {
            JLabel label = new JLabel(LABELS[i]);
            textFields[i] = new JTextField();
            formPanel.add(label);
            formPanel.add(textFields[i]);
        }
        add(formPanel, BorderLayout.CENTER);
    }

    private void createPredictButton() {
        predictButton = new JButton("Predict");
        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] userConditions = getUserInput();
                EntrepreneurPredictorNaiveBayes.predict(userConditions);
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(predictButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private String[] getUserInput() {
        String[] userInput = new String[textFields.length];
        for (int i = 0; i < textFields.length; i++) {
            userInput[i] = textFields[i].getText();
        }
        return userInput;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EntrepreneurPredictorGUI().setVisible(true);
            }
        });
    }
}
