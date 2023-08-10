package UseCase;

public class EmployeeCreationFailed extends RuntimeException {
    public EmployeeCreationFailed(String error) {
        super(error);
    }
}

