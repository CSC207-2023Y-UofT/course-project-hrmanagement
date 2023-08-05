package Payroll;

/**
 * This is the business object for Employee.
 * It is used to construct a map of Employee objects for Payroll GUI.
 */
public class EmployeeEntity {
    private String employeeId;
    private String lastName;
    private String firstName;
    private String address;
    private String phoneNumber;
    private String role;

    // Constructors, getters, and setters for EmployeeBO attributes

    public String getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
