package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EmployeeSignInBox class allows a user to choose to sign in with appropriate credentials.
 * It creates a JFrame displaying a textfield for employee id entry and one for password entry.
 * The user can click on the sign-in button to open EmployeePortalBox
 */

public class EmployeeSignInBox {
    Label userNameLabel;
    TextField userNameText;
    Label passwordLabel;
    TextField passwordText;
    Button signIn;
    MyJFrame frame;
    Panel panel;
    Button back;
    /**
     * This method creates all UI components of EmployeeSignInBox
     */

    public void createEmployeeBox(){

        // creates JFrame
        frame = new MyJFrame();
        frame.createFrame("Employee Login", 300, 300);

        // create a panel
        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "null", 0, 1, 10, 10, frame.getJFrame());

        // label for userName
        userNameLabel = new Label();
        userNameLabel.createLabel(50, 8, 80, 20, frame.getJFrame(), "EmployeeID");

        // textField for userName
        userNameText = new TextField();
        userNameText.createTextField(frame.getJFrame(), 50, 27, 193, 28);

        // label for password
        passwordLabel = new Label();
        passwordLabel.createLabel(50, 55, 70, 20, frame.getJFrame(), "Password");

        // textField for password
        passwordText = new TextField();
        passwordText.createTextField(frame.getJFrame(), 50, 75, 193, 28);

        // Back button to return
        back = new Button();
        back.createButtonWithIcon(frame.getJFrame(), "< Back to Employees", 1, 200, 200, 20);

        // create button for signIn
        signIn = new Button();
        signIn.createButtonWithIcon(frame.getJFrame(), "SignIn", 90, 110, 100, 50);

        // action when signIn button is clicked
        signIn.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeePortalBox().createEmployeePortalBox();
                frame.getJFrame().dispose();
            }
        });

        back.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeBox().createEmployeeBox();
                frame.getJFrame().dispose();
            }
        });
    }
}
