package UI;
import javax.swing.*;

public class MyJFrame {
    JFrame frame;

    /**
     * Retrieves the JFrame instance managed by this class.
     */
    public JFrame getJFrame() {
        return frame;
    }

    /**
     * Creates and configures a JFrame with specified title and dimensions.
     * @param frameText The text to be displayed in the title bar of the JFrame.
     * @param width The width of the JFrame.
     * @param height The height of the JFrame.
     */
    public void createFrame(String frameText, int width, int height){
        frame = new JFrame(frameText);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}