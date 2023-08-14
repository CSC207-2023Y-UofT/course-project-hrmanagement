package tests;

import TimeOff.GUI.*;
import TimeOff.DataAccess.*;
import TimeOff.Entities.*;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TimeOffTest provides a set of tests to ensure TimeOff Functionality works as intended on both employee and
 * manager sides. Ensure to run the test with a database that is empty when the set of tests is run! This set of tests
 * uses the connector between the GUI and functional logic to ensure the correct end result indicating a fully
 * operational data flow pipeline.
 */

public class TimeOffTest {
    public static void main(String[] args) {
        TimeOffEntity request = new TimeOffEntity();
        String db = "src/test/java/testsData/DatabaseTest.txt";
        JFrame frame = new JFrame();
        String name = "Employee", id = "1", start = "01/01/01", end = "02/01/01";

        request.CreateTimeOffEntity(name, id, start, end, "Pending");
        new RequestTimeOff().Submit(frame, request, db);
        String test = new History().CreateHistory(db);
        assertEquals(test, "<html>Employee - 1 - 01/01/01 - 02/01/01 - Pending<br>");


        frame = new JFrame();
        request.CreateTimeOffEntity(name, id, start, end, "Approved");
        new AcceptTimeOff().Approve(frame, request, db);
        test = new History().CreateHistory(db);
        assertEquals(test, "<html>Employee - 1 - 01/01/01 - 02/01/01 - Approved<br>");

        frame = new JFrame();
        request.CreateTimeOffEntity(name, id, start, end, "Denied");
        new RejectTimeOff().Deny(frame, request, db);
        test = new History().CreateHistory(db);
        assertEquals(test, "<html>Employee - 1 - 01/01/01 - 02/01/01 - Denied<br>");
    }
}
