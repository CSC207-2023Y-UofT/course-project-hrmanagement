package UI;

import GUILogin.EmployeeForm;
import GUILogin.EmployeeSignIn;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EmployeeTimeOff {
    MyJFrame frame;
    Panel panel;
    Button request;
    Button back;
    Label hist;
    Label start;
    Label end;
    Label name;
    Label id;
    Label dateFormat;
    TextField nametext;
    TextField idtext;
    TextField starting;
    TextField ending;

    public void CreateEmployeeTimeOff(){
        frame = new MyJFrame();
        frame.createFrame("TimeOff", 300, 300);

        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());

        request = new Button();
        request.createButtonWithIcon(frame.getJFrame(), "Request", 50, 225, 200, 20);

        back = new Button();
        back.createButtonWithIcon(frame.getJFrame(), "< Back to Employee Portal", 1, 1, 200, 20);

        dateFormat = new Label();
        dateFormat.createLabel(30,150,200,20, frame.getJFrame(),
                "Date in dd/MM/yyyy:");

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

        request.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter out = new FileWriter("data/Database.txt", true);
                    out.write(nametext.textField.getText() + "," + idtext.textField.getText() + ","
                            + starting.textField.getText() + "," + ending.textField.getText() + ",Pending" + "\n");
                    out.close();
                    frame.getJFrame().dispose();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        back.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getJFrame().dispose();
            }
        });

    }
}