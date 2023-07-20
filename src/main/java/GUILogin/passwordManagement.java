package GUILogin;

import javax.swing.*;

public class passwordManagement {
    public static void pwManage() {

        // panel and frame
        JPanel panel = new JPanel();
        JFrame systemFrame = new JFrame();
        systemFrame.setSize(350, 200);
        systemFrame.setLocationRelativeTo(null);
        systemFrame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        systemFrame.add(panel);

        panel.setLayout(null);

        JLabel yay = new JLabel("Hr Management system:");
        yay.setBounds(20, 20, 80, 25);
        panel.add(yay);

        systemFrame.setVisible(true);
    }
}
