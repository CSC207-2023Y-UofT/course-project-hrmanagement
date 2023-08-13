package UI;

import Controllers.EmployeeLoginController;
import Controllers.EmployeeLoginResponseFormatter;
import Controllers.EmployeeRegisterController;
import Controllers.EmployeeRegisterResponseFormatter;
import Database.EmployeeLoginDBGateway;
import Database.EmployeeRegisterDBGateway;
import Database.FileEmployee;
import Database.FileLoginEmployee;
import Entities.EmployeeFactory;
import Database.EmployeeLoginDBGateway;
import UseCase.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EmployeeBox {
    Button signUp;
    Button login;
    MyJFrame frame;
    Panel panel;

    /**
     * This method creates all UI components of EmployeeBox
     */
    public void createEmployeeBox(String url, String userName, String password, EmployeeFactory employeeFactory){
        frame = new MyJFrame();
        frame.createFrame("Employees", 300, 300);

        panel = new Panel();
        panel.createPanel(20, 30, 20, 30, "GridLayout", 0, 1, 10, 10, frame.getJFrame());

        signUp = new Button();
        signUp.createButtonWithIcon(frame.getJFrame(), "SignUp", 50, 50, 200, 50);

        login = new Button();
        login.createButtonWithIcon(frame.getJFrame(), "Login", 50, 120, 200, 50);

        // action when signUp button is clicked
        signUp.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EmployeeRegisterDBGateway employeeSignUp;
                try{
                    employeeSignUp = new FileEmployee(url, userName, password);
                }catch(SQLException e) {
                    e.printStackTrace();
                    employeeSignUp = null;
                }

                EmployeeRegisterPresenter presenter = new EmployeeRegisterResponseFormatter();
                EmployeeRegisterInputBoundary interactor = new EmployeeRegisterInteractor(employeeSignUp, presenter, employeeFactory);
                EmployeeRegisterController employeeRegisterController = new EmployeeRegisterController(interactor);
                EmployeeSignUpForm employeeSignUpForm = new EmployeeSignUpForm(employeeRegisterController);

            }
        });

        // action when login button is clicked
        login.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EmployeeLoginDBGateway employeeLoginDBGateway;
                try {
                    employeeLoginDBGateway = new FileLoginEmployee(url, userName, password);
                }catch (SQLException e){
                    e.printStackTrace();
                    employeeLoginDBGateway = null;
                }
                EmployeeLoginPresenter employeeLoginPresenter = new EmployeeLoginResponseFormatter();
                EmployeeLoginInputBoundary interactorInput = new EmployeeLoginInteractor(employeeLoginDBGateway, employeeLoginPresenter, employeeFactory);
                EmployeeLoginController employeeLoginController = new EmployeeLoginController(interactorInput);
                EmployeeSignInBox employeeSignInBox = new EmployeeSignInBox(employeeLoginController);
            }
        });
    }
}
