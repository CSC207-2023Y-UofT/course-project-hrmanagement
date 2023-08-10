package Entities;

public class Manager {
    private int managerID;
    private  String pass;

    public Manager(){
    }

    public Manager(int managerID, String pass) {
        this.managerID = managerID;
        this.pass = pass;
    }

    // Getters and Setter methods

    /**
     * Retrieves managerID of the Manager.
     *
     * @return The manager's ID.
     */
    public int getManagerID() {
        return managerID;
    }

    /**
     * Sets managerID of the Manager.
     *
     * @param managerID:  The managerID for the Manager.
     */
    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    /**
     * Retrieves password of the manager.
     *
     * @return The manager's password.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets pass of the manager.
     *
     * @param pass:  The password for the manager.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

}
