package Api;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Objects of this class hold the information for a lending of a book from a
 * specific user, the day that it was lent and the latest day it should be
 * returned.
 */
public class Lending implements Serializable {

    private User user;
    private Book book;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Class constructor.
     *
     * @param book The book being lent.
     * @param user The user borrowing the book.
     */
    public Lending(Book book, User user) {
        this.user = user;
        this.book = book;
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusDays(5);
    }

    // ----------------- Getters -----------------

    /**
     * Returns the user who borrowed the book.
     *
     * @return The user who borrowed the book.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the book being lent.
     *
     * @return The book being lent.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Returns the start date of the lending.
     *
     * @return The start date of the lending.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the lending.
     *
     * @return The end date of the lending.
     */
    public LocalDate getEndDate() {
        return endDate;
    }
}
