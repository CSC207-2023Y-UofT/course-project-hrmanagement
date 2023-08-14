package TimeOff.DataAccess;

import TimeOff.GUI.TimeOffGUI;
import TimeOff.Entities.TimeOffEntity;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The RequestTimeOff class provides a way to submit a Time Off Request to the database containing the data given by
 * the user regarding name, id, start and end date. The request is initially submitted with a status of pending.
 */


public class RequestTimeOff {

    public void Submit(JFrame frame, TimeOffEntity request, String db){
        try {
            FileWriter out = new FileWriter(db, true);
            out.write(request + "\n");
            out.close();
            new TimeOffGUI().CreateTimeOffGUI("Employee");
            frame.dispose();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
