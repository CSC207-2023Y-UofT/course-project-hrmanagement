package Payroll.dao;

import Payroll.entity.TimesheetEntity;
import Payroll.usecase.EmployeeHelper;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MySQLTimesheetDAO implements TimesheetDAO {
    // JDBC connection parameters
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    /**
     * Constructor, takes information required to connect to mysql database
     * @param dbUrl database url
     * @param dbUser user
     * @param dbPassword password
     */
    public MySQLTimesheetDAO(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    /**
     * Retrieves all timesheet records, stores in hashmap
     * @return Hashmap of timesheet information mapping employee name to TimeSheetEntity object
     */
    public  Map<String, TimesheetEntity> loadTimesheetToMap() {

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // Step 1: Create the JDBC connection

            // Step 2: Execute SQL query
            String sql = "SELECT * FROM timesheets";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                Map<String, TimesheetEntity> timesheetMap = new HashMap<>();

                // Step 3: Store the data in an ArrayList
                while (resultSet.next()) {
                    String employeeId = Integer.toString(resultSet.getInt("EMPLOYEEID"));
                    String lastName = resultSet.getString("LASTNAME");
                    String firstName = resultSet.getString("FIRSTNAME");
                    String startDate = resultSet.getString("STARTDATE");
                    String endDate = resultSet.getString("ENDDATE");
                    double salary  = resultSet.getDouble("SALARY");

                    // create new timesheet entity
                    createTimesheetEntity(timesheetMap, employeeId, lastName, firstName, startDate, endDate, salary);
                }

                return timesheetMap;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     * Creates a TimesheetEntity object based on the provided data and adds it to the timesheet map.
     *
     * @param timesheetMap The map to which the timesheet entity will be added.
     * @param employeeId   The ID of the employee associated with the timesheet.
     * @param lastName     The last name of the employee.
     * @param firstName    The first name of the employee.
     * @param startDate    The start date of the timesheet.
     * @param endDate      The end date of the timesheet.
     * @param salary       The salary associated with the timesheet.
     */
    static void createTimesheetEntity(Map<String, TimesheetEntity> timesheetMap,
                                      String employeeId,
                                      String lastName,
                                      String firstName,
                                      String startDate,
                                      String endDate,
                                      double salary) {
        TimesheetEntity timesheet = new TimesheetEntity();
        timesheet.setEmployeeId(employeeId);
        timesheet.setFirstName(firstName);
        timesheet.setLastName(lastName);

        timesheet.setStartDate(startDate);
        timesheet.setEndDate(endDate);
        timesheet.setSalary(salary);

        String employeeName = EmployeeHelper.getEmployeeName(firstName, lastName);
        timesheetMap.put(employeeName, timesheet);
    }

    /**
     * Adds a timesheet record to the MySQL database.
     * @param timesheet The TimesheetEntity object containing the timesheet information to be added.
     */
    @Override
    public void addTimesheet(TimesheetEntity timesheet) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO timesheets (EMPLOYEEID, LASTNAME, FIRSTNAME, STARTDATE, ENDDATE, SALARY) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, Integer.parseInt(timesheet.getEmployeeId()));
            statement.setString(2, timesheet.getLastName());
            statement.setString(3, timesheet.getFirstName());
            statement.setString(4, timesheet.getStartDate());
            statement.setString(5, timesheet.getEndDate());
            statement.setDouble(6, timesheet.getSalary());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Updates a timesheet record in the MySQL database.
     * Called when timesheet already exists
     * @param timesheet The TimesheetEntity object containing the updated timesheet information.
     */
    @Override
    public void updateTimesheet(TimesheetEntity timesheet) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE timesheets SET LASTNAME = ?, " +
                             "FIRSTNAME = ?, " +
                             "STARTDATE = ?, " +
                             "ENDDATE = ?, " +
                             "SALARY = ? " +
                             "WHERE EMPLOYEEID = ?")) {
            statement.setString(1, timesheet.getLastName());
            statement.setString(2, timesheet.getFirstName());
            statement.setString(3, timesheet.getStartDate());
            statement.setString(4, timesheet.getEndDate());
            statement.setDouble(5, timesheet.getSalary());
            statement.setString(6, timesheet.getEmployeeId());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves a timesheet record from the MySQL database based on the employee ID.
     * @param employeeId The ID of the employee associated with the timesheet to retrieve.
     * @return The TimesheetEntity object representing the retrieved timesheet, or null if not found
     */
    @Override
    public TimesheetEntity getTimesheetById(String employeeId) {
        TimesheetEntity timesheet = null;

        int iEmployeeId = Integer.parseInt(employeeId);

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM timesheets WHERE EMPLOYEEID = ?")) {
            statement.setInt(1, iEmployeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    timesheet = new TimesheetEntity((
                            Integer.valueOf(resultSet.getInt("EMPLOYEEID")).toString()),
                            resultSet.getString("FIRSTNAME"),
                            resultSet.getString("LASTNAME"),
                            resultSet.getString("STARTDATE"),
                            resultSet.getString("ENDDATE"),
                            resultSet.getDouble("SALARY"));
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return timesheet;
    }

    /**
     * Saves a timesheet record to the MySQL database. If a timesheet with the same employee ID exists,
     * it updates the existing record; otherwise, it adds a new record.
     * @param timesheet The TimesheetEntity object containing the timesheet information to be saved.
     */
    @Override
    public void saveTimesheet(TimesheetEntity timesheet) {

        String employeeId = timesheet.getEmployeeId();
        TimesheetEntity existingTimesheet = getTimesheetById(employeeId);

        if (existingTimesheet == null) {
            addTimesheet(timesheet);
        }
        else {
            updateTimesheet(timesheet);
        }
    }

}