package tests;

import Schedule.ScheduleDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test class for the ScheduleDAO class.
 * This class contains unit tests for the different methods in ScheduleDAO and checks for different scenarios.
 */
public class ScheduleTest {

    private static final String TEST_TIMESHEET = "src/test/java/testsData/TimesheetsTest.csv";
    private static final String TEST_TIME_OFF = "src/test/java/testsData/DatabaseTest.txt";
    private Map<List<String>, List<LocalDate>> employeeDates;
    private Map<String, List<LocalDate>> timeOffs;

    @BeforeEach
    public void setUp() throws ParseException {
        employeeDates = ScheduleDAO.getEmployeeDates(TEST_TIMESHEET);
        timeOffs = ScheduleDAO.loadTimeOffsFromTXT(TEST_TIME_OFF);
    }

    @Test
    public void testGetEmployeeDates(){

        List<String> employee = new ArrayList<>();
        employee.add("1");
        employee.add("Bob Johnson");
        List<LocalDate> dates = new ArrayList<>();
        LocalDate startDate = LocalDate.of(2023, 7, 1);
        LocalDate endDate = LocalDate.of(2023, 8, 10);
        dates.add(startDate);
        dates.add(endDate);
        // assertEquals(employeeDates.get(employee), dates);
    }

    @Test
    public void testLoadTimeOffsFromTXT(){
        List<LocalDate> dates = new ArrayList<>();
        LocalDate startDate = LocalDate.of(2023, 8, 12);
        LocalDate endDate = LocalDate.of(2023, 8, 13);
        dates.add(startDate);
        dates.add(endDate);
        assertEquals(timeOffs.get("3"), dates);

    }

}
