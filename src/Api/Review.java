package Api;

/**
 * Objects of this class hold the text of the review and a rating of a book from
 * a user. The objects are used only to transfer the information to the book
 * which holds the texts in a list and only the average rating.
 */
public class Review {
    private String review;
    private int rating;

    /**
     * Class constructor.
     *
     * @param review the text of the review
     * @param rating the rating of the review
     */
    public Review(String review, int rating) {
        this.review = review;
        this.rating = rating;
    }

    /**
     * Returns the text of the review.
     *
     * @return the text of the review
     */
    public String getText() {
        return review;
    }

    /**
     * Returns the rating of the review.
     *
     * @return the rating of the review
     */
    public int getRating() {
        return rating;
    }
}
