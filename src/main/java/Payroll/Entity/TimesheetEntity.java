package Payroll.Entity;

/**
 * Timesheet business object.
 * Used to construct a map of Timesheet objects for Payroll calculation.
 */
public class TimesheetEntity {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String startDate;
    private String endDate;
    private double salary;

    public TimesheetEntity() {
    }

    public TimesheetEntity(String employeeId, String firstName, String lastName, String startDate, String endDate, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salary = salary;
    }

    // Constructors, getters, and setters for EmployeeEntity attributes
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
