package Payroll.helper;

import Payroll.entity.EmployeeEntity;

import javax.swing.*;

/**
 * A helper class to assist with operations related to employee entities
 */
public class EmployeeHelper {
    /**
     * Build an employee entity using selected row of employee list in GUI.
     * @param employeeTable - an instance of JTable containing the employees.
     * @param selectedRow - an int representing the row that is selected for payroll calculation.
     * @return selectedEmployee - an employee entity representing the selected employee.
     */
    public static EmployeeEntity buildEmployeeEntity(JTable employeeTable, int selectedRow)
    {
        EmployeeEntity selectedEmployee = new EmployeeEntity();

        String employeeId = (String) employeeTable.getValueAt(selectedRow, 1);
        String lastName = (String) employeeTable.getValueAt(selectedRow, 2);
        String firstName = (String) employeeTable.getValueAt(selectedRow, 3);
        String role = (String) employeeTable.getValueAt(selectedRow, 6);

        selectedEmployee.setEmployeeId(employeeId);
        selectedEmployee.setFirstName(firstName);
        selectedEmployee.setLastName(lastName);
        selectedEmployee.setRole(role);

        return selectedEmployee;
    }

    /**
     * Get the full name of an EmployeeEntity.
     * @param employee The EmployeeEntity for which to get the full name.
     * @return The full name of the employee in the format "FirstName LastName".
     */
    public static String getEmployeeName(EmployeeEntity employee)
    {
        String employeeName;

        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        employeeName = firstName + " " + lastName;

        return employeeName;
    }

    /**
     * Return employee's full name in string given first name and last name
     * Helper method to ensure proper and consistent formatting
     * @param firstName first name
     * @param lastName last name
     * @return The full name of the employee in the format "FirstName LastName".
     */
    public static String getEmployeeName(String firstName, String lastName)
    {
        return firstName + " " + lastName;
    }

}
