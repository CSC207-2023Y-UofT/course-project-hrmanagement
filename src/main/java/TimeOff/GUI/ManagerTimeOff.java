package TimeOff.GUI;

import TimeOff.UseCases.ModifyTimeOff;
import UI.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ManagerTimeOff class provides Manager specific additional features to the baseline GUI.
 * This includes buttons for approving or denying Time Off Requests but notably not for requesting them.
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