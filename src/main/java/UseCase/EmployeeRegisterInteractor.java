package UseCase;

import Database.EmployeeRegisterDBGateway;
import Database.EmployeeRegisterDBRequestModel;
import Entities.Employee;
import Entities.EmployeeFactory;

public class EmployeeRegisterInteractor implements EmployeeRegisterInputBoundary{
    final EmployeeRegisterDBGateway employeeRegisterDBGateway;
    final EmployeeRegisterPresenter employeePresenter;
    final EmployeeFactory employeeFactory;

    public EmployeeRegisterInteractor(EmployeeRegisterDBGateway employeeRegisterDBGateway,  EmployeeRegisterPresenter employeePresenter, EmployeeFactory employeeFactory ) {
        this.employeeRegisterDBGateway = employeeRegisterDBGateway;
        this.employeeFactory = employeeFactory;
        this.employeePresenter = employeePresenter;
    }

    @Override
    public EmployeeRegisterResponseModel create(EmployeeRegisterRequestModel requestModel) {
        if (employeeRegisterDBGateway.existsByName(requestModel.getEmployeeID())){
            return employeePresenter.prepareFailView("EmployeeID already exists.");
        }

        Employee employee = employeeFactory.create(requestModel.getEmployeeID(), requestModel.getLastName(), requestModel.getFirstName(),
                requestModel.getAddress(), requestModel.getPhoneNumber(), requestModel.getPassText());
        if (!employee.passwordIsValid()) {
            return employeePresenter.prepareFailView("User password must have more than 5 characters and contain at least one uppercase letter.");
        }

        EmployeeRegisterDBRequestModel employeeDBRequestModel = new EmployeeRegisterDBRequestModel(employee.getEmployeeID(), employee.getLastName(),
                employee.getFirstName(), employee.getAddress(), employee.getPhoneNumber(), employee.getPassword());
        employeeRegisterDBGateway.save(employeeDBRequestModel);

        EmployeeRegisterResponseModel accountResponseModel = new EmployeeRegisterResponseModel(employee.getEmployeeID());
        return employeePresenter.prepareSuccessView(accountResponseModel);
    }
}
