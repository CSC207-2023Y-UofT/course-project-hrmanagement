package UI;

import javax.swing.*;
import java.awt.*;
public class MyJFrame {
    JFrame frame;


    public JFrame getJFrame() {
        return frame;
    }


    public void createFrame(String frameText, int width, int height){
        frame = new JFrame(frameText);
        frame.setLayout(null);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
