package Entities;

public class CommonEmployee implements Employee{
    private final int employeeID;
    private String lastName;
    private String firstName;
    private String address;
    private String phoneNumber;
    private final String pass;

    /**
     * Constructs a CommonEmployee object with the provided attributes.
     * @param employeeID The ID for the employee.
     * @param lastName The last name of the employee.
     * @param firstName The first name of the employee.
     * @param address The address of the employee.
     * @param phoneNumber The phone number of the employee.
     * @param passText The password associated with the employee.
     */
    public CommonEmployee(int employeeID, String lastName, String firstName, String address, String phoneNumber, String passText) {
        this.employeeID = employeeID;
        this.lastName= lastName;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pass = passText;
    }
    /**
     * Constructs a CommonEmployee object with the employee ID and password.
     * @param employeeID The ID for the employee.
     * @param password The password associated with the employee.
     */
   public CommonEmployee(int employeeID, String password){
        this.employeeID = employeeID;
        this.pass = password;
   }

    /**
     * Checks if the password associated with this employee is valid.
     */
    @Override
    public boolean passwordIsValid() {
        return pass != null && pass.length() > 5;
    }

    //getters and setters
    @Override
    public int getEmployeeID() {
        return employeeID;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getPassword() {
        return pass;
    }
}
