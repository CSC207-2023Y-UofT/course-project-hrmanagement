package Payroll;

// Use Case layer

import java.util.concurrent.TimeUnit;

/**
 * Use Case, the payroll calculation for contract workers
 */
public class ContractPayrollCalculator {

    public long getTotalDays(ContractTimeSheet contractTimeSheet) {
        long daysInMillis = Math.abs(contractTimeSheet.getEndDate().getTime() - contractTimeSheet.getStartDate().getTime());
        long days = TimeUnit.DAYS.convert(daysInMillis, TimeUnit.MILLISECONDS);
        return days;
    }

    /**
     * Return total hours worked
     * @param contractTimeSheet
     * @return total hours worked for one employee within the timeframe
     */
    public double getTotalHoursWorked(ContractTimeSheet contractTimeSheet) {
        return contractTimeSheet.getHoursPerDay() * getTotalDays(contractTimeSheet);
    }

    /**
     * Return total pay given employee and timesheet
     * @param contractWorker
     * @param timeSheet
     * @return total pay amount
     */
    public double calculateTotalPay(ContractWorker contractWorker, ContractTimeSheet timeSheet) {
        return getTotalHoursWorked(timeSheet) * contractWorker.getHourlyWage();
    }

    public double calculateTaxWithholding(double totalPay, ContractWorker contractWorker) {
        return totalPay * contractWorker.getTaxRate();
    }

    public double calculateNetPay(ContractWorker contractWorker, ContractTimeSheet timeSheet) {
        double totalPay = calculateTotalPay(contractWorker, timeSheet);
        double taxWithholding = calculateTaxWithholding(totalPay, contractWorker);
        return totalPay - taxWithholding;
    }
}