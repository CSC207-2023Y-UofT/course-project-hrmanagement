package UI;

import GUILogin.EmployeeForm;
import GUILogin.EmployeeSignIn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeTimeOff {
    MyJFrame frame;
    Panel panel;
    Button request;
    TextField history;

    public void CreateEmployeeTimeOff(){
        frame = new MyJFrame();
        frame.createFrame("TimeOff", 300, 300);

        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());

        request = new Button();
        request.createButtonWithIcon(frame.getJFrame(), "Request", 50, 225, 200, 20);

        history = new TextField();
        history.createTextField(frame.getJFrame(), 0,0,300,200);

    }
}
