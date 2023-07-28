package TeamStructure;

public class Employee extends User{
    private double wage;


    public Employee(String Username, String Password, double wage)
    {
        super(Username, Password);
        this.wage = wage;
    }

    public double getWage() {
        return wage;
    }
}
