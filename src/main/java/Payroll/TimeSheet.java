package Payroll;

import java.util.Date;

// Entity

public class TimeSheet {
    private Date startDate;
    private Date endDate;
    private double hoursPerDay;

    /**
     * Constructor
     * @param startDate
     * @param endDate
     * @param hoursPerDay
     */
    public TimeSheet(Date startDate, Date endDate, double hoursPerDay) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.hoursPerDay = hoursPerDay;
    }

    // Getters and setters
    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getHoursPerDay() {
        return hoursPerDay;
    }
}