package UI;
import GUILogin.EmployeePortal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EmployeeSignInBox class allows the user to Sign in to an Employee account. It includes
 * a place to input a username and a password, and a Sign-in bbutton.
 */
public class EmployeeSignInBox {
    Label userNameLabel;
    TextField userNameText;
    Label passwordLabel;
    TextField passwordText;
    Button signIn;
    MyJFrame frame;
    Panel panel;


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

    }
}
