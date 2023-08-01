package Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIPasswordManagement {

    // frame and panel
    public static JFrame createFrame;
    public  static JPanel createPanel;
    public static JFrame frame;

    // user
    public static JLabel createUserLabel;
    public static JTextField createUserText;

    // password
    public static JLabel createPasswordLabel;
    public static JTextField createPasswordText;

    // login button
    public static JButton activateButton;
    public static void pwManage() {

        // panel and frame
        createPanel = new JPanel();
        createFrame = new JFrame();
        createFrame.setSize(350, 200);
        createFrame.setLocationRelativeTo(null);
        createFrame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        createFrame.add(createPanel);

        createPanel.setLayout(null);

        // username
        createUserLabel = new JLabel("New Username:");
        createUserLabel.setBounds(10, 20, 80, 25);
        createPanel.add(createUserLabel);

        createUserText = new JTextField(20);
        createUserText.setBounds(100, 20, 165, 25);
        createPanel.add(createUserText);

        // password
        createPasswordLabel = new JLabel("New Password:");
        createPasswordLabel.setBounds(10, 50, 80, 25);
        createPanel.add(createPasswordLabel);

        createPasswordText = new JTextField(20);
        createPasswordText.setBounds(100, 50, 165, 25);
        createPanel.add(createPasswordText);

        // login button
        activateButton = new JButton(new AbstractAction("Create User") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUser = createUserLabel.getText();
                char[] chars = newUser.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (char c : chars) {
                    if (Character.isDigit(c)) {
                        break;
                    }
                }
            }
        });
        activateButton.setBounds(185, 80, 80, 25);
        createPanel.add(activateButton);

        createFrame.setVisible(true);
    }
}
