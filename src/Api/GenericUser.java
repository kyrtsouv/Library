package Api;

import java.io.Serializable;

/**
 * The GenericUser class represents a generic user (either admin or simple user)
 * with a username and password.
 * It implements the Serializable interface to allow objects of this class to be
 * stored in files.
 */
public class GenericUser implements Serializable {

    protected String username;
    protected String password;

    /**
     * Class constructor.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public GenericUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // ----------------- Getters -----------------

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    // ----------------- Setters -----------------

    /**
     * Sets the username of the user.
     *
     * @param username the new username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
