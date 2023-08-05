package Payroll;

import javax.swing.*;
import java.util.Map;

public class PayrollMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                PayrollGUI payrollGUI = new PayrollGUI();
                init(payrollGUI);

                // Display the payroll GUI.
                payrollGUI.run();
                payrollGUI.setVisible(true);
            }
        });
    }

    // Initialize the GUI.
    private static void init(PayrollGUI gui)
    {
        PayrollCalculator payrollCalculator = new PayrollCalculator();

        // Load employee data from CSV file or DB.
        Object[][]  employees = loadEmployeeData();
        Map<String, TimesheetEntity> timesheetMap = loadTimesheetData();

        gui.setEmployees(employees);
        gui.setTimesheetMap(timesheetMap);
        gui.setPayrollCalculator(payrollCalculator);
    }

    // Load employee data from CSV file or DB.
    private static Object[][] loadEmployeeData()
    {
        Object[][]  employees = null;
        if (PayrollConstant.READ_DATA_FROM_DB) {
            employees = DBDataReader.loadEmployeesTo2DArray();
        }
        else {
            Map<String, EmployeeEntity> employeeMap = CSVDataReader.loadEmployeesFromCSV(PayrollConstant.strPathToEmployeeFile);
            employees = CSVDataReader.convertEmployeeMapTo2DArray(employeeMap);

        }
        return employees;
    }

    // Load timesheet data from CSV file or DB.
    private static Map<String, TimesheetEntity> loadTimesheetData()
    {
        Map<String, TimesheetEntity> timesheetMap = null;
        if (PayrollConstant.READ_DATA_FROM_DB) {
            timesheetMap = DBDataReader.loadTimesheetsTo2DArray();
        }
        else {
            timesheetMap = CSVDataReader.loadTimesheetsFromCSV(PayrollConstant.strPathToTimesheetFile);
        }
        return timesheetMap;
    }
}
