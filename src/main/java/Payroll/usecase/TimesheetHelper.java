package Payroll.usecase;

import Payroll.entity.EmployeeEntity;
import Payroll.entity.TimesheetEntity;

/**
 * Helper class for creating TimesheetEntity objects based on employee and timesheet data.
 */
public class TimesheetHelper {

    /**
     * Creates a TimesheetEntity object using employee information, timesheet data, and salary.
     * @param employeeEntity The EmployeeEntity for which the timesheet is being created.
     * @param timesheetData  An array containing timesheet data including start and end dates.
     * @param salary The salary associated with the timesheet.
     * @return A TimesheetEntity object with the provided information.
     */
    public static TimesheetEntity createTimesheetEntity(EmployeeEntity employeeEntity,
                                                  String[] timesheetData,
                                                  Double salary){
        TimesheetEntity timesheet = new TimesheetEntity();

        String employeeId = employeeEntity.getEmployeeId();
        String lastName = employeeEntity.getLastName();
        String firstName = employeeEntity.getFirstName();

        String startDate = timesheetData[0];
        String endDate = timesheetData[1];

        timesheet.setEmployeeId(employeeId);
        timesheet.setLastName(lastName);
        timesheet.setFirstName(firstName);
        timesheet.setStartDate(startDate);
        timesheet.setEndDate(endDate);
        timesheet.setSalary(salary);

        return timesheet;
    }

}
