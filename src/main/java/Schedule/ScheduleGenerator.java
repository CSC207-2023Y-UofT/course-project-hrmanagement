package Schedule;

import java.text.ParseException;
import java.util.Date;

/**
 * Adapter class to generate Schedule. Called by ManagerPortalBoxGUI, calls and generates
 * Schedule entity class
 */
public class ScheduleGenerator {

    /**
     * Calls and generates Schedule entity class
     *
     * @return a Schedule entity based on current day and employee data
     *
     * @throws ParseException when invalid date format used in a database
     */
    public static Schedule generateSchedule() throws ParseException {
        return new Schedule();
    }

}
