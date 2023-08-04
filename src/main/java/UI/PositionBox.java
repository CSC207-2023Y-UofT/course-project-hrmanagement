package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The PositionBox class allows a user to choose their position.
 * It creates a JFrame displaying two buttons - for 'Manager' and 'Employee'.
 * The user can click on the Manager button to open the Manager Portal or the Employee button to open Employee Portal.
 */
public class PositionBox {
    Button manager;
    Button employee;
    MyJFrame frame;
    Panel panel;

    /**
     * This method creates all UI components of PositionBox
     */
    public void createChoosePositionBox(){
        // creates JFrame
        frame = new MyJFrame();
        frame.createFrame("Choose your Position..", 300, 300);

        // create a panel
        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());

        // create button for manager
        manager = new Button();
        manager.createButtonWithIcon(frame.getJFrame(), "Manager", 50, 50, 200, 50);

        // create button for employee
        employee = new Button();
        employee.createButtonWithIcon(frame.getJFrame(), "Employee", 50, 120, 200, 50);

        // action when manager button is clicked
        manager.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerBox().createManagerBox();
                frame.getJFrame().dispose();
            }
        });

        // action when employee button is clicked
        employee.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeBox().createEmployeeBox();
                frame.getJFrame().dispose();
            }
        });

    }
}
