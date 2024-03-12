package Api;

public class GenericUser implements java.io.Serializable {

    protected String username;
    protected String password;

    public GenericUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // ----------------- Getters -----------------
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // ----------------- Setters -----------------
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
