package UI;

import Entities.EmployeeFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*Main Position Box at start of the program*/
public class PositionBox {
    Button manager;
    Button employee;
    MyJFrame frame;
    Panel panel;

    /**
     * Creates the graphical user interface for choosing a position.
     * @param url The URL for connecting to the database.
     * @param userName The username for the database connection.
     * @param password The password for the database connection.
     * @param employeeFactory The factory for creating different types of employees.
     */
    public void createChoosePositionBox(String url, String userName, String password, EmployeeFactory employeeFactory){
        frame = new MyJFrame();
        frame.createFrame("Choose your Position..", 300, 300);
        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());
        manager = new Button();
        manager.createButtonWithIcon(frame.getJFrame(), "Manager", 50, 50, 200, 50);
        employee = new Button();
        employee.createButtonWithIcon(frame.getJFrame(), "Employee", 50, 120, 200, 50);

        /*ActionListener for Manager button*/
        manager.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerBox().createManagerBox();
            }
        });

        /*ActionListener for Employee button*/
        employee.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeBox().createEmployeeBox(url, userName, password, employeeFactory);
            }
        });
    }
}
