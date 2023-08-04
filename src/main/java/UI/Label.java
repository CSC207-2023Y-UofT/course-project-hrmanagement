package UI;

import javax.swing.*;
import java.awt.*;

public class Label {
    JLabel label;

    /**
     * Returns class variable 'label' representing JLabel instance
     * @return label created by user
     */
    public JLabel getLabel() {
        return label;
    }


    /**
     * Creates a label and (optionally) adds it to an existing Dialog Box.
     * @param labelText: text to be displayed on label
     */
    public void createLabel(int boundX, int boundY, int boundWidth, int boundHeight, JFrame frame, String labelText) {
        label = new JLabel(labelText);
        label.setBounds(boundX, boundY, boundWidth, boundHeight);
        if (frame != null) {
            frame.add(label);
        }
    }

}
