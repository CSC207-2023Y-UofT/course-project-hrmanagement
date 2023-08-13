package Controllers;
import UseCase.EmployeeCreationFailed;
import UseCase.EmployeeRegisterPresenter;
import UseCase.EmployeeRegisterResponseModel;

public class EmployeeRegisterResponseFormatter implements EmployeeRegisterPresenter {

    /**
     * Prepares a success view for the employee registration response.
     * @param response The response model containing registration success information.
     */
    @Override
    public EmployeeRegisterResponseModel prepareSuccessView(EmployeeRegisterResponseModel response) {
        return response;
    }

    /**
     * Prepares a fail view for the employee registration response.
     * @param error The error message indicating the cause of the registration failure.
     */
    @Override
    public EmployeeRegisterResponseModel prepareFailView(String error) {
        throw new EmployeeCreationFailed(error);
    }
}
