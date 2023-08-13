package Database;
import java.sql.*;

public class FileEmployee implements EmployeeRegisterDBGateway{
    private final Connection connection;

    /**
     * Constructs a new FileEmployee object with the provided database connection parameters.
     * @param url The URL of the database.
     * @param username The username for database authentication.
     * @param password The password for database authentication.
     */
    public FileEmployee(String url, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
        createTableDB();
    }

    /**
     * Creates the Employees table in the database if it doesn't exist.
     */
    public void createTableDB() throws SQLException{
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Employees (" +
                "EmployeeID INT PRIMARY KEY," +
                "LastName VARCHAR(255) NOT NULL," +
                "FirstName VARCHAR(255) NOT NULL," +
                "Address VARCHAR(255) NOT NULL," +
                "PhoneNumber VARCHAR(255) NOT NULL," +
                "Password VARCHAR(255) NOT NULL" +
                ")";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(createTableQuery);
        }
    }

    /**
     * Saves employee registration data into the database.
     * @param requestModel The employee registration data to be saved.
     */
    @Override
    public void save(EmployeeRegisterDBRequestModel requestModel) {
        String insertQuery = "insert INTO Employees(EMPLOYEEID, LASTNAME, FIRSTNAME, ADDRESS, PHONENUMBER, PASSWORD)  VALUES (?, ?, ?, ?, ?, ?)";
        try{
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, requestModel.getEmployeeID());
            preparedStatement.setString(2, requestModel.getLastName());
            preparedStatement.setString(3, requestModel.getFirstName());
            preparedStatement.setString(4, requestModel.getAddress());
            preparedStatement.setString(5, requestModel.getPhoneNumber());
            preparedStatement.setString(6, requestModel.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
        }
    }


    @Override
    public boolean existsByName(int employeeIdentifier) {
        String selectSQLQuery = "SELECT EMPLOYEEID FROM Employees WHERE EMPLOYEEID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQLQuery)) {
            preparedStatement.setInt(1, employeeIdentifier);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
