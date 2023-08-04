package UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerBox {
    Label userNameLabel;
    TextField userNameText;
    Label passwordLabel;
    TextField passwordText;
    Button signIn;
    MyJFrame frame;
    Panel panel;


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

        // action when signIn button is clicked
        signIn.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerPortalBox().createManagerPortalBox();
                frame.getJFrame().dispose();
            }
        });

    }
}
