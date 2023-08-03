package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmployeeSignUpForm {

    MyJFrame frame;
    Panel panel;

    TextField employeeID;
    TextField lastName;
    TextField firstName;
    TextField address;
    TextField phoneNumber;

    Button buttonOk;

    public void createEmployeeSignUpForm(){
    // creates JFrame
    frame = new MyJFrame();
    frame.createFrame("Manager Login", 300, 300);

    // create a panel
    panel = new Panel();
    panel.createPanel(20, 30, 20, 30, "null", 0, 1, 10, 10, frame.getJFrame());
    }
}