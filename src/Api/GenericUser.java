package Api;

public class GenericUser implements java.io.Serializable {

    protected String username;
    protected String password;

    public GenericUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
