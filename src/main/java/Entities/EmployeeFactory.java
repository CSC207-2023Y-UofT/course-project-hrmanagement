package Entities;

public interface EmployeeFactory {
    Employee create(int employeeID, String lastName, String firstName, String address, String phoneNumber, String passText);
    Employee checkLogin(int employeeID, String password);
}
