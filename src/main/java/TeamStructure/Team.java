package TeamStructure;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private static List<Employee> employees = new ArrayList<Employee>();
    private Manager manager;

    public Team(Manager manager) {
        this.manager = manager;
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public Manager getManager() {
        return manager;
    }
}
