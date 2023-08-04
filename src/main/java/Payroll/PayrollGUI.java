package Payroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import TeamStructure.Employee;

public class PayrollGUI {
    private JFrame frame;
    private JTextField usernameField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField hoursPerDayField;
    private JButton calculateButton;
    private JLabel totalSalaryLabel;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public PayrollGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Payroll Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        frame.add(inputPanel, BorderLayout.CENTER);

        totalSalaryLabel = new JLabel("Total Salary: $0.00");
        totalSalaryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(totalSalaryLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Employee Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        usernameField = new JTextField(15);
        inputPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Start Date (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        startDateField = new JTextField(10);
        inputPanel.add(startDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("End Date (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        endDateField = new JTextField(10);
        inputPanel.add(endDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Hours Per Day:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        hoursPerDayField = new JTextField(5);
        inputPanel.add(hoursPerDayField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTotalPay();
            }
        });
        inputPanel.add(calculateButton, gbc);

        return inputPanel;
    }

    private void displayTotalPay() {
        try {
            String username = usernameField.getText();
            Date startDate = DATE_FORMAT.parse(startDateField.getText());
            Date endDate = DATE_FORMAT.parse(endDateField.getText());
            double hoursPerDay = Double.parseDouble(hoursPerDayField.getText());

            TimeSheet timeSheet = new TimeSheet(startDate, endDate, hoursPerDay);

            //Employee employee = EmployeeRepository.findEmployeeByUsername(username); // include later when database is set up
            Employee employee = new Employee("john123", "asdfghjkl", 20.0); // just for testing for now

            if (employee != null) {
                PayrollCalculator payrollCalculator = new PayrollCalculator();
                double totalSalary = payrollCalculator.calculateTotalPay(employee, timeSheet);
                totalSalaryLabel.setText("Total Salary: $" + String.format("%.2f", totalSalary));
            } else {
                JOptionPane.showMessageDialog(frame, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ParseException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input format. Please check your input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

