package Database;

public interface EmployeeLoginDBGateway {
    boolean existsNotID(int employeeID, String password);
}
