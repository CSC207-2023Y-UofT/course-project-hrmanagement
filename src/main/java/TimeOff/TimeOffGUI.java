package TimeOff;

import UI.*;

/**
 * The TimeOffGUI class provides the basic user interface structure for the Time Off Request system.
 * This includes the JFrame, Panel, a Back button to exit and return to the respective Employee or Manager Portal Hub
 * as well as Labels and TextFields for inputting Time Off Request data followed by calls to supplement the design
 * with employee or manager specific features respectively.
 */

public class TimeOffGUI {
    MyJFrame frame;
    Panel panel;
    Button back;
    Label start;
    Label end;
    Label name;
    Label id;
    Label hist;
    TextField nametext;
    TextField idtext;
    TextField starting;
    TextField ending;

    public void CreateTimeOffGUI(String type) {

        frame = new MyJFrame();
        frame.createFrame("TimeOff", 300, 300);

        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());

        back = new Button();
        back.createButtonWithIcon(frame.getJFrame(), "< Back to Portal", 1, 1, 200, 20);

        name = new Label();
        name.createLabel(30, 180, 100, 20, frame.getJFrame(), "Name:");

        nametext = new TextField();
        nametext.createTextField(frame.getJFrame(), 65, 180, 90, 20);

        id = new Label();
        id.createLabel(160, 180, 90, 20, frame.getJFrame(), "ID:");

        idtext = new TextField();
        idtext.createTextField(frame.getJFrame(), 175, 180, 90, 20);

        start = new Label();
        start.createLabel(30, 200, 100, 20, frame.getJFrame(), "Start:");

        starting = new TextField();
        starting.createTextField(frame.getJFrame(), 60, 200, 90, 20);

        end = new Label();
        end.createLabel(150, 200, 90, 20, frame.getJFrame(), "End:");

        ending = new TextField();
        ending.createTextField(frame.getJFrame(), 175, 200, 90, 20);

        hist = new Label();
        hist.createLabel(30,20,240,160, frame.getJFrame(), new History().CreateHistory());

        if (type.equals("Employee")) {
            new EmployeeTimeOff().CreateEmployeeTimeOff(frame.getJFrame(), nametext, idtext, starting, ending);
        }
        else if (type.equals("Manager")) {
            new ManagerTimeOff().CreateManagerTimeOff(frame.getJFrame(), nametext, idtext, starting, ending);
        }
    }
}
