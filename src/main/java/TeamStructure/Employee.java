package TeamStructure;

public class Employee extends User{
    private double Wage;

    public Employee(String Username, String Password, double wage)
    {
        super(Username, Password);
        this.Wage = wage;
    };
}