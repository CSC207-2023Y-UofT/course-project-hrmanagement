package Payroll;

import Payroll.entity.TimesheetEntity;
import Payroll.dao.*;
import Payroll.ui.PayrollGUI;
import Payroll.usecase.PayrollCalculator;

import javax.swing.*;
import java.util.Map;


public class PayrollMain
{
    public static String jdbcUrl = PayrollConstant.db_jdbcUrl;
    public static String username = PayrollConstant.db_username;
    public static String password = PayrollConstant.db_password;

    public static String csv_employee_filepath = PayrollConstant.strPathToEmployeeFile;
    public static String csv_timesheet_filepath = PayrollConstant.strPathToTimesheetFile;


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
    private static void init(PayrollGUI gui) {
        PayrollCalculator payrollCalculator = PayrollCalculator.getInstance();

        // Load employee data from CSV file or DB.
        Object[][]  employees = loadEmployeeData();
        Map<String, TimesheetEntity> timesheetMap = loadTimesheetData();

        gui.setEmployees(employees);
        gui.setTimesheetMap(timesheetMap);
        gui.setPayrollCalculator(payrollCalculator);
    }

    // Load employee data from CSV file or DB.
    private static Object[][] loadEmployeeData() {
        Object[][]  employees = null;
        if (PayrollConstant.READ_DATA_FROM_DB) {
            EmployeeDAO employeeDAO = new MySQLEmployeeDAO(jdbcUrl, username, password);

            //employees = DBDataReader.loadEmployeesTo2DArray();
            employees = employeeDAO.loadEmployeesTo2DArray();
        }
        else {
            //Map<String, EmployeeEntity> employeeMap = CSVDataReader.loadEmployeesFromCSV(PayrollConstant.strPathToEmployeeFile);
            //employees = CSVDataReader.convertEmployeeMapTo2DArray(employeeMap);

            EmployeeDAO employeeDAO = new CSVEmployeeDAO(csv_employee_filepath);
            employees = employeeDAO.loadEmployeesTo2DArray();
        }
        return employees;
    }

    // Load timesheet data from CSV file or DB.
    private static Map<String, TimesheetEntity> loadTimesheetData() {
        Map<String, TimesheetEntity> timesheetMap = null;
        if (PayrollConstant.READ_DATA_FROM_DB)
        {
            //timesheetMap = DBDataReader.loadTimesheetsToMap();
            TimesheetDAO timesheetDAO = new MySQLTimesheetDAO(jdbcUrl, username, password);
            timesheetMap = timesheetDAO.loadTimesheetToMap();
        }
        else {
            //timesheetMap = CSVDataReader.loadTimesheetsFromCSV(PayrollConstant.strPathToTimesheetFile);
            TimesheetDAO timesheetDAO = new CSVTimesheetDAO(csv_timesheet_filepath);
            timesheetMap = timesheetDAO.loadTimesheetToMap();
        }
        return timesheetMap;
    }

}
