package UI;
import javax.swing.*;

public class Button {
    JButton button;

    /**
     * Returns class variable 'button' representing JButton instance
     * @return button created by user
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Creates a JButton UI component with an image icon
     * @param buttonText: the text to be displayed on JButton
     * @param boundX: x-coordinate of JButton
     * @param boundY: y - coordinate of JButton
     * @param boundWidth: width of JButton
     * @param boundHeight: height of JButton
     */
    public void createButtonWithIcon(JFrame frame, String buttonText, int boundX,
                                     int boundY, int boundWidth, int boundHeight) {
        button = new JButton(buttonText);
        button.setBounds(boundX,boundY,boundWidth,boundHeight);
        frame.add(button);
    }
}
