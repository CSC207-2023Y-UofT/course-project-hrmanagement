package TeamStructure;

/**
 * A User of the system. Has a unique username and a password.
 */
public abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get the User's account Username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the User's account password.
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
