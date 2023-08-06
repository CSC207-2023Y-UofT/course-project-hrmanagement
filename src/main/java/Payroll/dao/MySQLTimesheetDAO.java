package Payroll.dao;

import Payroll.entity.TimesheetEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLTimesheetDAO implements TimesheetDAO {
    // JDBC connection parameters
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    /**
     * constructor, takes information required to connect to mysql database
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
                List<Object[]> dataList = new ArrayList<>();
                while (resultSet.next()) {
                    String employeeId = new Integer(resultSet.getInt("EMPLOYEEID")).toString();
                    String lastName = resultSet.getString("LASTNAME");
                    String firstName = resultSet.getString("FIRSTNAME");
                    String startDate = resultSet.getString("STARTDATE");
                    String endDate = resultSet.getString("ENDDATE");

                    // create new timesheet entity
                    TimesheetEntity timesheet = new TimesheetEntity();
                    timesheet.setEmployeeId(employeeId);
                    timesheet.setFirstName(firstName);
                    timesheet.setLastName(lastName);

                    timesheet.setStartDate(startDate);
                    timesheet.setEndDate(endDate);

                    String employeeName = firstName + " " + lastName;
                    timesheetMap.put(employeeName, timesheet);
                }

                return timesheetMap;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

}