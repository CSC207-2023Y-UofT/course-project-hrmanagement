package Payroll.usecase;

// Use Case layer

import Payroll.PayrollConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Use Case, the payroll calculation for contract workers
 * Implemented Singleton design pattern to provide single instance of payroll calculator
 */
public class PayrollCalculator {
    private static PayrollCalculator instance;
    // Private constructor to prevent instantiation from other classes
    private PayrollCalculator() {}

    /**
     * public method to return a PayrollCalculator instance
     * @return instance of PayrollCalculator
     */
    public static PayrollCalculator getInstance() {
        // Lazy initialization - create the instance only when needed
        if (instance == null) {
            synchronized (PayrollCalculator.class) {
                if (instance == null) {
                    instance = new PayrollCalculator();
                }
            }
        }
        return instance;
    }

    /**
     * Salary calculation, (hoursPerDay * days * rate) + bonus
     * @param role employee or manager
     * @param timesheetData array of timesheet data
     * @return total salary
     */
    public double calculateSalary(String role, String[] timesheetData) {
        // Implement the salary calculation logic based on the role and additional data
        // For simplicity, we'll use some fixed values for demonstration purposes.
        double baseSalary = 0;         // Because it is hourly based salary.
        double hoursPerDay = Double.parseDouble(timesheetData[2]); // hours per day
        double bonus = Double.parseDouble(timesheetData[3]); // bonus
        double totalDays = calculateTotalDays(timesheetData[0], timesheetData[1]); // start end, end date

        // Determine the hourly rate by role.
        double hourlyRate = calculateHourlyRate(role);
        return baseSalary + (hoursPerDay * totalDays * hourlyRate) + bonus; // calculation
    }

    /**
     * Returns hourly rate based on role
     * @param role person's role: manager or employee
     * @return hourly rate double
     */
    private double calculateHourlyRate(String role) {
        double hourlyRate = 0;
        if (role.equalsIgnoreCase(PayrollConstant.ROLE_MANAGER))
            hourlyRate = PayrollConstant.MANAGER_HOURLY_RATE;
        else if (role.equalsIgnoreCase(PayrollConstant.ROLE_EMPLOYEE))
            hourlyRate = PayrollConstant.EMPLOYEE_HOURLY_RATE;
        else
            hourlyRate = PayrollConstant.MINIMUM_HOURLY_RATE;

        return hourlyRate;
    }

    /**
     * Calculate total days between start date and end date
     * @param startDateStr start date
     * @param endDateStr end date
     * @return number of days
     */
    private double calculateTotalDays(String startDateStr, String endDateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            long diff = endDate.getTime() - startDate.getTime();
            return (double) (diff / (1000 * 60 * 60 * 24)) + 1; // Adding 1 day to include both start and end days
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

}