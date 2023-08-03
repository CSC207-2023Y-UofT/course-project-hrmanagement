package GUILogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosePosition extends JFrame{
    private JButton manager;
    private JButton employee;
    private JLabel label;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT =250;

    public ChoosePosition(){
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setupListeners();
    }

    private void createComponents(){
        label = new JLabel("Are you..");

        manager = new JButton("Manager");
//        manager.addActionListener(this);

        employee = new JButton("Employee");



        JPanel gui = new JPanel(new GridLayout(0,1,10,10));
        gui.setBorder(new EmptyBorder(20,30,20,30));

//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        gui.add(label);
        gui.add(manager);
        gui.add(employee);

        add(gui);
    }

    private void setupListeners() {
        manager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerPortal().setVisible(true);
                dispose();
            }
        });

        employee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Employee().setVisible(true);
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new ChoosePosition();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
