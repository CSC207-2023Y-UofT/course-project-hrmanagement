package Payroll.dao;

import Payroll.entity.TimesheetEntity;

import java.util.Map;

/**
 * Implementation of DataAccessStrategy for loading data from CSV files.
 */
public class CSVStrategy implements DataAccessStrategy {

    private String csvEmployeePath;
    private String csvTimesheetPath;


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
