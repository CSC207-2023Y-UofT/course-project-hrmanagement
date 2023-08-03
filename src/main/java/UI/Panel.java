package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Panel {
    JPanel panel;

    /**
     * Returns class variable 'label' representing JLabel instance
     * @return label created by user
     */
    public JPanel getPanel() {
        return panel;
    }

    public void createPanel(int top, int left, int bottom, int right, String layout,
                            int rows, int cols, int hgap, int vgap, JFrame frame){
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(top, left, bottom, right));

        // set layout for panel
        switch (layout){
            case "GridLayout":
                panel.setLayout(new GridLayout(rows, cols, hgap, vgap));
                break;
            case "BorderLayout":
                panel.setLayout(new BorderLayout());
                break;
            default:
                // If an unsupported layout is provided, set a default layout
                panel.setLayout(new FlowLayout());
        }

        frame.add(panel);
    }

}
