package Schedule;

import TeamStructure.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.TextStyle;
import java.util.*;
import java.time.LocalDate;
import java.time.DayOfWeek;


public class Schedule {

    private HashMap<Employee, List<Date>> schedule;
    private List<LocalDate> week;

    // Constructor for Schedule, called by ScheduleGenerator
    public Schedule() {

        // Find days of current week
        LocalDate monday = LocalDate.now();
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
            monday = monday.minusDays(1);
        }
        LocalDate tuesday = monday.plusDays(1);
        LocalDate wednesday = tuesday.plusDays(1);
        LocalDate thursday = wednesday.plusDays(1);
        LocalDate friday = thursday.plusDays(1);
        LocalDate saturday = friday.plusDays(1);
        LocalDate sunday = saturday.plusDays(1);

        List<LocalDate> week = new ArrayList<LocalDate>();
        week.add(monday);
        week.add(tuesday);
        week.add(wednesday);
        week.add(thursday);
        week.add(friday);
        week.add(saturday);
        week.add(sunday);
        this.week = week;


//        this.schedule = new HashMap<Employee, List<Date>>();
//        for (Employee employee : Team.getEmployees()){
//            List<Date> workingHours = new ArrayList<>();
//            workingHours.add(startDate)
//            workingHours.add(endDate)
//            this.schedule.put(employee, workingHours);
//        }
    }

    // Prints text of schedule, called by gui
    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        StringBuilder scheduleText = new StringBuilder();
        for (LocalDate day : this.week) {
            // Add title for day
            String dayName = day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
            String title = dayName + " (" + dateFormat.format(this.week.get(0)) + "):\n";
            scheduleText.append(title);
        }

//        StringBuilder scheduleText = new StringBuilder("Monday (" + dateFormat.format(this.week.get(0)) + "):\n");
//        for (HashMap.Entry<Employee, List<Date>> set : this.schedule.entrySet()){
//            String startTime = timeFormat.format(set.getValue().get(0));
//            String endTime = timeFormat.format(set.getValue().get(1));
//            String employeeText = set.getKey() + ":" + startTime + " to " + endTime + "\n";
//            scheduleText.append(employeeText);
//        }
//
        return scheduleText.toString();
    }

}
