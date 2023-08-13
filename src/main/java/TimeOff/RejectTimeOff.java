package TimeOff;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RejectTimeOff {
    String[][] database;

    public void Deny(JFrame frame, TimeOffEntity request){
        try {
            BufferedReader in = new BufferedReader(new FileReader("data/Database.csv"));
            int count = 0;
            String temp = in.readLine();
            while (temp != null){
                count++;
                temp = in.readLine();
            }
            in.close();
            database = new String[count][5];
            in = new BufferedReader(new FileReader("data/Database.csv"));
            int counter = 0;
            temp = in.readLine();
            while (counter < count){
                database[counter] = temp.split(",");
                if (database[counter][0].equals(request.name)
                        && database[counter][1].equals(request.id)
                        && database[counter][2].equals(request.start)
                        && database[counter][3].equals(request.end)){
                    database[counter][4] = "Denied";
                }
                temp = in.readLine();
                counter++;
            }
            in.close();
            FileWriter out = new FileWriter("data/Database.csv");
            out.write("");
            out.close();
            out = new FileWriter("data/Database.csv", true);
            int var = 0;
            while (var < counter){
                out.write(database[var][0] + "," + database[var][1] + "," + database[var][2]
                        + "," + database[var][3] + "," + database[var][4] + "\n");
                var++;
            }
            out.close();
            new TimeOffGUI().CreateTimeOffGUI("Manager");
            frame.dispose();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
