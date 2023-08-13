package Entities;

public interface Employee {
    boolean passwordIsValid();
    int getEmployeeID();
    String getLastName();
    String getFirstName();
    String getAddress();
    String getPhoneNumber();
    String getPassword();
}
