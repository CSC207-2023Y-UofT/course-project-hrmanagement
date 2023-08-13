package GUILogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeSignIn extends JFrame {
    private JTextField employeeID;
    private JPasswordField pass;
    private JButton login;
    private JLabel label;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT =250;

    public EmployeeSignIn(){
        initializeComponents();
        setupLayout();
        setupListeners();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }

    private void initializeComponents() {
        employeeID = new JTextField(10);
        pass = new JPasswordField(15);

        login = new JButton("Login");

        label = new JLabel();

//        JPanel gui = new JPanel(new GridLayout(0,1,10,10));
//        gui.setBorder(new EmptyBorder(20,30,20,30));

//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        gui.add(signUp);
//        gui.add(login);
//        gui.add(label);
//        add(gui);
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        //employeeID
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("EmployeeID:"), gbc);

        gbc.gridx = 1;
        panel.add(employeeID, gbc);

        //password
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        panel.add(pass, gbc);

        //okButton
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(login, gbc);

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupListeners() {
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int employeeID = Integer.parseInt(EmployeeSignIn.this.employeeID.getText());

                String passText = new String(pass.getPassword());

                // Save the employee information to the database
                Connection connection = null;
                try
                {
                    connection = SQLConnection.getConnection();

                    String query = "SELECT * FROM Employees where EMPLOYEEID=? and PASSWORD=?";

                    // Set values using preparedStatement
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, employeeID);
                    preparedStatement.setString(2, passText);

                    ResultSet result = preparedStatement.executeQuery();


                    if(result.next()) {
//                        new EmployeePortal().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong Username or Password.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }



    public static void main(String[] args) {
        JFrame frame = new EmployeeSignIn();


        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
