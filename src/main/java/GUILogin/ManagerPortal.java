package GUILogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPortal extends JFrame implements ActionListener {
    private JButton viewSchedule;
    private JButton viewTimeOff;
    private JLabel label;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT =250;
    public ManagerPortal(){
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }

    private void createComponents(){
        viewSchedule = new JButton("View Schdeule");
        viewSchedule.addActionListener(this);

        viewTimeOff = new JButton("View TimeOff Requests");

        label = new JLabel();

        JPanel gui = new JPanel(new GridLayout(0,1,10,10));
        gui.setBorder(new EmptyBorder(20,30,20,30));

//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        gui.add(viewSchedule);
        gui.add(viewTimeOff);
        gui.add(label);
        add(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new ManagerPortal();


        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
