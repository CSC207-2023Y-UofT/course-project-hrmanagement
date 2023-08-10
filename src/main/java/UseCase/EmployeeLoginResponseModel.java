package UseCase;

public class EmployeeLoginResponseModel {
    int loginID;

    public EmployeeLoginResponseModel(int loginID){
        this.loginID = loginID;
    }

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }
}
