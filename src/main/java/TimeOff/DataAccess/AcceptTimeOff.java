package TimeOff.DataAccess;

import TimeOff.GUI.TimeOffGUI;
import TimeOff.Entities.TimeOffEntity;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The AcceptTimeOff class provides a way to search for a request in the database whose details match the given
 * Time Off Request. Note that this is possible only as the entity is representative of a collection of information.
 * The request's status is then changed to being approved.
 */

public class AcceptTimeOff {
    String[][] database;

    public void Approve(JFrame frame, TimeOffEntity request, String db){
        try {
            BufferedReader in = new BufferedReader(new FileReader(db));
            int count = 0;
            String temp = in.readLine();
            while (temp != null){
                count++;
                temp = in.readLine();
            }
            in.close();
            database = new String[count][5];
            in = new BufferedReader(new FileReader(db));
            int counter = 0;
            temp = in.readLine();
            while (counter < count){
                database[counter] = temp.split(",");
                if (database[counter][0].equals(request.name)
                        && database[counter][1].equals(request.id)
                        && database[counter][2].equals(request.start)
                        && database[counter][3].equals(request.end)){
                    database[counter][4] = "Approved";
                }
                temp = in.readLine();
                counter++;
            }
            in.close();
            FileWriter out = new FileWriter(db);
            out.write("");
            out.close();
            out = new FileWriter(db, true);
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
