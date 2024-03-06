package Api;

import java.util.HashSet;

public class Book implements java.io.Serializable {
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String year;
    private int copies;

    private int rating;
    private HashSet<String> reviews;

    public Book(String title, String author, String publisher, String isbn, String year, int copies) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.year = year;
        this.copies = copies;

        rating = 0;
        reviews = new HashSet<>();
    }

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

    public int getCopies() {
        return copies;
    }

    public int getRating() {
        return rating;
    }

    public HashSet<String> getReviews() {
        return reviews;
    }

    public void addReview(String review) {
        reviews.add(review);
    }

    public void rate(int rating) {
        this.rating = rating;
    }

}
