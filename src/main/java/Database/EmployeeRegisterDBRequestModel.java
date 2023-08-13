package Database;
public class EmployeeRegisterDBRequestModel {
    private final int employeeID;
    private final String lastName, firstName, address, phoneNumber, password;

    /**
     * Constructs a new EmployeeRegisterDBRequestModel with the provided attributes.
     * @param employeeID The unique identifier for the employee.
     * @param lastName The last name of the employee.
     * @param firstName The first name of the employee.
     * @param address The address of the employee.
     * @param phoneNumber The phone number of the employee.
     * @param password The password associated with the employee.
     */
    public EmployeeRegisterDBRequestModel(int employeeID, String lastName, String firstName, String address, String phoneNumber, String password) {
        this.employeeID = employeeID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    //getters and setters
    public int getEmployeeID(){
        return employeeID;
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

    public String getPassword() {
        return password;
    }

}
