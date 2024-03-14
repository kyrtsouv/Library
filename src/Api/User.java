package Api;

import java.util.HashSet;

/**
 * The User class represents a user of the library with a name, surname, email,
 * ADT, username and password.
 * It extends the GenericUser class and adds the extra information of the user.
 * It also contains a list of the books the user is currently lending and a list
 * of the books the user has reviewed.
 */
public class User extends GenericUser {

    private String name;
    private String surname;
    private String email;
    private String ADT;

    private HashSet<Book> activeLendings;
    private HashSet<Book> hasReviewd;

    /**
     * C
     *
     * @param name     the user's name
     * @param surname  the user's surname
     * @param email    the user's email
     * @param ADT      the user's ADT (identification number)
     * @param username the user's username
     * @param password the user's password
     */
    public User(String name, String surname, String email, String ADT, String username, String password) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.ADT = ADT;

        activeLendings = new HashSet<>();
        hasReviewd = new HashSet<>();
    }

    // ----------------- Getters -----------------

    /**
     * Returns the user's name.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the user's surname.
     *
     * @return the user's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the user's email.
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the user's ADT (identification number).
     *
     * @return the user's ADT
     */
    public String getADT() {
        return ADT;
    }

    /**
     * Returns the set of active lendings by the user.
     *
     * @return the set of active lendings
     */
    public HashSet<Book> getActiveLendings() {
        return activeLendings;
    }

    /**
     * Checks if the user has reviewed the specified book.
     *
     * @param book the book to check
     * @return true if the user has reviewed the book, false otherwise
     */
    public boolean hasReviewd(Book book) {
        return hasReviewd.contains(book);
    }

    // ----------------- Setters -----------------

    /**
     * Sets the user's name.
     *
     * @param name the user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the user's surname.
     *
     * @param surname the user's surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Sets the user's email.
     *
     * @param email the user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the user's ADT (identification number).
     *
     * @param ADT the user's ADT
     */
    public void setADT(String ADT) {
        this.ADT = ADT;
    }

    // ----------------- Updaters -----------------

    /**
     * Adds a book to the user's active lendings.
     *
     * @param book the book to add
     */
    public void addLending(Book book) {
        activeLendings.add(book);
    }

    /**
     * Removes a book from the user's active lendings.
     *
     * @param book the book to remove
     */
    public void removeLending(Book book) {
        activeLendings.remove(book);
    }

    /**
     * Adds a book to the user's reviewed books.
     *
     * @param book the book to add
     */
    public void addReview(Book book) {
        hasReviewd.add(book);
    }
}
