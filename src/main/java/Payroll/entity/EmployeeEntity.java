package Payroll.entity;

/**
 * Employee business object
 * Used to construct a map of Employee objects for Payroll GUI.
 */
public class EmployeeEntity {
    private String employeeId;
    private String lastName;
    private String firstName;
    private String address;
    private String phoneNumber;
    private String role;
    private String pass;

    // Constructors, getters, and setters for EmployeeEntity attributes
    public EmployeeEntity() {}

    public EmployeeEntity(String employeeId, String lastName, String firstName, String address, String phoneNumber, String role) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
