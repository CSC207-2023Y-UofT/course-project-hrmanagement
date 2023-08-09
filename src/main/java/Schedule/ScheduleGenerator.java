package Schedule;

import java.text.ParseException;
import java.util.Date;

/**
 * Creates a new schedule to be used.
 */
public class ScheduleGenerator {

    public static Schedule generateSchedule() throws ParseException {
        return new Schedule();
    }

}
