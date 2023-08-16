package tests;

import Payroll.UseCase.DataValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DataValidator class.
 */
public class PayrollDataValidatorTest {

    /**
     * Test that a valid date is correctly identified as valid
     */
    @Test
    public void testIsValidDate_ValidDate() {
        assertTrue(DataValidator.isValidDate("2023/07/01"));
    }

    /**
     * Test various invalid date formats and values
     */
    @Test
    public void testIsValidDate_InvalidDate() {
        assertFalse(DataValidator.isValidDate("31/12/2023")); // Incorrect format
        assertFalse(DataValidator.isValidDate("12/31/2023")); // Incorrect format
        assertFalse(DataValidator.isValidDate("2023-12-31")); // Incorrect delimiter
        assertFalse(DataValidator.isValidDate("02/30/2023")); // Invalid date
        assertFalse(DataValidator.isValidDate("13/30/2023")); // Invalid date
    }

    /**
     * Test that a valid double value is correctly identified as valid
     */
    @Test
    public void testIsValidDouble_ValidDouble() {
        assertTrue(DataValidator.isValidDouble("123.45"));
    }

    /**
     * Test various invalid double formats and values
     */
    @Test
    public void testIsValidDouble_InvalidDouble() {
        assertFalse(DataValidator.isValidDouble("abc")); // Not a number
        assertFalse(DataValidator.isValidDouble("")); // empty
        assertFalse(DataValidator.isValidDouble(" ")); // empty
        assertFalse(DataValidator.isValidDouble("123.45.67")); // Invalid format
    }
}
