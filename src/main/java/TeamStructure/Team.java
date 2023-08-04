package TeamStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * A team of employees and a manager. Contains a list of employees, and a single manager.
 */
public class Team {
    private List<Employee> employees = new ArrayList<Employee>();
    private Manager manager;

    public Team(Manager manager) {
        this.manager = manager;
    }

    /**
     * Returns the list of all employees in the team.
     * @return
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Returns the team's manager.
     * @return
     */
    public Manager getManager() {
        return manager;
    }
}
