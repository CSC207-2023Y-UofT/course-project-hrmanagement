package tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Payroll.PayrollConstant;
import Payroll.usecase.PayrollCalculator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PayrollCalculatorTest {

    private PayrollCalculator payrollCalculator;

    @BeforeEach
    public void setUp() {
        payrollCalculator = PayrollCalculator.getInstance();
    }

    @Test
    public void testCalculateSalaryForManager() {
        String role = "Manager";
        String[] timesheetData = {"07/01/2023", "07/31/2023", "8", "500"};
        double expectedSalary = 8 * 31 * PayrollConstant.MANAGER_HOURLY_RATE + 500;
        double actualSalary = invokeCalculateSalary(role, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    @Test
    public void testCalculateSalaryForEmployee() {
        String role = "Employee";
        String[] timesheetData = {"07/01/2023", "07/31/2023", "8", "300"};
        double expectedSalary = 8 * 31 * PayrollConstant.EMPLOYEE_HOURLY_RATE + 300;
        double actualSalary = invokeCalculateSalary(role, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    @Test
    public void testCalculateSalaryForUnknownRole() {
        String role = "UnknownRole";
        String[] timesheetData = {"07/01/2023", "07/31/2023", "8", "0"};
        double expectedSalary = 8 * 31 * PayrollConstant.MINIMUM_HOURLY_RATE;
        double actualSalary = invokeCalculateSalary(role, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    @Test
    public void testCalculateSalaryForZeroBonus() {
        String role = "Employee";
        String[] timesheetData = {"07/01/2023", "07/31/2023", "8", "0"};
        double expectedSalary = 8 * 31 * PayrollConstant.EMPLOYEE_HOURLY_RATE;
        double actualSalary = invokeCalculateSalary(role, timesheetData);
        assertEquals(expectedSalary, actualSalary, 0.001);
    }

    @Test
    public void testCalculateTotalDays() {
        String startDateStr = "07/01/2023";
        String endDateStr = "07/31/2023";
        double expectedTotalDays = 31;
        double actualTotalDays = invokeCalculateTotalDays(startDateStr, endDateStr);
        assertEquals(expectedTotalDays, actualTotalDays, 0.001);
    }

    // Helper method to invoke the protected calculateSalary method using reflection
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

    // Helper method to invoke the private calculateTotalDays method using reflection
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
