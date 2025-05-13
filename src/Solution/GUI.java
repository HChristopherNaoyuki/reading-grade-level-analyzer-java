package Solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Minimalist GUI for the Reading Grade Level Analyzer.
 */
public class GUI 
{
    private static final String SAVE_FILE = "grade_level_data.ser";
    private JFrame frame;
    private JTextArea textArea;
    private JLabel resultLabel;
    private final Logic logic;

    public GUI() 
    {
        logic = loadLogic() != null ? loadLogic() : new Logic();
    }

    /**
     * Creates and displays the GUI.
     */
    public void createAndShowGUI() 
    {
        try 
        {
            // Configure main frame
            frame = new JFrame("Reading Level Analyzer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            // Set minimalist layout
            frame.setLayout(new BorderLayout(5, 5));
            frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Create components
            textArea = createTextArea();
            resultLabel = createResultLabel();
            JButton analyzeButton = createAnalyzeButton();
            JButton saveButton = createSaveButton();

            // Add components to panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            buttonPanel.add(analyzeButton);
            buttonPanel.add(saveButton);

            // Add components to frame
            frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.add(resultLabel, BorderLayout.NORTH);

            // Display frame
            frame.setVisible(true);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(frame, 
                                        "Error initializing GUI: " + e.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Creates the text area component.
     */
    private JTextArea createTextArea() 
    {
        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return area;
    }

    /**
     * Creates the result label component.
     */
    private JLabel createResultLabel() 
    {
        JLabel label = new JLabel("Enter text to analyze", SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));
        return label;
    }

    /**
     * Creates the analyze button with action handler.
     */
    private JButton createAnalyzeButton() 
    {
        JButton button = new JButton("Analyze");
        button.addActionListener((ActionEvent e) -> 
        {
            try 
            {
                String inputText = textArea.getText();
                String gradeLevel = logic.calculateGradeLevel(inputText);
                resultLabel.setText("Grade Level: " + gradeLevel);
            } 
            catch (IllegalArgumentException ex) 
            {
                resultLabel.setText("Please enter some text to analyze");
            } 
            catch (Exception ex) 
            {
                resultLabel.setText("Error in analysis");
                JOptionPane.showMessageDialog(frame, 
                                            ex.getMessage(),
                                            "Analysis Error",
                                            JOptionPane.ERROR_MESSAGE);
            }
        });
        return button;
    }

    /**
     * Creates the save button with action handler.
     */
    private JButton createSaveButton() 
    {
        JButton button = new JButton("Save");
        button.addActionListener((ActionEvent e) -> 
        {
            try 
            {
                saveLogic();
                JOptionPane.showMessageDialog(frame, 
                                            "Analysis data saved successfully",
                                            "Success",
                                            JOptionPane.INFORMATION_MESSAGE);
            } 
            catch (IOException ex) 
            {
                JOptionPane.showMessageDialog(frame, 
                                            "Failed to save data: " + ex.getMessage(),
                                            "Save Error",
                                            JOptionPane.ERROR_MESSAGE);
            }
        });
        return button;
    }

    /**
     * Saves the logic object to a file.
     */
    private void saveLogic() throws IOException 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SAVE_FILE))) 
        {
            oos.writeObject(logic);
        }
    }

    /**
     * Loads the logic object from file if it exists.
     */
    private Logic loadLogic() 
    {
        File file = new File(SAVE_FILE);
        if (!file.exists()) 
        {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(SAVE_FILE))) 
        {
            return (Logic) ois.readObject();
        } 
        catch (Exception e) 
        {
            System.err.println("Error loading saved data: " + e.getMessage());
            return null;
        }
    }
}