package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EmployeeBox class allows a user to choose to 'signUp' or 'login'.
 * It creates a JFrame displaying two buttons - for 'signUp' and 'login'.
 * The user can click on the signUp button to open Employee signUp form or
 * the login button to open login frame.
 */

public class EmployeeBox {
    Button signUp;
    Button login;
    MyJFrame frame;
    Panel panel;
    Button back;

    /**
     * This method creates all UI components of EmployeeBox
     */

    public void createEmployeeBox(){

        // creates JFrame
        frame = new MyJFrame();
        frame.createFrame("Employees", 300, 300);

        // create a panel
        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());

        // create button for signup
        signUp = new Button();
        signUp.createButtonWithIcon(frame.getJFrame(), "SignUp", 50, 50, 200, 50);

        // create button for login
        login = new Button();
        login.createButtonWithIcon(frame.getJFrame(), "Login", 50, 120, 200, 50);

        // Back button to return
        back = new Button();
        back.createButtonWithIcon(frame.getJFrame(), "< Back to Selection", 1, 200, 200, 20);

        // action when signUp button is clicked
        signUp.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new EmployeeForm().setVisible(true);
            }
        });

        // action when login button is clicked
        login.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeSignInBox().createEmployeeBox();
                frame.getJFrame().dispose();
            }
        });

        back.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PositionBox().createChoosePositionBox();
                frame.getJFrame().dispose();
            }
        });
    }
}
