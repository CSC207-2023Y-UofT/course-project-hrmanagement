package Payroll.ui;

import Payroll.PayrollConstant;
import Payroll.entity.EmployeeEntity;
import Payroll.entity.TimesheetEntity;
import Payroll.usecase.DataValidator;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Timesheet related GUI components
 * Displays a dialogue window to display / gather time-sheet related information from the user
 */
public class TimesheetWindow {
    private final Component parentComponent;
    private final Map<String, TimesheetEntity> timesheetMap;

    /**
     * Constructor
     * @param parentComponent JFrame component
     * @param timesheetMap map of timesheet data
     */
    public TimesheetWindow(Component parentComponent, Map<String, TimesheetEntity> timesheetMap) {
        this.parentComponent = parentComponent;
        this.timesheetMap = timesheetMap;
    }

    /**
     * Create time sheet window
     * @param employee  employee entity
     * @return an array containing start date, end date, hours per day, bonus
     */
    public String[] showInputDialog(EmployeeEntity employee) {
        JPanel panel = new JPanel(new GridLayout(7, 2));

        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        String employeeName = firstName + " " + lastName;

        // Set start date.
        TimesheetEntity timesheet  = (TimesheetEntity)(timesheetMap.get(employeeName));
        if (timesheet == null)
        {
            timesheet = new TimesheetEntity();
            timesheet.setEmployeeId(employee.getEmployeeId());
            timesheet.setFirstName(firstName);
            timesheet.setLastName(lastName);
            timesheet.setStartDate(PayrollConstant.DEFAULT_START_DATE);
            timesheet.setEndDate(PayrollConstant.DEFAULT_END_DATE);
            timesheet.setSalary(PayrollConstant.DEFAULT_SALARY);

            timesheetMap.put(employeeName, timesheet);
        }

        double previousSalary = timesheetMap.get(employeeName).getSalary();
        String strStartDate = timesheetMap.get(employeeName).getStartDate();

        SpinnerDateModel startDateSpinnerDateModel;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(PayrollConstant.SIMPLE_DATE_FORMAT);
            Date startDate = dateFormat.parse(strStartDate);
            // Create a new SpinnerDateModel with the parsed date
            startDateSpinnerDateModel = new SpinnerDateModel(startDate, null, null, java.util.Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            startDateSpinnerDateModel = new SpinnerDateModel();
        }

        // Set end date.
        String strEndDate = timesheetMap.get(employeeName).getEndDate();

        SpinnerDateModel endDateSpinnerDateModel;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(PayrollConstant.SIMPLE_DATE_FORMAT);
            Date endDate = dateFormat.parse(strEndDate);
            // Create a new SpinnerDateModel with the parsed date
            endDateSpinnerDateModel = new SpinnerDateModel(endDate, null, null, java.util.Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            endDateSpinnerDateModel = new SpinnerDateModel();
        }

        JLabel employeeNameLabel = new JLabel("Employee Name:");
        employeeNameLabel.setFont(employeeNameLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JLabel employeeNameValueLabel = new JLabel(employeeName);

        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setFont(startDateLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JSpinner startDateSpinner = new JSpinner(startDateSpinnerDateModel);
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, PayrollConstant.SIMPLE_DATE_FORMAT);
        startDateSpinner.setEditor(startDateEditor);

        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setFont(endDateLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JSpinner endDateSpinner = new JSpinner(endDateSpinnerDateModel);
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, PayrollConstant.SIMPLE_DATE_FORMAT);
        endDateSpinner.setEditor(endDateEditor);

        JLabel hoursPerDayLabel = new JLabel("Hours Per Day:");
        hoursPerDayLabel.setFont(hoursPerDayLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JTextField hoursPerDayField = new JTextField(10);
        hoursPerDayField.setFont(hoursPerDayLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        hoursPerDayField.setText(String.valueOf(PayrollConstant.HoursPerDay));

        JLabel bonusLabel = new JLabel("Bonus:");
        bonusLabel.setFont(bonusLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JTextField bonusField = new JTextField(10);
        bonusField.setFont(bonusLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        bonusField.setText(String.valueOf(PayrollConstant.DefaultBonus));

        JLabel previousSalaryLabel = new JLabel("Previous Salary: ");
        previousSalaryLabel.setFont(previousSalaryLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JLabel previousSalaryValueLabel = new JLabel("$" + Double.toString(previousSalary));


        JLabel blankLabel = new JLabel("");

        panel.add(employeeNameLabel);
        panel.add(employeeNameValueLabel);

        panel.add(startDateLabel);
        panel.add(startDateSpinner);
        panel.add(endDateLabel);
        panel.add(endDateSpinner);
        panel.add(hoursPerDayLabel);
        panel.add(hoursPerDayField);
        panel.add(bonusLabel);
        panel.add(bonusField);
        panel.add(previousSalaryLabel);
        panel.add(previousSalaryValueLabel);
        panel.add(blankLabel);

        // Set the preferred width and height for the input dialog
        panel.setPreferredSize(new Dimension(200, 100));

        while (true) {
            Object[] options = {"Calculate", "Cancel"};
            int result = JOptionPane.showOptionDialog(parentComponent, panel,
                    "Timesheet", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (result == JOptionPane.OK_OPTION) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(PayrollConstant.SIMPLE_DATE_FORMAT);
                String startDateStr = dateFormat.format(startDateSpinner.getValue());
                String endDateStr = dateFormat.format(endDateSpinner.getValue());
                String hoursPerDay = hoursPerDayField.getText();
                String bonus = bonusField.getText();

                // Perform date validation
                if (!DataValidator.isValidDate(startDateStr) || !DataValidator.isValidDate(endDateStr)) {
                    JOptionPane.showMessageDialog(parentComponent,
                            "Invalid date format. Please use the format " + PayrollConstant.SIMPLE_DATE_FORMAT + ".",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (!DataValidator.isValidDouble(hoursPerDay))
                {
                    JOptionPane.showMessageDialog(parentComponent,
                            "Invalid number.  A sample of hours per day is: 7.5",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (!DataValidator.isValidDouble(bonus))
                {
                    JOptionPane.showMessageDialog(parentComponent,
                            "Invalid bonus value. ",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // Convert dates to Date objects for comparison
                    Date startDate = DataValidator.parseDate(startDateStr);
                    Date endDate = DataValidator.parseDate(endDateStr);

                    // Check if the End Date is after the Start Date
                    assert endDate != null;
                    if (endDate.before(startDate)) {
                        JOptionPane.showMessageDialog(parentComponent,
                                "End Date must be after Start Date.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        return new String[]{startDateStr, endDateStr, hoursPerDay, bonus};
                    }
                }
            } else {
                // Return null if the user cancels the input
                return null;
            }
        }
    }

}
