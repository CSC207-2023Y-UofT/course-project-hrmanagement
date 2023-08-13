package Database;

public interface EmployeeRegisterDBGateway {
    boolean existsByName(int employeeIdentifier);
    void save(EmployeeRegisterDBRequestModel requestModel);
}
