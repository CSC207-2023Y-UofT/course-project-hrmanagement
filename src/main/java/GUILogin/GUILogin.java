package GUILogin;

import Employee.GUIEmployee;
import Manager.GUIManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Objects;

public class GUILogin {

    // frame and panel
    public static JFrame loginFrame;
    public static JPanel loginPanel;
    public static JFrame frame;

    // user
    public static JLabel userLabel;
    public static JTextField userText;

    // password
    public static JLabel passwordLabel;
    public static JPasswordField passwordText;

    // login button
    public static JButton loginButton;
    public static JButton changePasswordButton;

    public static void loginTest() {

        // Panel and Frame
        loginPanel = new JPanel();

        loginFrame = new JFrame();
        loginFrame.setSize(500, 400);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        loginFrame.add(loginPanel);

        loginPanel.setLayout(null);

        // Username
        userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        loginPanel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        loginPanel.add(userText);

        // Password
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        loginPanel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        loginPanel.add(passwordText);

        // Button functions
        buttonLogin();
        buttonChangePassword();

        loginFrame.setVisible(true);
    }

    public static void buttonLogin() {

        // Login Button
        loginButton = new JButton(new AbstractAction("Login") {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = userText.getText();
                String password = passwordText.getText(); // although getText is deprecated, in this event Password pass

                HashMap<String, String> employee = new HashMap<String, String>();
                employee.put("Employee", "Password"); // (user, password)

                HashMap<String, String> manager = new HashMap<String, String>();
                manager.put("Manager", "Password"); // (user, password)

                if (manager.containsKey(username)) {
                    if (Objects.equals(manager.get(username), password)) {
                        GUIManager.system();
                        loginFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Please enter a valid username and/or password.",
                                "Login Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else if (employee.containsKey(username)) {
                    if (Objects.equals(employee.get(username), password)) {
                        GUIEmployee.systemTest();
                        loginFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Please enter a valid username and/or password.",
                                "Login Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter a valid username and/or password.",
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginButton.setBounds(185, 80, 80, 25);
        loginPanel.add(loginButton);
    }

    private static void buttonChangePassword() {

        // Change Password Button
        changePasswordButton = new JButton(new AbstractAction("Change Password") {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIEmployee.systemTest();
                loginFrame.dispose();
            }
        });

        changePasswordButton.setBounds(100, 80, 80, 25);
        loginPanel.add(changePasswordButton);
    }

}
