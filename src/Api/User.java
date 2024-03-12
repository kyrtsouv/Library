package Api;

public class User extends GenericUser {

    private String name;
    private String surname;
    private String email;
    private String ADT;

    private OrderedBookSet activeLendings;
    private OrderedBookSet hasReviewd;

    public User(String name, String surname, String email, String ADT, String username, String password) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.ADT = ADT;

        activeLendings = new OrderedBookSet();
        hasReviewd = new OrderedBookSet();
    }

    // ----------------- Getters -----------------
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

    public OrderedBookSet getActiveLendings() {
        return activeLendings;
    }

    public boolean hasReviewd(Book book) {
        return hasReviewd.contains(book);
    }

    // ----------------- Setters -----------------
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setADT(String ADT) {
        this.ADT = ADT;
    }

    // ----------------- Updaters -----------------
    public void addLending(Book book) {
        activeLendings.add(book);
        book.addSet(activeLendings);
    }

    public void addReview(Book book) {
        hasReviewd.add(book);
        book.addSet(hasReviewd);
    }
}
