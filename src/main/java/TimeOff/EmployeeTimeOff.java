package TimeOff;

import UI.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EmployeeTimeOff class allows a user to choose to request Time Off.
 * It creates a JFrame displaying four text fields for their name, id and time off start and end dates.
 * The user can click on the request button to submit the request.
 * The JFrame displays a label showing the latest time off requests.
 */

public class EmployeeTimeOff {
    Button request;

    /**
     * This method creates all UI components of EmployeeTimeOff
     */

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