package Payroll.dao;

import Payroll.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDAO
{
    Object[][] loadEmployeesTo2DArray();

    EmployeeEntity getEmployeeById(int id);
    List<EmployeeEntity> getAllEmployees();
    void addEmployee(EmployeeEntity employee);
    void updateEmployee(EmployeeEntity employee);
    void deleteEmployee(int id);
}
