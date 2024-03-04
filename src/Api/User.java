package Api;

public class User extends GenericUser {

    private String name;
    private String surname;
    private String email;
    private String ADT;

    public User(String name, String surname, String email, String ADT, String username, String password) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.ADT = ADT;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getADT() {
        return ADT;
    }

}
