package GUILogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeForm extends JFrame {
    private JTextField employeeID;
//    private JTextField userName;
    private JTextField lastName;
    private JTextField firstName;
    private JTextField address;
    private JTextField phoneNumber;
    private JButton buttonOk;


    // connect database
    private final String url = "jdbc:mysql://localhost:3306/employees";
    private final String username = "root";
    private final String password = "password";

    // setup form
    public EmployeeForm() {
        initializeComponents();
        setupLayout();
        setupListeners();
    }

   // settingUp text fields
  private void initializeComponents() {
      employeeID = new JTextField(10);
      lastName = new JTextField(10);
      firstName = new JTextField(10);
      address = new JTextField(20);
      phoneNumber = new JTextField(15);
      buttonOk = new JButton("OK");
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

        // lastName
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Last Name:"), gbc);

        gbc.gridx = 1;
        panel.add(lastName, gbc);

        // firstName
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("First Name:"), gbc);

        gbc.gridx = 1;
        panel.add(firstName, gbc);

        // address
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Address:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(address, gbc);

        //phoneNumber
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Phone Number:"), gbc);

        gbc.gridx = 1;
        panel.add(phoneNumber, gbc);

        //okButton
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(buttonOk, gbc);

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setupListeners() {
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int employeeID = Integer.parseInt(EmployeeForm.this.employeeID.getText());
//                String userName = EmployeeForm.this.userName.getText();
                String lastName = EmployeeForm.this.lastName.getText();
                String firstName = EmployeeForm.this.firstName.getText();
                String address = EmployeeForm.this.address.getText();
                String phoneNumber = EmployeeForm.this.phoneNumber.getText();

                // Save the employee information to the database
                try (Connection conn = DriverManager.getConnection(url, username, password)) {
                    String query = "insert INTO Employees(EMPLOYEEID, LASTNAME, FIRSTNAME, ADDRESS, PHONENUMBER)  VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setInt(1, employeeID);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, firstName);
                    preparedStatement.setString(4, address);
                    preparedStatement.setString(5, phoneNumber);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Employee information saved to the database.");
                    } else {
                        System.out.println("Failed to save employee information to the database.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Clear the input fields after saving
                EmployeeForm.this.employeeID.setText("");
                EmployeeForm.this.lastName.setText("");
                EmployeeForm.this.firstName.setText("");
                EmployeeForm.this.address.setText("");
                EmployeeForm.this.phoneNumber.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeForm().setVisible(true);
            }
        });
    }



}
