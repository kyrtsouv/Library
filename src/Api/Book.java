package Api;

import java.util.ArrayList;

/**
 * Represents a book in the library.
 * 
 * This class implements the Serializable interface to allow objects of this
 * class to be stored in files.
 * 
 * The Book class contains information about the title, author, publisher, ISBN,
 * year of publication,
 * genre, total copies, available copies, average rating, and reviews of a book.
 * 
 * The class provides getters and setters for accessing and modifying the book's
 * attributes, as well
 * as methods for updating the book's rating and adding reviews.
 */
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

    /**
     * Class constructor.
     *
     * @param title     the title of the book
     * @param author    the author of the book
     * @param publisher the publisher of the book
     * @param isbn      the ISBN of the book
     * @param year      the year of publication of the book
     * @param genre     the genre of the book
     * @param copies    the total number of copies of the book
     */
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
    }

    // ----------------- Getters -----------------

    /**
     * Returns the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the publisher of the book.
     *
     * @return the publisher of the book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Returns the ISBN of the book.
     *
     * @return the ISBN of the book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the year of publication of the book.
     *
     * @return the year of publication of the book
     */
    public String getYear() {
        return year;
    }

    /**
     * Returns the total number of copies of the book.
     *
     * @return the total number of copies of the book
     */
    public int getTotalCopies() {
        return totalCopies;
    }

    /**
     * Returns the number of available copies of the book.
     *
     * @return the number of available copies of the book
     */
    public int getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Returns the rating of the book.
     *
     * @return the rating of the book
     */
    public float getRating() {
        return rating;
    }

    /**
     * Returns the reviews of the book.
     *
     * @return the reviews of the book
     */
    public ArrayList<String> getReviews() {
        return reviews;
    }

    /**
     * Returns the genre of the book.
     *
     * @return the genre of the book
     */
    public String getGenre() {
        return genre;
    }

    // ----------------- Setters -----------------

    /**
     * Sets the title of the book.
     *
     * @param title the title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the publisher of the book.
     *
     * @param publisher the publisher of the book
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn the ISBN of the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Sets the year of publication of the book.
     *
     * @param year the year of publication of the book
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Sets the total number of copies of the book.
     * If the new total number of copies is less than the number of available
     * copies,
     * the number of available copies is also updated.
     *
     * @param totalCopies the total number of copies of the book
     */
    public void setTotalCopies(int totalCopies) {
        availableCopies = totalCopies > availableCopies ? availableCopies : totalCopies;
        this.totalCopies = totalCopies;
    }

    /**
     * Sets the genre of the book.
     *
     * @param genre the genre of the book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // ----------------- Updaters -----------------

    /**
     * Adds a review to the book.
     * Updates the book's rating based on the new review.
     *
     * @param review the review to be added
     */
    public void addReview(Review review) {
        rating = (rating * reviews.size() + review.getRating()) / (reviews.size() + 1);
        reviews.add(review.getText());
    }

    /**
     * Decreases the total number of copies of the book by 1.
     */
    public void decreaseTotalCopies() {
        totalCopies--;
    }

    /**
     * Increases the number of available copies of the book by 1.
     */
    public void increaseAvailableCopies() {
        availableCopies++;
    }

    /**
     * Decreases the number of available copies of the book by 1.
     */
    public void decreaseAvailableCopies() {
        availableCopies--;
    }
}
