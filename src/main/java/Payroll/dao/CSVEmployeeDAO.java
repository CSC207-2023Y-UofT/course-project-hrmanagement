package Payroll.dao;

import Payroll.bo.EmployeeBO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVEmployeeDAO implements EmployeeDAO {
    private final String filepath;

    public CSVEmployeeDAO(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public Object[][] loadEmployeesTo2DArray()
    {
        Map<String, EmployeeBO> employeeMap = loadEmployeeToMap();

        // Convert Map to Object[][];
        Object[][] result = new Object[employeeMap.size()][7];
        int rowIndex = 0;

        for (Map.Entry<String, EmployeeBO> entry : employeeMap.entrySet()) {
            EmployeeBO employee = entry.getValue();

            result[rowIndex][0] = false;     // The is for the Select column.
            result[rowIndex][1] = employee.getEmployeeId();
            result[rowIndex][2] = employee.getLastName();
            result[rowIndex][3] = employee.getFirstName();
            result[rowIndex][4] = employee.getAddress();
            result[rowIndex][5] = employee.getPhoneNumber();
            result[rowIndex][6] = employee.getRole();

            rowIndex++;
        }

        return result;

    }

    private Map<String, EmployeeBO> loadEmployeeToMap() {
        Map<String, EmployeeBO> employeeMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filepath))) {
            // Read the header line to skip it
            String headerLine = br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String employeeId = parts[0].trim();
                    String lastName = parts[1].trim();
                    String firstName = parts[2].trim();
                    String address = parts[3].trim();
                    String phoneNumber = parts[4].trim();
                    String role = parts[5].trim();

                    EmployeeBO employee = new EmployeeBO();
                    employee.setEmployeeId(employeeId);
                    employee.setLastName(lastName);
                    employee.setFirstName(firstName);
                    employee.setAddress(address);
                    employee.setPhoneNumber(phoneNumber);
                    employee.setRole(role);

                    String employeeName = firstName + " " + lastName;
                    employeeMap.put(employeeName, employee);
                } else {
                    System.err.println("Invalid CSV format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return employeeMap;
    }

    @Override
    public EmployeeBO getEmployeeById(int id) {
        return null;
    }

    @Override
    public List<EmployeeBO> getAllEmployees() {
        return null;
    }

    @Override
    public void addEmployee(EmployeeBO employee) {
    }

    @Override
    public void updateEmployee(EmployeeBO employee) {
    }

    @Override
    public void deleteEmployee(int id) {
    }

}