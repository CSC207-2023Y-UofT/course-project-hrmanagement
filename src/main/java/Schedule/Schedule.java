package Schedule;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import TeamStructure.*;

public class Schedule {

    private final HashMap<Employee, LocalTime> schedule;
    private final Date day;

    // Constructor for Schedule, called by ScheduleGenerator
    public Schedule(Date date) {
        this.day = date;
        this.schedule = new HashMap<Employee, LocalTime>();
        // TODO: add working hours / days off for each employee
    }

    // Prints text of schedule
    @Override
    public String toString() {
        String scheduleText = "Schedule for " + this.day + ":\n";
        for (Employee employee : this.schedule){
        // TODO: add working hours / days off for each employee

        }
        return scheduleText;
    }

}
