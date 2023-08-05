package Payroll;

/**
 * This is the business object for Timesheet.
 * It is used to construct a map of Timesheet objects for Payroll calculation.
 */
public class TimesheetEntity {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String startDate;
    private String endDate;

    // Constructors, getters, and setters for EmployeeBO attributes
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
}
