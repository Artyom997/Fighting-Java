package ui_elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputDialog extends JDialog {
    private JTextField inputField;
    private String userInput = null;

    /**
     * Constructor for the InputDialog.
     *
     * @param parent            The parent frame. Can be null.
     * @param question          The question or prompt to display.
     * @param submitButtonText  The text for the submit button.
     */
    public InputDialog(JFrame parent, String question, String submitButtonText) {
        super(parent, "Input", true);
        setLayout(new BorderLayout(10, 10));

        // Create and add the question label
        JLabel questionLabel = new JLabel(question);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        add(questionLabel, BorderLayout.NORTH);

        // Create and add the input field
        inputField = new JTextField(20);
        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        add(inputPanel, BorderLayout.CENTER);

        // Create and add the submit button with the given text
        JButton submitButton = new JButton(submitButtonText);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInput = inputField.getText();
                dispose(); // Close the dialog
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set up dialog properties
        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Shows the input dialog and returns the user's input.
     *
     * @param parent            The parent frame. Can be null.
     * @param question          The question or prompt to display.
     * @param submitButtonText  The text for the submit button.
     * @return                  The text entered by the user, or null if no input was provided.
     */
    public static String showInputDialog(String question, String submitButtonText) {
        InputDialog dialog = new InputDialog(null, question, submitButtonText);
        dialog.setVisible(true);
        return dialog.userInput;
    }

    // Example usage and testing of the InputDialog
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String input = InputDialog.showInputDialog("Enter your name:", "Submit");
            if (input != null) {
                System.out.println("User input: " + input);
            } else {
                System.out.println("No input provided.");
            }
        });
    }
}
