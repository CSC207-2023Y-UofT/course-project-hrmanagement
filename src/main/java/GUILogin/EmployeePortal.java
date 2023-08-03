package GUILogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeePortal extends JFrame implements ActionListener {
    private JButton availability;
    private JButton timeOff;
    private JLabel label;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT =250;
    public EmployeePortal(){
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }

    private void createComponents(){
        availability = new JButton("Choose Availability");
        availability.addActionListener(this);

        timeOff = new JButton("TimeOff Request");

        label = new JLabel();

        JPanel gui = new JPanel(new GridLayout(0,1,10,10));
        gui.setBorder(new EmptyBorder(20,30,20,30));

//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        gui.add(availability);
        gui.add(timeOff);
        gui.add(label);
        add(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new EmployeePortal();


        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
