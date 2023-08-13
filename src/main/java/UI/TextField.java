package UI;
import javax.swing.*;

public class TextField {
    public JTextField textField;

    /**
     * Retrieves the JTextField instance managed by this class.
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * Creates a new JTextField with specified dimensions and adds it to the specified JFrame.
     *
     * @param frame The JFrame to which the JTextField will be added.
     * @param boundX The x-coordinate of the text field's top-left corner.
     * @param boundY The y-coordinate of the text field's top-left corner.
     * @param boundWidth The width of the text field.
     * @param boundHeight The height of the text field.
     */
    public void createTextField(JFrame frame, int boundX, int boundY, int boundWidth, int boundHeight) {
        textField = new JTextField();
        textField.setBounds(boundX, boundY, boundWidth, boundHeight);
        frame.add(textField);
    }
}
