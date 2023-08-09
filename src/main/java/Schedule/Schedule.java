package Schedule;

import Payroll.*;
import Payroll.dao.CSVEmployeeDAO;
import Payroll.dao.CSVTimesheetDAO;
import Payroll.dao.TimesheetDAO;
import Payroll.entity.TimesheetEntity;
import TeamStructure.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.*;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.ZoneId;

public class Schedule {

    private LinkedHashMap<LocalDate, List<List<String>>> schedule;
    private List<LocalDate> week;
    private static final String WORK_HOURS = "0900-1700";

    // Constructor for Schedule, called by ScheduleGenerator
    public Schedule() throws ParseException {

        // Find days of current week, assign to week attribute
        this.week = getCurrentWeek();

        LinkedHashMap<LocalDate, List<List<String>>> schedule = new LinkedHashMap<>();

        //Get map of employees to work dates
        Map<List<String>, List<LocalDate>> employeeDates = getEmployeeDates();

        //Get map of employee ids to time offs
        String filePath = "data/Database.txt";
        Map<String, List<LocalDate>> timeOffMap = loadTimeOffsFromTXT(filePath);

        for (LocalDate day : this.week) {

            List<List<String>> workingEmployees = new ArrayList<>();


            for (Map.Entry<List<String>, List<LocalDate>> set : employeeDates.entrySet()) {

                LocalDate startDate = set.getValue().get(0);
                LocalDate endDate = set.getValue().get(1);

                //check if employee's work dates are within day
                boolean inDay = inDay = (startDate.isBefore(day) || startDate.equals(day)) &&
                        (day.isBefore(endDate) || day.isEqual(endDate));

                //check if employee has time off approved
                boolean noTimeOff = true;
                for (Map.Entry<String, List<LocalDate>> timeOffset : timeOffMap.entrySet()) {
                    String employeeId = timeOffset.getKey();
                    LocalDate startOffDate = timeOffset.getValue().get(0);
                    LocalDate endOffDate = timeOffset.getValue().get(1);
                    if (employeeId.equals(set.getKey().get(0)) &&
                    (day.isBefore(endOffDate) || day.equals(endOffDate)) &&
                            (day.isAfter(startOffDate) || day.isEqual(startOffDate))) {
                        noTimeOff = false;
                    }
                }

                //add employee to list if no contradictions
                if (inDay && noTimeOff) {
                    workingEmployees.add(set.getKey());
                }

            }

            schedule.put(day, workingEmployees);
        }

        this.schedule = schedule;
    }

    // Prints text of schedule, called by gui
    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        StringBuilder scheduleText = new StringBuilder();
        int counter = 0;
        for (Map.Entry<LocalDate, List<List<String>>> set : this.schedule.entrySet()) {

            LocalDate day = set.getKey();
            List<List<String>> workingEmployees = set.getValue();

            // Add title for day
            String dayName = day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
            String title = dayName + " (" + this.week.get(counter).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                    "):\n";
            scheduleText.append(title);

            // Add working employees for day
            for (List<String> employee : workingEmployees) {

                String employeeText = employee.get(1) + " (" + employee.get(0) + "): " + WORK_HOURS + "\n";
                scheduleText.append(employeeText);

            }

            scheduleText.append("\n");
            counter++;
        }

        return scheduleText.toString();
    }

    //Get a list of days in current week
    public List<LocalDate> getCurrentWeek(){

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
        return week;
    }

    /**
     * Gets a map of employee IDs mapped to work dates from payroll database.
     * @return Map of Employee IDs, Workdays.
     * @throws ParseException
     */
    public Map<List<String>, List<LocalDate>> getEmployeeDates() throws ParseException {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        Map<List<String>, List<LocalDate>> employeeDates = new HashMap<>();

        String filePath = "./data/Timesheets.csv";
        TimesheetDAO timesheetDAO = new CSVTimesheetDAO(filePath);
        Map<String, TimesheetEntity> timesheetMap = timesheetDAO.loadTimesheetToMap();

        for (HashMap.Entry<String, TimesheetEntity> set : timesheetMap.entrySet()){
            String employeeId  = set.getValue().getEmployeeId();
            String employeeName = set.getValue().getFirstName() + " " + set.getValue().getLastName();
            List<String> employee = new ArrayList<>();
            employee.add(employeeId);
            employee.add(employeeName);

            String startDateStr = set.getValue().getStartDate();
            Date startDate = dateFormatter.parse(startDateStr);
            LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            String endDateStr = set.getValue().getEndDate();
            Date endDate = dateFormatter.parse(endDateStr);
            LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            List<LocalDate> startToEndDates = new ArrayList<>();
            startToEndDates.add(startLocalDate);
            startToEndDates.add(endLocalDate);

            employeeDates.put(employee, startToEndDates);
        }

        return employeeDates;
    }

    /**
     * Gets a map of time off days for employees from the timeOff database.
     * @param filePath: the databse from which to get the information.
     * @return Timeoffs for employees.
     */
    public static Map<String, List<LocalDate>> loadTimeOffsFromTXT(String filePath) {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Map<String, List<LocalDate>> timeOffMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String employeeName = parts[0].trim();
                    String employeeId = parts[1].trim();

                    String startDateStr = parts[2].trim();
                    Date startDate = dateFormatter.parse(startDateStr);
                    LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    String endDateStr = parts[3].trim();
                    Date endDate = dateFormatter.parse(endDateStr);
                    LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    String status = parts[4].trim();

                    List<LocalDate> timeOffDates = new ArrayList<>();
                    timeOffDates.add(startLocalDate);
                    timeOffDates.add(endLocalDate);

                    if (status.equals("Approved")) {
                        timeOffMap.put(employeeId, timeOffDates);
                    }

                } else {
                    System.err.println("Invalid TXT format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading TXT file: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return timeOffMap;
    }

}
