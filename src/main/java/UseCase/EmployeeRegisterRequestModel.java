package UseCase;

public class EmployeeRegisterRequestModel {
    private int employeeID;
    private String lastName, firstName, address, phoneNumber, passText;

    public EmployeeRegisterRequestModel(int employeeID, String lastName, String firstName, String address, String phoneNumber, String passText){
        this.employeeID = employeeID;
        this.lastName = lastName;
        this. firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.passText = passText;
    }

    public int getEmployeeID(){
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassText() {
        return passText;
    }

    public void setPassText(String passText) {
        this.passText = passText;
    }
}

