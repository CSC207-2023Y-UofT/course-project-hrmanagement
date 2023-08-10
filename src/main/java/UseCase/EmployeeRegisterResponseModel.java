package UseCase;

public class EmployeeRegisterResponseModel {
    int login;

    public EmployeeRegisterResponseModel(int login){
        this.login = login;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }
}
