package Payroll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVDataReader {

    public static Map<String, EmployeeEntity> loadEmployeesFromCSV(String filePath) {
        Map<String, EmployeeEntity> employeeMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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

                    EmployeeEntity employee = new EmployeeEntity();
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

    public static Object[][] convertEmployeeMapTo2DArray(Map<String, EmployeeEntity> employeeMap) {
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

    public static Map<String, TimesheetEntity> loadTimesheetsFromCSV(String filePath) {
        Map<String, TimesheetEntity> timesheetMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the header line to skip it
            String headerLine = br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String employeeId = parts[0].trim();
                    String lastName = parts[1].trim();
                    String firstName = parts[2].trim();
                    String startDate = parts[3].trim();
                    String endDate = parts[4].trim();

                    TimesheetEntity timesheet = new TimesheetEntity();
                    timesheet.setEmployeeId(employeeId);
                    timesheet.setFirstName(firstName);
                    timesheet.setLastName(lastName);

                    timesheet.setStartDate(startDate);
                    timesheet.setEndDate(endDate);

                    String employeeName = firstName + " " + lastName;
                    timesheetMap.put(employeeName, timesheet);
                } else {
                    System.err.println("Invalid CSV format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return timesheetMap;
    }

    // This is a test.
    public static void main(String[] args) {
        String filePath = "./data/Employees.csv";
        Map<String, EmployeeEntity> employeeMap = loadEmployeesFromCSV(filePath);

        // Display the contents of the HashMap
        for (Map.Entry<String, EmployeeEntity> entry : employeeMap.entrySet()) {
            String employeeId = entry.getKey();
            EmployeeEntity employee = entry.getValue();
            System.out.println("Employee ID: " + employeeId);
            System.out.println("Last Name: " + employee.getLastName());
            System.out.println("First Name: " + employee.getFirstName());
            System.out.println("Address: " + employee.getAddress());
            System.out.println("Phone Number: " + employee.getPhoneNumber());
            System.out.println("Role: " + employee.getRole());
            System.out.println();
        }
    }

}