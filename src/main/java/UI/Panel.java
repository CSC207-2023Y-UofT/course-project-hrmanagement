package UI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Panel {
    JPanel panel;

    /**
     * Returns class variable 'label' representing JLabel instance
     */
    public JPanel getPanel() {
        return panel;
    }
    /**
     * Creates and configures a JPanel with specified layout and borders.
     * @param top The top border size.
     * @param left The left border size.
     * @param bottom The bottom border size.
     * @param right The right border size.
     * @param layout The layout to be applied to the panel.
     * @param rows The number of rows (used for GridLayout).
     * @param cols The number of columns (used for GridLayout).
     * @param hGap The horizontal gap between components (used for GridLayout).
     * @param vGap The vertical gap between components (used for GridLayout).
     * @param frame The JFrame to which the JPanel will be added.
     */
    public void createPanel(int top, int left, int bottom, int right, String layout,
                            int rows, int cols, int hGap, int vGap, JFrame frame){
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(top, left, bottom, right));

        // set layout for panel
        switch (layout){
            case "GridLayout":
                panel.setLayout(new GridLayout(rows, cols, hGap, vGap));
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
