package Database;
import java.sql.*;

public class FileLoginEmployee implements EmployeeLoginDBGateway {
    private final Connection connection;

    /**
     * Constructs a new FileLoginEmployee object with the provided database connection parameters.
     * @param url The URL of the database.
     * @param username The username for database authentication.
     * @param password The password for database authentication.
     */
    public FileLoginEmployee(String url, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }
    /**
     * Checks if an employee with the given employeeID and password exists in the database.
     * @param employeeID The employeeID to check.
     * @param password The password to check.
     */
    @Override
    public boolean existsNotID(int employeeID, String password) {
        String selectSQLQuery = "SELECT EMPLOYEEID FROM Employees WHERE EMPLOYEEID = ? and PASSWORD=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQLQuery)) {
            preparedStatement.setInt(1, employeeID);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}