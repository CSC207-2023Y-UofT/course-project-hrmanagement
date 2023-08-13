package Entities;

public class CommonEmployeeFactory implements EmployeeFactory {

    /**
     * Creates a new instance of a common employee with the provided attributes.
     * @param employeeID The unique identifier for the employee.
     * @param lastName The last name of the employee.
     * @param firstName The first name of the employee.
     * @param address The address of the employee.
     * @param phoneNumber The phone number of the employee.
     * @param passText The password associated with the employee.
     */
    @Override
    public Employee create(int employeeID, String lastName, String firstName, String address, String phoneNumber, String passText) {
        return new CommonEmployee(employeeID, firstName, lastName, address, phoneNumber, passText);
    }
    /**
     * Creates a new instance of a common employee with the provided employee ID and password.
     * @param employeeID The unique identifier for the employee.
     * @param password The password associated with the employee.
     */
    @Override
    public Employee checkLogin(int employeeID, String password) {
        return new CommonEmployee(employeeID, password);
    }

}
