package UI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerBox implements ActionListener {
    Label userNameLabel;
    TextField userNameText;
    Label passwordLabel;
    PasswordField passwordText;
    Button signIn;
    MyJFrame frame;
    Panel panel;
    Button back;
    /**
     * Creates the Manager login user interface.
     */
    public void createManagerBox(){
        frame = new MyJFrame();
        frame.createFrame("Manager Login", 300, 300);
        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "null", 0, 1, 10, 10, frame.getJFrame());

        userNameLabel = new Label();
        userNameLabel.createLabel(50, 8, 80, 20, frame.getJFrame(), "ManagerID");
        userNameText = new TextField();
        userNameText.createTextField(frame.getJFrame(), 50, 27, 193, 28);

        passwordLabel = new Label();
        passwordLabel.createLabel(50, 55, 70, 20, frame.getJFrame(), "Password");
        passwordText = new PasswordField();
        passwordText.createPasswordField(frame.getJFrame(), 50, 75, 193, 28);

        // create button for signIn
        signIn = new Button();
        signIn.createButtonWithIcon(frame.getJFrame(), "SignIn", 90, 110, 100, 50);
        signIn.getButton().addActionListener(this);

    }

    /**
     * This method is called when the "SignIn" button is clicked.
     * @param e The ActionEvent object generated by the "SignIn" button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int managerID = Integer.parseInt(this.userNameText.getTextField().getText());
        String password = new String(passwordText.getPasswordFieldField().getPassword());
        if (managerID == 1 && password.equals("manager")){
            ManagerPortalBox managerPortalBox = new ManagerPortalBox();
            managerPortalBox.createManagerPortalBox();
        }else{
            JOptionPane.showMessageDialog(null, "Incorrect ManagerID or password. Try again!");
        }
//        back = new Button();
//        back.createButtonWithIcon(frame.getJFrame(), "< Back to Selection", 1, 200, 200, 20);

    }
}

