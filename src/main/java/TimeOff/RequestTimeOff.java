package TimeOff;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

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
