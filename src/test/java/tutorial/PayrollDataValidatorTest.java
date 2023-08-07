package tutorial;

import Payroll.usecase.DataValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PayrollDataValidatorTest {

    @Test
    public void testIsValidDate_ValidDate() {
        assertTrue(DataValidator.isValidDate("12/31/2023"));
    }

    @Test
    public void testIsValidDate_InvalidDate() {
        assertFalse(DataValidator.isValidDate("31/12/2023")); // Incorrect format
        assertFalse(DataValidator.isValidDate("2023-12-31")); // Incorrect delimiter
        assertFalse(DataValidator.isValidDate("02/30/2023")); // Invalid date
    }

    @Test
    public void testIsValidDouble_ValidDouble() {
        assertTrue(DataValidator.isValidDouble("123.45"));
    }

    @Test
    public void testIsValidDouble_InvalidDouble() {
        assertFalse(DataValidator.isValidDouble("abc")); // Not a number
        assertFalse(DataValidator.isValidDouble("123.45.67")); // Invalid format
    }
}
