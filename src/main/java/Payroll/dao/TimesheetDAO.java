package Payroll.dao;

import Payroll.bo.TimesheetBO;

import java.util.Map;

public interface TimesheetDAO
{
    Map<String, TimesheetBO> loadTimesheetToMap();
}
