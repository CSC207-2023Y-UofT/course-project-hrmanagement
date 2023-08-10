package UI;

import Controllers.EmployeeRegisterController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmployeeSignUpForm implements ActionListener {

    MyJFrame frame;
    Panel panel;
    Label employeeIDLabel, lastNameLabel, firstNameLabel, addressLabel,
            phoneNumberLabel, passLabel;
    TextField employeeIDField, lastNameField, firstNameField, addressField,
            phoneNumberField;
    PasswordField passField;
    Button buttonSignUp;
    int employeeID;
    String lastName, firstName, address, phoneNumber, passText;
    /*Controller*/
    EmployeeRegisterController employeeRegisterController;


    /**
     * Constructs an EmployeeSignUpForm with the specified EmployeeRegisterController.
     */
    public EmployeeSignUpForm(EmployeeRegisterController controller) {
        this.employeeRegisterController = controller;
        frame = new MyJFrame();
        frame.createFrame("Employee SignUp", 500, 600);

        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "null", 0, 1, 10, 10, frame.getJFrame());

        employeeIDLabel = new Label();
        employeeIDLabel.createLabel(100, 100, 80, 20, frame.getJFrame(), "EmployeeID:");
        employeeIDField = new TextField();
        employeeIDField.createTextField(frame.getJFrame(), 200, 100, 193, 28);

        lastNameLabel = new Label();
        lastNameLabel.createLabel(100, 150, 80, 20, frame.getJFrame(), "Last Name:");
        lastNameField = new TextField();
        lastNameField.createTextField(frame.getJFrame(), 200, 150, 193, 28);

        firstNameLabel = new Label();
        firstNameLabel.createLabel(100, 200, 80, 20, frame.getJFrame(), "First Name:");
        firstNameField = new TextField();
        firstNameField.createTextField(frame.getJFrame(), 200, 200, 193, 28);

        addressLabel = new Label();
        addressLabel.createLabel(100, 250, 100, 20, frame.getJFrame(), "Address:");
        addressField = new TextField();
        addressField.createTextField(frame.getJFrame(), 200, 250, 193, 28);

        phoneNumberLabel = new Label();
        phoneNumberLabel.createLabel(100, 300, 100, 20, frame.getJFrame(), "Phone Number:");
        phoneNumberField = new TextField();
        phoneNumberField.createTextField(frame.getJFrame(), 200, 300, 193, 28);

        passLabel = new Label();
        passLabel.createLabel(100, 350, 80, 20, frame.getJFrame(), "Password:");
        passField = new PasswordField();
        passField.createPasswordField(frame.getJFrame(), 200, 350, 193, 28);

        // create button for signUp
        buttonSignUp = new Button();
        buttonSignUp.createButtonWithIcon(frame.getJFrame(), "Sign Up", 200, 400, 100, 50);
        buttonSignUp.getButton().addActionListener(this);
    }

    /**
     * When the user clicks on SignUp button, this Action is performed.
     * @param evt for ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // get text from UserInout in respective fields
        employeeID = Integer.parseInt(this.employeeIDField.getTextField().getText());
        lastName = this.lastNameField.getTextField().getText();
        firstName = this.firstNameField.getTextField().getText();
        address = this.addressField.getTextField().getText();
        phoneNumber = this.phoneNumberField.getTextField().getText();
        passText = new String(passField.getPasswordFieldField().getPassword());
        try{
            employeeRegisterController.create(employeeID, lastName, firstName, address, phoneNumber, passText);
            JOptionPane.showMessageDialog(null, "SignUp Successful!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}


