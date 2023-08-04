package GUILogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {


    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // connect database
            final String URL = "jdbc:mysql://localhost:3306/employees";
            final String USERNAME = "root";
            final String PASSWORD = "password";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
