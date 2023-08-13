package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import TimeOff.GUI.TimeOffGUI;

/**
 * The EmployeePortalBox class allows a user to choose to enter availability or request time off.
 * It creates a JFrame displaying two buttons - one for each option.
 * The user can click on the choose availability button to enter their availability or the
 * request time off button to submit a request or view the latest time off history.
 */

public class EmployeePortalBox {
    Button requestTimeOff;
    MyJFrame frame;
    Panel panel;

    /**
     * This method creates all UI components of EmployeePortalBox
     */

    public void createEmployeePortalBox(){

        // creates JFrame
        frame = new MyJFrame();
        frame.createFrame("Employee Portal", 300, 300);

        // create a panel
        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());

        // create button for login
        requestTimeOff = new Button();
        requestTimeOff.createButtonWithIcon(frame.getJFrame(), "Request TimeOff", 50, 120, 200, 50);

        // action when Request Time Off button is clicked
        requestTimeOff.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TimeOffGUI().CreateTimeOffGUI("Employee");
            }
        });

    }
}