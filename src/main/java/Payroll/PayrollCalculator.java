package Payroll;

// Use Case layer

import java.util.concurrent.TimeUnit;
import TeamStructure.*;

/**
 * Use Case, the payroll calculation for contract workers
 */
public class PayrollCalculator {

    public long getTotalDays(TimeSheet timeSheet) {
        long daysInMillis = Math.abs(timeSheet.getEndDate().getTime() - timeSheet.getStartDate().getTime());
        long days = TimeUnit.DAYS.convert(daysInMillis, TimeUnit.MILLISECONDS);
        return days;
    }

    /**
     * Return total hours worked
     * @param timeSheet
     * @return total hours worked for one employee within the timeframe
     */
    public double getTotalHoursWorked(TimeSheet timeSheet) {
        return timeSheet.getHoursPerDay() * getTotalDays(timeSheet);
    }

    /**
     * Return total pay given employee and timesheet
     * @param employee
     * @param timeSheet
     * @return total pay amount
     */
    public double calculateTotalPay(Employee employee, TimeSheet timeSheet) {
        return getTotalHoursWorked(timeSheet) * employee.getWage();
    }

    // possible method for extension: tax calculation
}