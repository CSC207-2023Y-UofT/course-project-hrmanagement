package Payroll.dao;

import Payroll.entity.EmployeeEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * concrete implementation of employeeDAO
 * Access and manage employee data from MYSQL database
 * Uses JDBC to connect to MYSQL database
 */
public class MySQLEmployeeDAO implements EmployeeDAO {
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
    public MySQLEmployeeDAO(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    /**
     * Retrieves employee information from database based on id
     * @param id employee's id
     * @return EmployeeEntity object
     */
    @Override
    public EmployeeEntity getEmployeeById(int id) {
        EmployeeEntity employee = null;
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    employee = new EmployeeEntity(
                            Integer.valueOf(resultSet.getInt("EMPLOYEEID")).toString(),
                            resultSet.getString("LASTNAME"),
                            resultSet.getString("FIRSTNAME"),
                            resultSet.getString("ADDRESS"),
                            resultSet.getString("PHONENUMBER"),
                            resultSet.getString("ROLE"));
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return employee;
    }

    /**
     * Retrieves all employee records from database
     * @return List of EmployeeEntity objects
     */
    @Override
    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees")) {
            while (resultSet.next()) {
                EmployeeEntity employee = new EmployeeEntity(
                        Integer.valueOf(resultSet.getInt("EMPLOYEEID")).toString(),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("FIRSTNAME"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("PHONENUMBER"),
                        resultSet.getString("ROLE"));
                employees.add(employee);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return employees;
    }

    /**
     * Retrieves all employee records and stores them in 2D array
     * 2D array format used for GUI components
     * @return 2D array of employee information
     */
    @Override
    public Object[][] loadEmployeesTo2DArray() {

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
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
                    row[1] = Integer.valueOf(resultSet.getInt("EMPLOYEEID")).toString();
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

    /**
     * Adds new employee record to databased based on EmployeeEntity object
     * @param employee EmployeeEntity object
     */
    @Override
    public void addEmployee(EmployeeEntity employee) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO employees (id, name, email) VALUES (?, ?, ?)")) {
            statement.setInt(1, Integer.parseInt(employee.getEmployeeId()));
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getFirstName());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Updates existing employee record
     * @param employee EmployeeEntity object
     */
    @Override
    public void updateEmployee(EmployeeEntity employee) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE employees SET LASTNAME = ?, " +
                             "FIRSTNAME = ? " +
                             "ADDRESS = ? " +
                             "PHONENUMBER = ? " +
                             "ROLE = ? " +
                             "WHERE EMPLOYEEID = ?")) {
            statement.setString(1, employee.getLastName());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getPhoneNumber());
            statement.setString(5, employee.getRole());
            statement.setString(6, employee.getEmployeeId());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
    }
}