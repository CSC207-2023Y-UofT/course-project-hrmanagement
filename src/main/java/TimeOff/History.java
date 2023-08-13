package TimeOff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class History {

    public String CreateHistory(){
        File data = new File("data/Database.csv");
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
        return s.toString();
    }

}
