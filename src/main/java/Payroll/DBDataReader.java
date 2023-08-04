package Payroll;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBDataReader
{
    public static String jdbcUrl = "jdbc:mysql://localhost:3306/employees";
    public static String username = "root";
    public static String password = "password";

    public static Object[][] loadEmployeesTo2DArray() {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Step 1: Create the JDBC connection

            // Step 2: Execute SQL query
            String sql = "SELECT * FROM employees";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                // Step 3: Store the data in an ArrayList
                List<Object[]> dataList = new ArrayList<>();
                while (resultSet.next()) {
                    Object[] row = new Object[7]; // Assuming 6 columns in the table
                    row[0] = false;     // The is for the Select column.
                    row[1] = new Integer(resultSet.getInt("EMPLOYEEID")).toString();
                    row[2] = resultSet.getString("LASTNAME");
                    row[3] = resultSet.getString("FIRSTNAME");
                    row[4] = resultSet.getString("ADDRESS");
                    row[5] = resultSet.getString("PHONENUMBER");
                    row[6] = resultSet.getString("ROLE");
                    dataList.add(row);
                }

                // Step 4: Convert ArrayList to Object[][]
                Object[][] resultArray = new Object[dataList.size()][];
                for (int i = 0; i < dataList.size(); i++) {
                    resultArray[i] = dataList.get(i);
                }
                return resultArray;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, TimesheetEntity> loadTimesheetsTo2DArray() {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
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
