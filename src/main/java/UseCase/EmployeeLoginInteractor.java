package UseCase;

import Database.EmployeeLoginDBGateway;
import Entities.Employee;
import Entities.EmployeeFactory;


public class EmployeeLoginInteractor implements EmployeeLoginInputBoundary{

    final EmployeeLoginDBGateway employeeLoginDBGateway;
    final EmployeeLoginPresenter employeeLoginPresenter;
    final EmployeeFactory employeeFactory;

    public EmployeeLoginInteractor( EmployeeLoginDBGateway employeeLoginDBGateway,
                                    EmployeeLoginPresenter employeeLoginPresenter,
                                    EmployeeFactory employeeFactory) {
        this.employeeLoginDBGateway = employeeLoginDBGateway;
        this.employeeFactory = employeeFactory;
        this.employeeLoginPresenter = employeeLoginPresenter;
    }

    @Override
    public EmployeeLoginResponseModel checkLogin(EmployeeLoginRequestModel requestModel) {
        if(!employeeLoginDBGateway.existsNotID(requestModel.getEmployeeID(), requestModel.getPassword())){
            return employeeLoginPresenter.prepareFailView("Wrong EmployeeID/Password");
        }else{
        Employee employee = employeeFactory.checkLogin(requestModel.getEmployeeID(), requestModel.getPassword());

        EmployeeLoginResponseModel accountResponseModel = new EmployeeLoginResponseModel(employee.getEmployeeID());
        return employeeLoginPresenter.prepareSuccessView(accountResponseModel);
        }
    }

}
