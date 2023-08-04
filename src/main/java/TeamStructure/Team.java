package TeamStructure;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private static List<Employee> Employees = new ArrayList<Employee>();
    private Manager Manager;

    public Team(Manager Manager) {
        this.Manager = Manager;
    }

    public static List<Employee> getTeam(){
        return Employees;
    }

}
