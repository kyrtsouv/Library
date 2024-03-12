package Api;

import java.io.Serializable;
import java.time.LocalDate;

public class Lending implements Serializable {

    private User user;
    private Book book;
    private LocalDate startDate;
    private LocalDate endDate;

    public Lending(Book book, User user) {
        this.user = user;
        this.book = book;
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusDays(5);
    }

    // ----------------- Getters -----------------
    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
