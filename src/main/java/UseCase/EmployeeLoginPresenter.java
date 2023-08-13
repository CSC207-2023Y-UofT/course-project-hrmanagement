package UseCase;

public interface EmployeeLoginPresenter {
    EmployeeLoginResponseModel prepareSuccessView(EmployeeLoginResponseModel user);
    EmployeeLoginResponseModel prepareFailView(String error);
}
