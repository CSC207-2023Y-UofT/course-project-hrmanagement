package Payroll.dao;

import Payroll.entity.TimesheetEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation for reading and loading from timesheet csv file
 */
public class CSVTimesheetDAO implements TimesheetDAO {
    private final String filepath;

    public CSVTimesheetDAO(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads timesheet information to hashmap
     * reads csv file and create TimesheetEntity object for each row
     * return hasmap with employee's full name as key and TimesheetEntity object as value
     * @return timesheetMap hashmap
     */
    public Map<String, TimesheetEntity> loadTimesheetToMap() {
        Map<String, TimesheetEntity> timesheetMap = new HashMap<>();

        //String filePath = PayrollConstant.strPathToTimesheetFile;
        try (BufferedReader br = new BufferedReader(new FileReader(this.filepath))) {
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
}