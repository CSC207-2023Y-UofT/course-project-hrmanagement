package TimeOff.GUI;

import TimeOff.UseCases.ModifyTimeOff;
import UI.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EmployeeTimeOff class provides Employee specific additional features to the baseline GUI.
 * This includes a button for submitting a Time Off Request but not ones for approving or denying one.
 */

public class EmployeeTimeOff {
    Button request;

    public void CreateEmployeeTimeOff(JFrame frame, TextField nametext, TextField idtext, TextField starting, TextField ending){
        request = new Button();
        request.createButtonWithIcon(frame, "Request", 50, 225, 200, 20);

        request.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifyTimeOff().Modify("Request", frame, nametext.textField.getText(), idtext.textField.getText(),
                        starting.textField.getText(), ending.textField.getText());
            }
        });

    }
}