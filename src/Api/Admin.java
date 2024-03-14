package Api;

/**
 * The Admin class represents an administrator user in the system.
 * It extends the `GenericUser` class and provides additional functionality
 * specific to administrators.
 */
public class Admin extends GenericUser {

    public Admin(String username, String password) {
        super(username, password);
    }
}