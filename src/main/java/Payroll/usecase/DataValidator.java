package Payroll.usecase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Validate user input data in timesheet
 */
public class DataValidator {

    /**
     * Checks valid date format
     * @param dateStr data input in string format
     * @return boolean true if data is valid
     */
    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateStr);
            return dateStr.length() == 10;

        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Parses date string into Data object
     * @param dateStr data string
     * @return Data object if parsing successful, null if parsing fails
     */
    public static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Checks valid data format for double (hours per day and bonus)
     * @param input string input
     * @return true if input can be parsed into a double, false if parsing fails
     */
    public static boolean isValidDouble(String input) {
        try {
            // Try parsing the input string into a double
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
