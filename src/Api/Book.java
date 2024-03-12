package Api;

import java.util.ArrayList;
import java.util.HashSet;

public class Book implements java.io.Serializable {
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String year;
    private int totalCopies;
    private int availableCopies;
    private String genre;

    private float rating;
    private ArrayList<String> reviews;

    private HashSet<OrderedBookSet> setsThatContainThis;

    public Book(String title, String author, String publisher, String isbn, String year, String genre, int copies) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.year = year;
        this.genre = genre;
        this.totalCopies = copies;
        this.availableCopies = copies;

        rating = 0;
        reviews = new ArrayList<>();

        setsThatContainThis = new HashSet<>();
    }

    // ----------------- Getters -----------------
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getYear() {
        return year;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public float getRating() {
        return rating;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public String getGenre() {
        return genre;
    }

    // ----------------- Setters -----------------
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    // ----------------- Updaters -----------------
    public void addReview(Review review) {
        rating = (rating * reviews.size() + review.getRating()) / (reviews.size() + 1);
        reviews.add(review.getText());

        for (OrderedBookSet set : setsThatContainThis) {
            set.remove(this);
            set.add(this);
        }
    }

    public void addSet(OrderedBookSet set) {
        setsThatContainThis.add(set);
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void decreaseTotalCopies() {
        totalCopies--;
    }

    public void increaseAvailableCopies() {
        availableCopies++;
    }

    public void decreaseAvailableCopies() {
        availableCopies--;
    }

}
