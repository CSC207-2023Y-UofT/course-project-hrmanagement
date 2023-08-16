package Payroll.Dao;

import Payroll.entity.TimesheetEntity;

import java.util.Map;

/**
 * Implementation of DataAccessStrategy for loading data from a MySQL database.
 * MYSQL database includes data persistence for employee and timesheet
 */
public class MySQLStrategy implements DataAccessStrategy {

    private final String jdbcUrl;
    private String username;
    private String password;

    /**
     * Constructs a MySQLStrategy instance with the provided database connection details.
     * @param jdbcUrl  The JDBC URL of the MySQL database.
     * @param username The database username.
     * @param password The database password.
     */
    public MySQLStrategy(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public Object[][] loadEmployeeData() {
        EmployeeDAO employeeDAO = new MySQLEmployeeDAO(jdbcUrl, username, password);
        return employeeDAO.loadEmployeesTo2DArray();
    }

    @Override
    public Map<String, TimesheetEntity> loadTimesheetData() {
        TimesheetDAO timesheetDAO = new MySQLTimesheetDAO(jdbcUrl, username, password);
        return timesheetDAO.loadTimesheetToMap();
    }
}
