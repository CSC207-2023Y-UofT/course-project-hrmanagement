package Payroll.Dao;

import Payroll.entity.TimesheetEntity;

import java.util.Map;

/**
 * Implementation of DataAccessStrategy for loading data from CSV files.
 * Note that CSV Files are designed as backup data for running payroll feature's employee list if MYSQL doesn't work
 * CSVTimesheet supports initial reading from file, but is not automatically updated
 * Data persistence is implemented through MYSQL database
 */
public class CSVStrategy implements DataAccessStrategy {

    private final String csvEmployeePath;
    private final String csvTimesheetPath;


    /**
     * Constructs a CSVStrategy instance with the provided CSV file paths.
     * @param csvEmployeePath   The path to the CSV file containing employee data.
     * @param csvTimesheetPath  The path to the CSV file containing timesheet data.
     */
    public CSVStrategy(String csvEmployeePath, String csvTimesheetPath) {
        this.csvEmployeePath = csvEmployeePath;
        this.csvTimesheetPath = csvTimesheetPath;
    }

    @Override
    public Object[][] loadEmployeeData() {
        EmployeeDAO employeeDAO = new CSVEmployeeDAO(csvEmployeePath);
        return employeeDAO.loadEmployeesTo2DArray();
    }

    @Override
    public Map<String, TimesheetEntity> loadTimesheetData() {
        TimesheetDAO timesheetDAO = new CSVTimesheetDAO(csvTimesheetPath);
        return timesheetDAO.loadTimesheetToMap();
    }
}
