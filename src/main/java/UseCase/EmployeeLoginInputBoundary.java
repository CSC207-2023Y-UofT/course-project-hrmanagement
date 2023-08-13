package UseCase;

public interface EmployeeLoginInputBoundary {
    EmployeeLoginResponseModel checkLogin(EmployeeLoginRequestModel requestModel);
}
