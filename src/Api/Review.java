package Api;

public class Review {
    private String review;
    private int rating;

    public Review(String review, int rating) {
        this.review = review;
        this.rating = rating;
    }

    // ----------------- Getters -----------------
    public String getText() {
        return review;
    }

    public int getRating() {
        return rating;
    }
}
