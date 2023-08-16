package Payroll;

import Payroll.entity.TimesheetEntity;
import Payroll.Dao.*;
import Payroll.ui.PayrollGUI;
import Payroll.usecase.PayrollCalculator;

import javax.swing.*;
import java.util.Map;

/**
 * The main entry point of the Payroll feature.
 * This class initializes the graphical user interface (GUI), loads employee and timesheet data,
 * and displays the payroll calculator GUI for the user to interact with.
 */
public class PayrollApplication {
    public static String jdbcUrl = PayrollConstant.db_jdbcUrl;
    public static String username = PayrollConstant.db_username;
    public static String password = PayrollConstant.db_password;

    public static String csv_employee_filepath = PayrollConstant.strPathToEmployeeFile;
    public static String csv_timesheet_filepath = PayrollConstant.strPathToTimesheetFile;

    public void start() {
        SwingUtilities.invokeLater(() -> {
            PayrollGUI payrollGUI = new PayrollGUI();
            PayrollCalculator payrollCalculator = PayrollCalculator.getInstance();

            DataAccessStrategy dataAccessStrategy;
            TimesheetDAO timesheetDAO;

            if (PayrollConstant.READ_DATA_FROM_MYSQL) { // determines which data access strategy to use
                dataAccessStrategy = new MySQLStrategy(jdbcUrl, username, password);
                timesheetDAO = new MySQLTimesheetDAO(jdbcUrl, username, password);
            } else {
                dataAccessStrategy = new CSVStrategy(csv_employee_filepath, csv_timesheet_filepath);
                timesheetDAO = new CSVTimesheetDAO(csv_timesheet_filepath);
            }

            // load employee and timesheet data
            Object[][]  employees = dataAccessStrategy.loadEmployeeData();
            Map<String, TimesheetEntity> timesheetMap = dataAccessStrategy.loadTimesheetData();

            payrollGUI.setEmployees(employees);
            payrollGUI.setTimesheetMap(timesheetMap);
            payrollGUI.setPayrollCalculator(payrollCalculator);

            // Display the payroll GUI.
            payrollGUI.run(timesheetDAO);
            payrollGUI.setVisible(true);
        });
    }
}