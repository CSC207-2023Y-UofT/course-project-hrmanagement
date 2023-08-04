package UI;

public class EmployeeSignUpForm {

    MyJFrame frame;
    Panel panel;

    Label employeeIDLabel;
    Label lastNameLabel;
    Label firstNameLabel;
    Label addressLabel;
    Label phoneNumberLabel;
    Label passLabel;

    TextField employeeID;
    TextField lastName;
    TextField firstName;
    TextField address;
    TextField phoneNumber;
    PasswordField pass;

    Button buttonSignUp;

/**
 * Creates the Employee SignUp Form with relevant labels, text fields, and a sign-up button.
 * This method sets up GUI components for the Employee SignUp Form,
 * including the employee ID, last name, first name, address, phone number, and password fields,
 * and "Sign Up" button.
 *
 */
 public void createEmployeeSignUpForm(){
        // creates JFrame
        frame = new MyJFrame();
        frame.createFrame("Employee SignUp", 500, 600);

        // create a panel
        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "null", 0, 1, 10, 10, frame.getJFrame());

        // labels and textFields
        employeeIDLabel = new Label();
        employeeIDLabel.createLabel(100, 100, 80, 20, frame.getJFrame(), "EmployeeID:");
        employeeID = new TextField();
        employeeID.createTextField(frame.getJFrame(), 200, 100, 193, 28);

        lastNameLabel = new Label();
        lastNameLabel.createLabel(100, 150, 80, 20, frame.getJFrame(), "Last Name:");
        lastName = new TextField();
        lastName.createTextField(frame.getJFrame(), 200, 150, 193, 28);

        firstNameLabel = new Label();
        firstNameLabel.createLabel(100, 200, 80, 20, frame.getJFrame(), "First Name:");
        firstName = new TextField();
        firstName.createTextField(frame.getJFrame(), 200, 200, 193, 28);

        addressLabel = new Label();
        addressLabel.createLabel(100, 250, 100, 20, frame.getJFrame(), "Address:");
        address = new TextField();
        address.createTextField(frame.getJFrame(), 200, 250, 193, 28);

        phoneNumberLabel = new Label();
        phoneNumberLabel.createLabel(100, 300, 100, 20, frame.getJFrame(), "Phone Number:");
        phoneNumber = new TextField();
        phoneNumber.createTextField(frame.getJFrame(), 200, 300, 193, 28);

        passLabel = new Label();
        passLabel.createLabel(100, 350, 80, 20, frame.getJFrame(), "Password:");
        pass = new PasswordField();
        pass.createPasswordField(frame.getJFrame(), 200, 350, 193, 28);

        // create button for signUp
        buttonSignUp = new Button();
        buttonSignUp.createButtonWithIcon(frame.getJFrame(), "Sign Up", 200, 400, 100, 50);

    }
}