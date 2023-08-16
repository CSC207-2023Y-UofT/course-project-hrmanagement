package Payroll.Dao;

import Payroll.entity.TimesheetEntity;

import java.util.Map;

/**
 * Interface to define methods to interact with datasource for timesheet data
 */
public interface TimesheetDAO
{
    Map<String, TimesheetEntity> loadTimesheetToMap();

    void addTimesheet(TimesheetEntity timesheet);

    TimesheetEntity getTimesheetById(String employeeId);

    void updateTimesheet(TimesheetEntity timesheet);

    void saveTimesheet(TimesheetEntity timesheet);


}
