package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * The ManagerTimeOff class allows a user to choose to approve or deny Time Off requests.
 * It creates a JFrame displaying four text fields for the name, id and time off start and end dates.
 * The user can click enter the information and click the approve or button to update the request.
 * The JFrame displays a label showing the latest time off requests.
 */

public class ManagerTimeOff {
    MyJFrame frame;
    Panel panel;
    Button approve;
    Button deny;
    Label hist;
    Label start;
    Label end;
    Label name;
    Label id;
    TextField nametext;
    TextField idtext;
    TextField starting;
    TextField ending;
    String[][] database;

    public void CreateManagerTimeOff(){

        frame = new MyJFrame();
        frame.createFrame("TimeOff", 300, 300);

        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());

        approve = new Button();
        approve.createButtonWithIcon(frame.getJFrame(), "Approve", 50, 225, 100, 20);

        deny = new Button();
        deny.createButtonWithIcon(frame.getJFrame(), "Deny", 150, 225, 100, 20);

        name = new Label();
        name.createLabel(30,180,100,20, frame.getJFrame(), "Name:");

        nametext = new TextField();
        nametext.createTextField(frame.getJFrame(), 65,180,90,20);

        id = new Label();
        id.createLabel(160,180,90,20, frame.getJFrame(), "ID:");

        idtext = new TextField();
        idtext.createTextField(frame.getJFrame(), 175,180,90,20);

        start = new Label();
        start.createLabel(30,200,100,20, frame.getJFrame(), "Start:");

        starting = new TextField();
        starting.createTextField(frame.getJFrame(), 60,200,90,20);

        end = new Label();
        end.createLabel(150,200,90,20, frame.getJFrame(), "End:");

        ending = new TextField();
        ending.createTextField(frame.getJFrame(), 175,200,90,20);

        hist = new Label();
        File data = new File("data/Database.txt");
        StringBuilder s = new StringBuilder("<html>");
        try {
            BufferedReader in = new BufferedReader(new FileReader(data));
            String temp = in.readLine();
            int count = 0;
            while (temp != null){
                count++;
                temp = in.readLine();
            }
            in.close();
            in = new BufferedReader(new FileReader(data));
            temp = in.readLine();
            while (count >= 10){
                temp = in.readLine();
                count--;
            }
            while (temp != null){
                s.append(temp.replace(",", " - ")).append("<br>");
                temp = in.readLine();
                count--;
            }
        } catch (IOException exception){
            exception.printStackTrace();
        }
        hist.createLabel(30,20,240,160, frame.getJFrame(), s.toString());

        approve.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader in = new BufferedReader(new FileReader(data));
                    int count = 0;
                    String temp = in.readLine();
                    while (temp != null){
                        count++;
                        temp = in.readLine();
                    }
                    in.close();
                    database = new String[count][5];
                    in = new BufferedReader(new FileReader(data));
                    int counter = 0;
                    temp = in.readLine();
                    while (counter < count){
                        database[counter] = temp.split(",");
                        if (database[counter][0].equals(nametext.textField.getText())
                                && database[counter][1].equals(idtext.textField.getText())
                                && database[counter][2].equals(starting.textField.getText())
                                && database[counter][3].equals(ending.textField.getText())){
                            database[counter][4] = "Approved";
                        }
                        temp = in.readLine();
                        counter++;
                    }
                    in.close();
                    FileWriter out = new FileWriter("data/Database.txt");
                    out.write("");
                    out.close();
                    out = new FileWriter("data/Database.txt", true);
                    int var = 0;
                    while (var < counter){
                        out.write(database[var][0] + "," + database[var][1] + "," + database[var][2]
                                + "," + database[var][3] + "," + database[var][4] + "\n");
                        var++;
                    }
                    out.close();
                    frame.getJFrame().dispose();

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        deny.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader in = new BufferedReader(new FileReader(data));
                    int count = 0;
                    String temp = in.readLine();
                    while (temp != null){
                        count++;
                        temp = in.readLine();
                    }
                    in.close();
                    database = new String[count][5];
                    in = new BufferedReader(new FileReader(data));
                    int counter = 0;
                    temp = in.readLine();
                    while (counter < count){
                        database[counter] = temp.split(",");
                        if (database[counter][0].equals(nametext.textField.getText())
                                && database[counter][1].equals(idtext.textField.getText())
                                && database[counter][2].equals(starting.textField.getText())
                                && database[counter][3].equals(ending.textField.getText())){
                            database[counter][4] = "Denied";
                        }
                        temp = in.readLine();
                        counter++;
                    }
                    in.close();
                    FileWriter out = new FileWriter("data/Database.txt");
                    out.write("");
                    out.close();
                    out = new FileWriter("data/Database.txt", true);
                    int var = 0;
                    while (var < counter){
                        out.write(database[var][0] + "," + database[var][1] + "," + database[var][2]
                                + "," + database[var][3] + "," + database[var][4] + "\n");
                        var++;
                    }
                    out.close();
                    frame.getJFrame().dispose();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            }
        });

    }
}