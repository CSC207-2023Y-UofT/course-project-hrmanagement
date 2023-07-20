package GUILogin;

import javax.swing.*;

public class GUIManager {
    public static void system() {

        // panel and frame
        JPanel managerPanel = new JPanel();
        JFrame systemFrame = new JFrame();
        systemFrame.setSize(350, 200);
        systemFrame.setLocationRelativeTo(null);
        systemFrame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        systemFrame.add(managerPanel);

        managerPanel.setLayout(null);

        JLabel welcomeManager = new JLabel("Hr Management system: Manager");
        welcomeManager.setBounds(20, 20, 80, 25);
        managerPanel.add(welcomeManager);

        // add button for employee creation

        systemFrame.setVisible(true);
    }
}
