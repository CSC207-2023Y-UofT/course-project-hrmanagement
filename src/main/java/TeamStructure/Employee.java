package TeamStructure;

/**
 * An employee of the team. Has a wage. Can create availability or create time off requests.
 */
public class Employee extends User{
    private double wage;

    public Employee(String username, String password, double wage)
    {
        super(username, password);
        this.wage = wage;
    };

    /**
     * Returns the Employee's Wage rate.
     * @return wage
     */
    public double getWage() {
        return wage;
    }
}