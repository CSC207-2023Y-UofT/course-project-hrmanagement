package Schedule.UseCase;

import Schedule.Entities.Schedule;

import java.text.ParseException;

/**
 * Creates a new schedule to be used.
 */
public class ScheduleGenerator {

    public static Schedule generateSchedule() throws ParseException {
        return new Schedule();
    }

}
