package Payroll;

/**
 * Interface defining constants used in payroll feature
 */
public interface PayrollConstant {

    // mysql database connection details, used in main class
    String db_jdbcUrl = "jdbc:mysql://localhost:3306/employees";
    String db_username = "root";
    String db_password = "password";

    // csv database details, used in main
    String strPathToEmployeeFile = "./data/Employees.csv";
    String strPathToTimesheetFile = "./data/Timesheets.csv";

    // default values for hours per day and bonus
    double HoursPerDay = 7.5;
    double DefaultBonus = 100.00;

    // Roles
    String ROLE_MANAGER = "Manager";
    String ROLE_EMPLOYEE = "Employee";

    double MANAGER_HOURLY_RATE = 50;
    double EMPLOYEE_HOURLY_RATE = 40;
    double MINIMUM_HOURLY_RATE = 15;

    boolean READ_DATA_FROM_MYSQL = true;  // default true means reading from mysql
    // only MYSQL database supports data persistence
    // CSV is designed as a backup database just to demonstrate the functionalities of payroll feature

    String SIMPLE_DATE_FORMAT = "yyyy/MM/dd";

    // The default start and end date should be based on current system date.
    // It is hard-coded in here for simplicity.
    String DEFAULT_START_DATE = "2023/07/01";
    String DEFAULT_END_DATE = "2023/07/31";
    Double DEFAULT_SALARY = 0.0;

}
