package Manager;

import Manager.GUIPasswordManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GUIManager {

    public static JFrame systemFrame;
    public static void system() {

        // panel and frame
        JPanel managerPanel = new JPanel();
        systemFrame = new JFrame();
        systemFrame.setSize(500, 350);
        systemFrame.setLocationRelativeTo(null);
        systemFrame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        systemFrame.add(managerPanel);

        managerPanel.setLayout(null);

        JLabel welcomeManager = new JLabel("Hr Management system: Manager");
        welcomeManager.setBounds(20, 20, 200, 25);
        managerPanel.add(welcomeManager);

        // add button for employee creation
        JButton createEmployeeButton = new JButton((new AbstractAction("Create Employee") {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIPasswordManagement.pwManage();
                systemFrame.dispose();
            }
        }));
        createEmployeeButton.setBounds(185, 80, 200, 25);
        managerPanel.add(createEmployeeButton);

        systemFrame.setVisible(true);
    }

}
