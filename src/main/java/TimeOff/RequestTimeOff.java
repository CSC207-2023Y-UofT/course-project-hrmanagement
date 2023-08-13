package TimeOff;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The RequestTimeOff class provides a way to submit a Time Off Request to the database containing the data given by
 * the user regarding name, id, start and end date. The request is initially submitted with a status of pending.
 */


public class RequestTimeOff {

    public void Submit(JFrame frame, TimeOffEntity request){
        try {
            FileWriter out = new FileWriter("data/Database.csv", true);
            out.write(request + "\n");
            out.close();
            new TimeOffGUI().CreateTimeOffGUI("Employee");
            frame.dispose();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
