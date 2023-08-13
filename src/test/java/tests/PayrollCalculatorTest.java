package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Payroll.entity.EmployeeEntity;
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
    public void testCalculateSalaryForManager2() {
        String[] timesheetData = {testStartDate, testEndDate, testHoursPerDay, testBonus};
        EmployeeEntity employee = new EmployeeEntity("1", "Johnson", "Bob", "123 King St.", "123456789", PayrollConstant.ROLE_MANAGER);
        double expectedSalary = (7.5 * invokeCalculateTotalDays(testStartDate, testEndDate) * PayrollConstant.MANAGER_HOURLY_RATE) + Double.parseDouble(testBonus);
        double actualSalary = payrollCalculator.calculateSalary(employee, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    /**
     * Test the calculateSalary method for an Employee role.
     */
    @Test
    public void testCalculateSalaryForEmployee2() {
        String[] timesheetData = {testStartDate, testEndDate, testHoursPerDay, testBonus};
        EmployeeEntity employee = new EmployeeEntity("2", "Smith", "John",
                "111 Maple Rd.", "777373737", PayrollConstant.ROLE_EMPLOYEE);
        double expectedSalary = (7.5 * invokeCalculateTotalDays(testStartDate, testEndDate) * PayrollConstant.EMPLOYEE_HOURLY_RATE) + Double.parseDouble(testBonus);
        double actualSalary = payrollCalculator.calculateSalary(employee, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    /**
     * Test the calculateSalary method for an unknown role using default salary
     */
    @Test
    public void testCalculateSalaryForUnknownRole() {
        String[] timesheetData = {testStartDate, testEndDate, testHoursPerDay, testBonus};
        EmployeeEntity employee = new EmployeeEntity("3", "Doe", "Jane", "123 Elm St.", "5551234567", "UnknownRole");
        double expectedSalary = (7.5 * invokeCalculateTotalDays(testStartDate, testEndDate) * PayrollConstant.MINIMUM_HOURLY_RATE) + Double.parseDouble(testBonus);
        double actualSalary = payrollCalculator.calculateSalary(employee, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    /**
     * Test salary for no bonus
     */
    @Test
    public void testCalculateSalaryForZeroBonus() {
        String[] timesheetData = {testStartDate, testEndDate, testHoursPerDay, "0"};
        EmployeeEntity employee = new EmployeeEntity("4", "Brown", "Sandra", "456 Oak St.", "8889990000", PayrollConstant.ROLE_EMPLOYEE);
        double expectedSalary = (7.5 * invokeCalculateTotalDays(testStartDate, testEndDate) * PayrollConstant.EMPLOYEE_HOURLY_RATE);
        double actualSalary = payrollCalculator.calculateSalary(employee, timesheetData);
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
     * Helper method to invoke the private calculateTotalDays method using reflection.
     * This method is used to test the private calculateTotalDays method within the PayrollCalculator class.
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
