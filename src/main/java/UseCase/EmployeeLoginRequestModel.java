package UseCase;

public class EmployeeLoginRequestModel {
    private int employeeID;
    private String password;

    public EmployeeLoginRequestModel(int employeeID, String password){
        this.employeeID = employeeID;
        this.password = password;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
