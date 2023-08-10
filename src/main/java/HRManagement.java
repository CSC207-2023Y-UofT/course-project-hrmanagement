import Entities.CommonEmployeeFactory;
import Entities.EmployeeFactory;
import UI.PositionBox;

/**
 * The main method of the HRManagement application.
 */
public class HRManagement {
    public static void main(String[] args) {
        final String URL = "jdbc:mysql://localhost:3306/employees";
        final String USERNAME = "root";
        final String PASSWORD = "password";
        EmployeeFactory employeeFactory = new CommonEmployeeFactory();

        /*Choose Position Portal*/
        PositionBox positionBox = new PositionBox();
        positionBox.createChoosePositionBox(URL, USERNAME, PASSWORD, employeeFactory);

    }

}
