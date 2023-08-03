package GUILogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Employee extends JFrame{
    private JButton signUp;
    private JButton login;
    private JLabel label;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT =250;
    public Employee(){
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setupListeners();
    }

    private void createComponents(){
        signUp = new JButton("SignUp");

        login = new JButton("Login");

        label = new JLabel();

        JPanel gui = new JPanel(new GridLayout(0,1,10,10));
        gui.setBorder(new EmptyBorder(20,30,20,30));

//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        gui.add(signUp);
        gui.add(login);
        gui.add(label);
        add(gui);
    }

    private void setupListeners() {
        //opens SignUp page
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeForm().setVisible(true);
                dispose();
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeSignIn().setVisible(true);
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new Employee();


        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
