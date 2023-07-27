package Schedule;

import java.util.Date;

public class ScheduleGenerator {

    // Create schedule, called by Manager
    public Schedule generateSchedule(Date date) {
        return new Schedule(date);
    }
}
