package Controllers;
import UseCase.EmployeeCreationFailed;
import UseCase.EmployeeLoginPresenter;
import UseCase.EmployeeLoginResponseModel;

public class EmployeeLoginResponseFormatter implements EmployeeLoginPresenter {

    /**
     * Prepares a success view for the employee login response.
     * @param user The response model containing login success information.
     */
    @Override
    public EmployeeLoginResponseModel prepareSuccessView(EmployeeLoginResponseModel user) {
        return user;
    }

    /**
     * Prepares a failure view for the employee login response.
     * @param error The error message indicating the cause of the login failure.
     */
    @Override
    public EmployeeLoginResponseModel prepareFailView(String error) {
        throw new EmployeeCreationFailed(error);
    }
}
