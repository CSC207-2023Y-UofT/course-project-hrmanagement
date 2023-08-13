package TimeOff;

import UI.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ManagerTimeOff class allows a user to choose to approve or deny Time Off requests.
 * It creates a JFrame displaying four text fields for the name, id and time off start and end dates.
 * The user can click enter the information and click the approve or button to update the request.
 * The JFrame displays a label showing the latest time off requests.
 */

public class ManagerTimeOff {
    Button approve;
    Button deny;

    public void CreateManagerTimeOff(JFrame frame, TextField nametext, TextField idtext, TextField starting, TextField ending){
        approve = new Button();
        approve.createButtonWithIcon(frame, "Approve", 50, 225, 100, 20);

        deny = new Button();
        deny.createButtonWithIcon(frame, "Deny", 150, 225, 100, 20);

        approve.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifyTimeOff().Modify("Approve", frame, nametext.textField.getText(), idtext.textField.getText(),
                        starting.textField.getText(), ending.textField.getText());
            }
        });

        deny.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifyTimeOff().Modify("Deny", frame, nametext.textField.getText(), idtext.textField.getText(),
                        starting.textField.getText(), ending.textField.getText());
            }
        });

    }
}