package Payroll.dao;

import Payroll.entity.TimesheetEntity;

import java.util.Map;

public interface TimesheetDAO
{
    Map<String, TimesheetEntity> loadTimesheetToMap();
}
