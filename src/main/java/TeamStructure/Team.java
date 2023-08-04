package TeamStructure;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private static List<Employee> Employees = new ArrayList<Employee>();
    private Manager Manager;

    public Team(Manager manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Manager getManager() {
        return manager;
    }

    public static List<Employee> getTeam(){
        return Employees;
    }

}
