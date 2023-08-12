package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Payroll.PayrollConstant;
import Payroll.usecase.PayrollCalculator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Test class for the PayrollCalculator class.
 * This class contains unit tests for various scenarios related to salary calculation
 * and date calculations using the PayrollCalculator class.
 */
public class PayrollCalculatorTest {

    String testStartDate = "2023/07/01";
    String testEndDate = "2023/07/31";
    String testHoursPerDay = "7.5";
    String testBonus = "100.0";


    private PayrollCalculator payrollCalculator;

    /**
     * Initialize the PayrollCalculator instance before each test.
     */
    @BeforeEach
    public void setUp() {
        payrollCalculator = PayrollCalculator.getInstance();
    }

    /**
     * Test the calculateSalary method for a Manager role.
     */
    @Test
    public void testCalculateSalaryForManager() {
        String role = "Manager";
        String[] timesheetData = {testStartDate, testEndDate, testHoursPerDay, testBonus};
        double expectedSalary = 7.5 * 31 * PayrollConstant.MANAGER_HOURLY_RATE + 100;
        double actualSalary = invokeCalculateSalary(role, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    /**
     * Test the calculateSalary method for an Employee role.
     */
    @Test
    public void testCalculateSalaryForEmployee() {
        String role = "Employee";
        String[] timesheetData = {testStartDate, testEndDate, testHoursPerDay, testBonus};
        double expectedSalary = 7.5 * 31 * PayrollConstant.EMPLOYEE_HOURLY_RATE + 100;
        double actualSalary = invokeCalculateSalary(role, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    /**
     * Test the calculateSalary method for an unknown role using default salary
     */
    @Test
    public void testCalculateSalaryForUnknownRole() {
        String role = "UnknownRole";
        String[] timesheetData = {testStartDate, testEndDate, testHoursPerDay, testBonus};
        double expectedSalary = 7.5 * 31 * PayrollConstant.MINIMUM_HOURLY_RATE + 100;
        double actualSalary = invokeCalculateSalary(role, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    /**
     * Test salary for no bonus
     */
    @Test
    public void testCalculateSalaryForZeroBonus() {
        String role = "Employee";
        String[] timesheetData = {testStartDate, testEndDate, testHoursPerDay, "0"};
        double expectedSalary = 7.5 * 31 * PayrollConstant.EMPLOYEE_HOURLY_RATE;
        double actualSalary = invokeCalculateSalary(role, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    /**
     * Test calculateTotalDays method
     */
    @Test
    public void testCalculateTotalDays() {
        double expectedTotalDays = 31;
        double actualTotalDays = invokeCalculateTotalDays(testStartDate, testEndDate);
        assertEquals(expectedTotalDays, actualTotalDays, 0.001);
    }

    /**
     * Helper method to invoke the protected calculateSalary method using reflection.
     * @param role The employee's role.
     * @param timesheetData The timesheet data.
     * @return The calculated salary.
     */
    private double invokeCalculateSalary(String role, String[] timesheetData) {
        try {
            Method method = PayrollCalculator.class.getDeclaredMethod("calculateSalary", String.class, String[].class);
            method.setAccessible(true);
            return (double) method.invoke(payrollCalculator, role, timesheetData);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    /**
     * Helper method to invoke the private calculateTotalDays method using reflection.
     * This method is used to test the private calculateTotalDays method within the PayrollCalculator class.
     *
     * @param startDateStr The start date in the format "MM/dd/yyyy".
     * @param endDateStr The end date in the format "MM/dd/yyyy".
     * @return The calculated total days between the start and end dates.
     */
    private double invokeCalculateTotalDays(String startDateStr, String endDateStr) {
        try {
            Method method = PayrollCalculator.class.getDeclaredMethod("calculateTotalDays", String.class, String.class);
            method.setAccessible(true);
            return (double) method.invoke(payrollCalculator, startDateStr, endDateStr);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
