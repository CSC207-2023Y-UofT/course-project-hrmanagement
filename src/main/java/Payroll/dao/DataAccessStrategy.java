package Payroll.dao;

import Payroll.entity.TimesheetEntity;

import java.util.Map;

/**
 * Interface representing a data access strategy. Defines methods to load employee data and timesheet data.
 */
public interface DataAccessStrategy {

    /**
     * Load employee data based on the implemented strategy.
     * @return A 2D array containing the loaded employee data.
     */
    Object[][] loadEmployeeData();

    /**
     * Load timesheet data based on the implemented strategy.
     * @return A map of employee names to TimesheetEntity objects representing timesheet data.
     */
    Map<String, TimesheetEntity> loadTimesheetData();
}
