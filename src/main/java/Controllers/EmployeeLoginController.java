package Controllers;

import UseCase.*;

public class EmployeeLoginController {

    final EmployeeLoginInputBoundary empInput;

    public EmployeeLoginController(EmployeeLoginInputBoundary accGateway){
        this.empInput = accGateway;
    }

        public EmployeeLoginResponseModel checkLogin(int employeeID, String password){
        EmployeeLoginRequestModel requestModel = new EmployeeLoginRequestModel(employeeID, password);

        return empInput.checkLogin(requestModel);
    }
}
