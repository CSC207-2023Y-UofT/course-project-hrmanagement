package Payroll.dao;

import Payroll.entity.EmployeeEntity;

import java.util.List;

/**
 * Interface to define methods to interact with datasource for timesheet data
 */
public interface EmployeeDAO
{
    Object[][] loadEmployeesTo2DArray();

    // The following methods are to be implemented.
    EmployeeEntity getEmployeeById(int id);
    List<EmployeeEntity> getAllEmployees();
    void addEmployee(EmployeeEntity employee);
    void updateEmployee(EmployeeEntity employee);
    void deleteEmployee(int id);
}
