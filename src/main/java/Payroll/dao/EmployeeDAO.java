package Payroll.dao;

import Payroll.bo.EmployeeBO;

import java.util.List;

public interface EmployeeDAO
{
    Object[][] loadEmployeesTo2DArray();

    EmployeeBO getEmployeeById(int id);
    List<EmployeeBO> getAllEmployees();
    void addEmployee(EmployeeBO employee);
    void updateEmployee(EmployeeBO employee);
    void deleteEmployee(int id);
}
