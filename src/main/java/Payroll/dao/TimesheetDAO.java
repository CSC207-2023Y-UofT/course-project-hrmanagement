package Payroll.dao;

import Payroll.entity.TimesheetEntity;

import java.util.Map;

/**
 * Interface to define methods to interact with datasource for timesheet data
 */
public interface TimesheetDAO
{
    Map<String, TimesheetEntity> loadTimesheetToMap();
}
