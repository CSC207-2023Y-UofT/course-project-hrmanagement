package UI;
import javax.swing.*;

public class Label {
    JLabel label;

    /**
     * Returns class variable 'label' representing JLabel instance
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * Creates a JLabel with the specified text and bounds.
     * @param boundX The x-coordinate of the label's top-left corner.
     * @param boundY The y-coordinate of the label's top-left corner.
     * @param boundWidth The width of the label.
     * @param boundHeight The height of the label.
     * @param frame The JFrame to which the label will be added.
     * @param labelText The text to be displayed on the label.
     */
    public void createLabel(int boundX, int boundY, int boundWidth, int boundHeight, JFrame frame, String labelText) {
        label = new JLabel(labelText);
        label.setBounds(boundX, boundY, boundWidth, boundHeight);
        if (frame != null) {
            frame.add(label);
        }
    }
}
