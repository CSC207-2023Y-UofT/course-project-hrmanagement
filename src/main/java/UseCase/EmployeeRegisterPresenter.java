package UseCase;

// Use case layer

public interface EmployeeRegisterPresenter {
    EmployeeRegisterResponseModel prepareSuccessView(EmployeeRegisterResponseModel user);
    EmployeeRegisterResponseModel prepareFailView(String error);
}