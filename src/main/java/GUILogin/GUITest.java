package GUILogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Objects;

public class GUITest implements ActionListener {

    // frame and panel
    public static JFrame loginFrame;
    public  static JPanel loginPanel;
    public static JFrame frame;

    // user
    public static JLabel userLabel;
    public static JTextField userText;

    // password
    public static JLabel passwordLabel;
    public static JPasswordField passwordText;

    // login button
    public static JButton loginButton;
    public static JLabel failLabel;

    public static void loginTest() {

        // panel and frame
        loginPanel = new JPanel();
        loginFrame = new JFrame();
        loginFrame.setSize(350, 200);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        loginFrame.add(loginPanel);

        loginPanel.setLayout(null);

        // username
        userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        loginPanel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        loginPanel.add(userText);

        // password
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        loginPanel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        loginPanel.add(passwordText);

        // login button
        loginButton = new JButton("Login");
        loginButton.setBounds(185, 80, 80, 25);
        loginButton.addActionListener(new GUITest());
        loginPanel.add(loginButton);

        loginFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = userText.getText();
        String password = passwordText.getText();

        // user password (should be under password management for manager)
        HashMap<String, String> employee = new HashMap<String, String>();
        employee.put("Employee", "Password"); // (user, password)

        HashMap<String, String> manager = new HashMap<String, String>();
        manager.put("Manager", "Password");

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
//
//        for (String i : employee.keySet()) {
//            if (Objects.equals(i, username)) {
//                if (Objects.equals(employee.get(username), password)) {
//                    GUIEmployee.systemTest();
//                    loginFrame.dispose();
//                }
//            } else {
//                JOptionPane.showMessageDialog(frame,
//                        "Please enter a valid username and/or password.",
//                        "Login Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }

//        for (String i : manager.keySet()) {
//            if (Objects.equals(i, username)) {
//                if (Objects.equals(manager.get(username), password)) {
//                    GUIManager.system();
//                    loginFrame.dispose();
//                }
//            } else {
//                JOptionPane.showMessageDialog(frame,
//                        "Please enter a valid username and/or password.",
//                        "Login Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
    }
}
