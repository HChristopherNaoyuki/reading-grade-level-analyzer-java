package Solution;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Main class to launch the Reading Grade Level Analyzer application.
 */
public class Solution 
{
    /**
     * Main method to start the application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) 
    {
        // Run the GUI in the Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                try 
                {
                    // Create and show the graphical user interface
                    GUI gui = new GUI();
                    gui.createAndShowGUI();
                } 
                catch (Exception e) 
                {
                    showErrorDialog("Failed to initialize application: " + e.getMessage());
                }
            }
        });
    }
    
    /**
     * Helper method to display error messages.
     * 
     * @param message The error message to display
     */
    private static void showErrorDialog(String message) 
    {
        JOptionPane.showMessageDialog(null, 
                                    message, 
                                    "Error", 
                                    JOptionPane.ERROR_MESSAGE);
    }
}