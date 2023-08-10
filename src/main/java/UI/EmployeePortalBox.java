package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EmployeeBox class allows a user to choose to 'signUp' or 'login'.
 * It creates a JFrame displaying two buttons - for 'signUp' and 'login'.
 * The user can click on the signUp button to open Employee signUp form or
 * the login button to open login frame.
 */
public class EmployeePortalBox {
    Button chooseAvailibility;
    Button requestTimeOff;
    MyJFrame frame;
    Panel panel;

    /**
     * This method creates all UI components of EmployeeBox
     */
    public void createEmployeePortalBox(){

        // creates JFrame
        frame = new MyJFrame();
        frame.createFrame("Employee Portal", 300, 300);

        // create a panel
        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());

        // create button for signup
        chooseAvailibility = new Button();
        chooseAvailibility.createButtonWithIcon(frame.getJFrame(), "Choose Availability", 50, 50, 200, 50);

        // create button for login
        requestTimeOff = new Button();
        requestTimeOff.createButtonWithIcon(frame.getJFrame(), "Request TimeOff", 50, 120, 200, 50);

        // action when signUp button is clicked
        chooseAvailibility.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new EmployeeForm().setVisible(true);
            }
        });

        // action when login button is clicked
        requestTimeOff.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new EmployeeSignIn().setVisible(true);
            }
        });

    }
}
