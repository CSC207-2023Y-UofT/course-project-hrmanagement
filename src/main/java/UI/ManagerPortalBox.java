package UI;

import Payroll.PayrollApplication;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import Schedule.*;
import TimeOff.TimeOffGUI;

/**
 * The ManagerPortalox class allows a user to choose to view schedules, timeoff requests or payroll.
 * It creates a JFrame displaying buttons for each of the features.
 * The user can click on each of the respective buttons to open a window to utilize the features.
 */

public class ManagerPortalBox {
    Button viewSchedule;
    Button viewTimeOffRequests;
    Button viewPayrollCalculator;
    MyJFrame frame;
    Panel panel;

    /**
     * This method creates all UI components of ManagerPortalBox
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
        viewSchedule.createButtonWithIcon(frame.getJFrame(), "View Schedule", 50, 50, 200, 50);

        // create button for login
        viewTimeOffRequests = new Button();
        viewTimeOffRequests.createButtonWithIcon(frame.getJFrame(), "View TimeOff Requests", 50, 120, 200, 50);


        // create button for payroll calculator
        viewPayrollCalculator = new Button();
        viewPayrollCalculator.createButtonWithIcon(frame.getJFrame(), "Calculate Payroll", 50, 190, 200, 50);

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

        // action when View Time Off Requests button is clicked
        viewTimeOffRequests.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TimeOffGUI().CreateTimeOffGUI("Manager");
            }
        });

        // Action when "View Payroll Calculator" button is clicked
        viewPayrollCalculator.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Payroll Calculator GUI
                PayrollApplication payrollApplication = new PayrollApplication();
                payrollApplication.start();
            }
        });

    }
}