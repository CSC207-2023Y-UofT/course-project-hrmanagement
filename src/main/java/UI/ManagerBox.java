package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ManagerBox class allows a user to choose to sign in with appropriate credentials.
 * It creates a JFrame displaying a textfield for manager id entry and one for password entry.
 * The user can click on the sign in button to open ManagerPortalBox.
 */

public class ManagerBox {
    Label userNameLabel;
    TextField userNameText;
    Label passwordLabel;
    TextField passwordText;
    Button signIn;
    MyJFrame frame;
    Panel panel;
    Button back;

    /**
     * This method creates all UI components of ManagerBox
     */

    public void createManagerBox(){

        // creates JFrame
        frame = new MyJFrame();
        frame.createFrame("Manager Login", 300, 300);

        // create a panel
        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "null", 0, 1, 10, 10, frame.getJFrame());

        // label for userName
        userNameLabel = new Label();
        userNameLabel.createLabel(50, 8, 80, 20, frame.getJFrame(), "ManagerID");

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

        // Back button to return
        back = new Button();
        back.createButtonWithIcon(frame.getJFrame(), "< Back to Selection", 1, 200, 200, 20);

        // action when signIn button is clicked
        signIn.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerPortalBox().createManagerPortalBox();
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
