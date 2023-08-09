package Schedule;

import Payroll.dao.CSVTimesheetDAO;
import Payroll.dao.TimesheetDAO;
import Payroll.entity.TimesheetEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ScheduleDAO {


    /**
     * Return a list of days in current week
     *
     * @return a list of days (Monday to Sunday) for the current week when called
     */
    public static List<LocalDate> getCurrentWeek(){

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
     * Return a map of employee ids to work dates from payroll database
     * @return a map with keys referring to employees (list of employee id, and full name) and values referring to
     * their work dates (list of start date, and end date)
     *
     * @throws ParseException when Timesheets.csv has invalid date formats
     */
    public static Map<List<String>, List<LocalDate>> getEmployeeDates(String filepath) throws ParseException {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        Map<List<String>, List<LocalDate>> employeeDates = new HashMap<>();

//        String filePath = "./data/Timesheets.csv";
        TimesheetDAO timesheetDAO = new CSVTimesheetDAO(filepath);
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
     * Return a map of time offs based on the time off database
     *
     * @param filePath The path of the time off database
     *
     * @return a map of time offs based on the time off database with keys referring to employee ids and values
     * referring to a list with the start and end date of the time off.
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
