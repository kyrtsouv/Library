package Api;

import java.util.HashSet;

public class Book implements java.io.Serializable {
    private String title;
    private String author;
    private String publisher;
    private int isbn;
    private int year;
    private int copies;

    private int rating;
    private HashSet<String> reviews;

    public Book(String title, String author, String publisher, int isbn, int year, int copies) {
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

    public int getIsbn() {
        return isbn;
    }

    public int getYear() {
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
