package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Schedule.*;

/**
 * The EmployeeBox class allows a user to choose to 'signUp' or 'login'.
 * It creates a JFrame displaying two buttons - for 'signUp' and 'login'.
 * The user can click on the signUp button to open Employee signUp form or
 * the login button to open login frame.
 */
public class ManagerPortalBox {
    Button viewSchedule;
    Button viewTimeOffRequests;
    MyJFrame frame;
    Panel panel;

    /**
     * This method creates all UI components of EmployeeBox
     */
    public void createManagerPortalBox(){

        // creates JFrame
        frame = new MyJFrame();
        frame.createFrame("Manager Portal", 300, 300);

        // create a panel
        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());

        // create button for signup
        viewSchedule = new Button();
        viewSchedule.createButtonWithIcon(frame.getJFrame(), "View Scehdule", 50, 50, 200, 50);

        // create button for login
        viewTimeOffRequests = new Button();
        viewTimeOffRequests.createButtonWithIcon(frame.getJFrame(), "View TimeOff Requests", 50, 120, 200, 50);

        // action when view schedule button is clicked
        viewSchedule.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //generate schedule
                Schedule schedule = null;
                try {
                    schedule = ScheduleGenerator.generateSchedule();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frame.getJFrame(),
                            "Invalid date format in database. Please try again.",
                            "Date Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                assert schedule != null;
                String scheduleStr = schedule.toString();

                //call information box with schedule text
                frame = new MyJFrame();
                JOptionPane.showMessageDialog(frame.getJFrame(),
                        scheduleStr,
                        "Schedule",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });

        // action when login button is clicked
        viewTimeOffRequests.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new EmployeeSignIn().setVisible(true);
                new ManagerTimeOff().CreateManagerTimeOff();
            }
        });

    }
}