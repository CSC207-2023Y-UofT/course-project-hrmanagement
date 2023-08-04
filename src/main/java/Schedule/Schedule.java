package Schedule;

import TeamStructure.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Schedule {

    private final HashMap<Employee, List<Date>> schedule;
    private final Date day;

    // Constructor for Schedule, called by ScheduleGenerator
    public Schedule(Date date) {
        this.day = date;
        this.schedule = new HashMap<Employee, List<Date>>();
        for (Employee employee : Team.getTeam()){
            List<Date> workingHours = new ArrayList<>();
//            workingHours.add(startDate)
//            workingHours.add(endDate)
            this.schedule.put(employee, workingHours);
        }
    }

    // Prints text of schedule, called by gui
    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        StringBuilder scheduleText = new StringBuilder("Schedule for " + dateFormat.format(this.day) + ":\n");
        for (HashMap.Entry<Employee, List<Date>> set : this.schedule.entrySet()){
            String startTime = timeFormat.format(set.getValue().get(0));
            String endTime = timeFormat.format(set.getValue().get(1));
            String employeeText = set.getKey() + ":" + startTime + " to " + endTime + "\n";
            scheduleText.append(employeeText);
        }

        return scheduleText.toString();
    }

}
