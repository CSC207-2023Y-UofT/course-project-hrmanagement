package GUILogin;

import Schedule.ScheduleGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static GUILogin.GUILogin.frame;

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

        //add button for schedule creation
        JLabel ScheduleManager = new JLabel("Generate Schedule for:");
        ScheduleManager.setBounds(185, 140, 200, 25);
        managerPanel.add(ScheduleManager);

        JTextField ScheduleDateEntry = new JTextField("dd/mm/yyyy");
        ScheduleDateEntry.setBounds(185, 160, 200, 25);
        managerPanel.add(ScheduleDateEntry);

        JButton ScheduleButton = new JButton(new AbstractAction("Generate") {
            @Override
            public void actionPerformed(ActionEvent e) {

                String scheduleDateStr = ScheduleDateEntry.getText();
                try {
                    Date scheduleDate = new SimpleDateFormat("dd/MM/yyyy").parse(scheduleDateStr);
//                    ScheduleGenerator.generateSchedule(scheduleDate);
//                    String scheduleStr = Schedule.Schedule.toString();
//                    JOptionPane.showMessageDialog(frame,
//                            scheduleStr,
//                            "Schedule for" + scheduleDateStr,
//                            JOptionPane.INFORMATION_MESSAGE);

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Invalid date format. Please try again.",
                            "Date Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        ScheduleButton.setBounds(185, 185, 80, 25);
        managerPanel.add(ScheduleButton);



                systemFrame.setVisible(true);
    }

}
