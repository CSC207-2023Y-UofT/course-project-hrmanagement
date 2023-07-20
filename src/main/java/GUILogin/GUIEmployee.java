package GUILogin;

import javax.swing.*;

public class GUIEmployee {
    public static void systemTest() {

        // panel and frame
        JPanel employeePanel = new JPanel();
        JFrame systemFrame = new JFrame();
        systemFrame.setSize(350, 200);
        systemFrame.setLocationRelativeTo(null);
        systemFrame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        systemFrame.add(employeePanel);

        employeePanel.setLayout(null);

        JLabel welcomeEmployee = new JLabel("Hr Management system: Employee");
        welcomeEmployee.setBounds(20, 20, 80, 25);
        employeePanel.add(welcomeEmployee);

        systemFrame.setVisible(true);
    }
}
