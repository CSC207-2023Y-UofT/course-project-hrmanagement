package UI;

import javax.swing.*;

public class PasswordField {
    JPasswordField passwordField;

    /**
     * Returns class variable passwordField
     * @return the passwordField created by user
     */
    public JPasswordField getPasswordFieldField() {
        return passwordField;
    }

    /**
     * Creates a JPasswordField as per specified criteria and adds it to the frame.
     * @param frame: adds passwordField to JFrame 'frame'
     * @param boundX: x-coordinate of JPasswordField
     * @param boundY: y-coordinate of JPasswordField
     * @param boundWidth: width of JPasswordField
     * @param boundHeight: height of JPasswordField
     */
    public void createPasswordField(JFrame frame, int boundX, int boundY, int boundWidth, int boundHeight) {
        passwordField = new JPasswordField();
        passwordField.setBounds(boundX, boundY, boundWidth, boundHeight);
        frame.add(passwordField);
    }
}
