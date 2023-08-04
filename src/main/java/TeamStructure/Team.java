package TeamStructure;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Employee> employees = new ArrayList<Employee>();
    private Manager manager;

    public Team(Manager manager) {
        this.manager = manager;
    }
}