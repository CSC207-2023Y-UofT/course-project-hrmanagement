package TeamStructure;

public class Employee extends User{
    private double wage;


    public Employee(String username, String password, double wage)
    {
        super(username, password);
        this.wage = wage;
    }

    public double getWage() {
        return wage;
    }

}
