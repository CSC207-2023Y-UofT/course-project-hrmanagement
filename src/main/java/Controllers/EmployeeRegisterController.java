package Controllers;
import UseCase.EmployeeRegisterInputBoundary;
import UseCase.EmployeeRegisterRequestModel;
import UseCase.EmployeeRegisterResponseModel;

public class EmployeeRegisterController {
    final EmployeeRegisterInputBoundary userInput;

    /**
     * Constructs a new EmployeeRegisterController with the provided input boundary.
     * @param accountGateway The input boundary representing the use case for employee registration.
     */
    public EmployeeRegisterController(EmployeeRegisterInputBoundary accountGateway){
        this.userInput = accountGateway;
    }

    /**
     * Creates a new employee registration with the provided information.
     * @param employeeID The employee identifier.
     * @param lastName The last name of the employee.
     * @param firstName The first name of the employee.
     * @param address The address of the employee.
     * @param phoneNumber The phone number of the employee.
     * @param passText The password for the employee's account.
     */
    public EmployeeRegisterResponseModel create(int employeeID, String lastName, String firstName, String address, String phoneNumber, String passText){
         EmployeeRegisterRequestModel requestModel = new EmployeeRegisterRequestModel(
                 employeeID, lastName, firstName, address, phoneNumber, passText);

        return userInput.create(requestModel);
    }
}
