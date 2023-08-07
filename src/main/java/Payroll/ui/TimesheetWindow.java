package Payroll.ui;

import Payroll.PayrollConstant;
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
    private Component parentComponent;
    private Map<String, TimesheetEntity> timesheetMap;

    /**
     * Constructor
     * @param parentComponent jframe component
     * @param timesheetMap map of timesheet data
     */
    public TimesheetWindow(Component parentComponent, Map<String, TimesheetEntity> timesheetMap) {
        this.parentComponent = parentComponent;
        this.timesheetMap = timesheetMap;
    }

    /**
     * Create time sheet window
     * @param employeeName employee's name
     * @return an array containing start date, end date, hours per day, bonus
     */
    public String[] showInputDialog(String employeeName) {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        // Set start date.
        String strStartDate = timesheetMap.get(employeeName).getStartDate();
        SpinnerDateModel startDateSpinnerDateModel = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(strStartDate);
            // Create a new SpinnerDateModel with the parsed date
            startDateSpinnerDateModel = new SpinnerDateModel(startDate, null, null, java.util.Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            startDateSpinnerDateModel = new SpinnerDateModel();
        }

        // Set end date.
        String strEndDate = timesheetMap.get(employeeName).getEndDate();
        SpinnerDateModel endDateSpinnerDateModel = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = dateFormat.parse(strEndDate);
            // Create a new SpinnerDateModel with the parsed date
            endDateSpinnerDateModel = new SpinnerDateModel(endDate, null, null, java.util.Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            endDateSpinnerDateModel = new SpinnerDateModel();
        }

        JLabel employeeNameLabel = new JLabel("Employee Name:");
        employeeNameLabel.setFont(employeeNameLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JLabel employeeNameValueLabel = new JLabel(employeeName);;

        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setFont(startDateLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JSpinner startDateSpinner = new JSpinner(startDateSpinnerDateModel);
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "MM/dd/yyyy");
        startDateSpinner.setEditor(startDateEditor);

        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setFont(endDateLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JSpinner endDateSpinner = new JSpinner(endDateSpinnerDateModel);
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "MM/dd/yyyy");
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
        panel.add(blankLabel);

        // Set the preferred width and height for the input dialog
        panel.setPreferredSize(new Dimension(200, 100));

        while (true) {
            Object[] options = {"Calculate", "Cancel"};
            int result = JOptionPane.showOptionDialog(parentComponent, panel,
                    "Timesheet", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (result == JOptionPane.OK_OPTION) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String startDateStr = dateFormat.format(startDateSpinner.getValue());
                String endDateStr = dateFormat.format(endDateSpinner.getValue());
                String hoursPerDay = hoursPerDayField.getText();
                String bonus = bonusField.getText();

                // Perform date validation
                if (!DataValidator.isValidDate(startDateStr) || !DataValidator.isValidDate(endDateStr)) {
                    JOptionPane.showMessageDialog(parentComponent,
                            "Invalid date format. Please use the format MM/dd/yyyy.",
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
