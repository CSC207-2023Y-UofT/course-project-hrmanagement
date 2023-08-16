package Payroll.Dao;

import Payroll.entity.EmployeeEntity;
import Payroll.usecase.EmployeeHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for reading and loading employee csv file
 */
public class CSVEmployeeDAO implements EmployeeDAO {
    private final String filepath;

    public CSVEmployeeDAO(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Takes employee hashmap
     * Convert to 2D array of employee information
     * @return 2D array of employee
     */
    @Override
    public Object[][] loadEmployeesTo2DArray()
    {
        Map<String, EmployeeEntity> employeeMap = loadEmployeeToMap();

        // Convert Map to Object[][];
        Object[][] result = new Object[employeeMap.size()][7];
        int rowIndex = 0;

        for (Map.Entry<String, EmployeeEntity> entry : employeeMap.entrySet()) {
            EmployeeEntity employee = entry.getValue();

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

    /**
     * Reads CSV file and parses to extract employee attributes
     * Employee's full name is key in map
     * EmployeeEntity object is the value in map
     * @return hashmap of employee information
     */
    private Map<String, EmployeeEntity> loadEmployeeToMap() {
        Map<String, EmployeeEntity> employeeMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filepath))) {
            // Read the header line to skip it
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) { // if csv format valid
                    // create EmployeeEntity object
                    String employeeId = parts[0].trim();
                    String lastName = parts[1].trim();
                    String firstName = parts[2].trim();
                    String address = parts[3].trim();
                    String phoneNumber = parts[4].trim();
                    String role = parts[5].trim();

                    EmployeeEntity employee = new EmployeeEntity();
                    employee.setEmployeeId(employeeId);
                    employee.setLastName(lastName);
                    employee.setFirstName(firstName);
                    employee.setAddress(address);
                    employee.setPhoneNumber(phoneNumber);
                    employee.setRole(role);

                    String employeeName = EmployeeHelper.getEmployeeName(employee);
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
    public EmployeeEntity getEmployeeById(int id) {
        return null;
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return null;
    }

    @Override
    public void addEmployee(EmployeeEntity employee) {
    }

    @Override
    public void updateEmployee(EmployeeEntity employee) {
    }

    @Override
    public void deleteEmployee(int id) {
    }

}