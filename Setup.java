import Api.Admin;
import Api.Book;
import Api.Review;
import Api.User;
import MVC.Data;

public class Setup {

    public static void main(String[] args) {
        Data data = Data.load();
        data.addAdmin(new Admin("medialab", "medialab_2024"));

        data.addGenre("Επιστημονική Φαντασία");
        data.addGenre("Μυστηρίου");

        Book book1 = new Book("Book 1", "Author 1", "Publisher 1", "ISBN 1", "2022", "Επιστημονική Φαντασία", 1);
        Book book2 = new Book("Book 2", "Author 2", "Publisher 2", "ISBN 2", "2022", "Επιστημονική Φαντασία", 2);
        Book book3 = new Book("Book 3", "Author 3", "Publisher 3", "ISBN 3", "2022", "Επιστημονική Φαντασία", 3);
        Book book4 = new Book("Book 4", "Author 4", "Publisher 4", "ISBN 4", "2022", "Επιστημονική Φαντασία", 4);
        Book book5 = new Book("Book 5", "Author 5", "Publisher 5", "ISBN 5", "2022", "Μυστηρίου", 5);
        Book book6 = new Book("Book 6", "Author 6", "Publisher 6", "ISBN 6", "2022", "Μυστηρίου", 6);

        User user1 = new User("User 1", "Lastname 1", "user1@example.com", "123456789", "username1", "password1");
        User user2 = new User("User 2", "Lastname 2", "user2@example.com", "987654321", "username2", "password2");
        User user3 = new User("User 3", "Lastname 3", "user3@example.com", "111111111", "username3", "password3");
        User user4 = new User("User 4", "Lastname 4", "user4@example.com", "999999999", "username4", "password4");

        data.addBook(book1);
        data.addBook(book2);
        data.addBook(book3);
        data.addBook(book4);
        data.addBook(book5);
        data.addBook(book6);

        data.addUser(user1);
        data.addUser(user2);
        data.addUser(user3);
        data.addUser(user4);

        data.lendBook(book1, user1);
        data.lendBook(book2, user1);

        data.lendBook(book3, user2);
        data.lendBook(book4, user2);

        data.lendBook(book5, user3);
        data.lendBook(book6, user3);

        data.lendBook(book6, user4);

        data.addReview(book6, user4, new Review("Πολύ καλό", 5));
        data.addReview(book3, user2, new Review("Μέτριο", 3));
        data.addReview(book4, user2, new Review("Έτσι κι έτσι", 4));
        data.addReview(book2, user1, new Review("Χάλια", 2));
        data.addReview(book1, user1, new Review("Ό,τι χειρότερο", 1));
        data.addReview(book5, user3, new Review("Μπορούσε και καλύτερο", 4));
        data.addReview(book6, user3, new Review("Καλό", 4));

        data.save();
    }
}
