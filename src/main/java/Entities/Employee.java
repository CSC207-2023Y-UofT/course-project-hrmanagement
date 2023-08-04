package Entities;

public class Employee {

    private int employeeID;
    private String lastName;
    private String firstName;
    private String address;
    private String phoneNumber;
    private  String pass;

    public Employee(){

    }

    /**
     * Creates an Employee object with the specified attributes.
     *
     * @param employeeID  The employeeID for the employee.
     * @param lastName    The last name of the employee.
     * @param firstName   The first name of the employee.
     * @param address     The address of the employee.
     * @param phoneNumber The phone number of the employee.
     * @param pass        The password of the employee.
     */
    public Employee(int employeeID, String lastName, String firstName, String address, String phoneNumber, String pass) {
        this.employeeID = employeeID;
        this.lastName= lastName;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pass = pass;
    }

    // Getters and Setter methods

    /**
     * Retrieves EmployeeID of the employee.
     *
     * @return The employee's ID.
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets EmployeeID of the employee.
     *
     * @param employeeID:  The employeeID for the employee.
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Retrieves LastName of the employee.
     *
     * @return The employee's LastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets lastName of the employee.
     *
     * @param lastName:  The lastName for the employee.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves firstName of the employee.
     *
     * @return The employee's firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets firstName of the employee.
     *
     * @param firstName:  The firstName for the employee.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves address of the employee.
     *
     * @return The employee's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address of the employee.
     *
     * @param address:  The address for the employee.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves phoneNumber of the employee.
     *
     * @return The employee's phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phoneNumber of the employee.
     *
     * @param phoneNumber:  The phoneNumber for the employee.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Retrieves password of the employee.
     *
     * @return The employee's password.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets pass of the employee.
     *
     * @param pass:  The password for the employee.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
