package Payroll;

public interface PayrollConstant {
    public static final String strPathToEmployeeFile = "./data/Employees.csv";
    public static final String strPathToTimesheetFile = "./data/Timesheets.csv";
    public static final double HoursPerDay = 7.5;
    public static final double DefaultBonus = 100.00;

    public static final String ROLE_MANAGER = "Manager";
    public static final String ROLE_EMPLOYEE = "Employee";

    public static final double MANAGER_HOURLY_RATE = 50;
    public static final double EMPLOYEE_HOURLY_RATE = 40;
    public static final double MINIMUM_HOURLY_RATE = 15;

    public static boolean READ_DATA_FROM_DB = true;  // true means reading from database

}
