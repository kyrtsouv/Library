package MVC;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import Api.Admin;
import Api.Book;
import Api.GenericUser;
import Api.Lending;
import Api.Review;
import Api.User;

/**
 * The Data class is responsible for managing the data of the library
 * application.
 * It contains the data model and is responsible for loading and saving the
 * data.
 * 
 * It has methods for:
 * 1) getting the different collections of objects and
 * 2) adding, updating and deleting users, books, genres and lendings.
 */

public class Data implements java.io.Serializable {

    private HashMap<String, GenericUser> usernamesToUsers;

    private HashSet<String> emails;
    private HashSet<String> ADTs;

    private HashSet<Book> books;
    private HashMap<String, HashSet<Book>> genreToBooks;

    private HashSet<Lending> lendings;

    private Data() {
        usernamesToUsers = new HashMap<>();
        emails = new HashSet<>();
        ADTs = new HashSet<>();

        books = new HashSet<>();
        genreToBooks = new HashMap<>();

        lendings = new HashSet<>();
    }

    // ----------------- Serialization -----------------
    public static Data load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("medialab/data.ser"));
            Data data = (Data) in.readObject();
            in.close();
            return data;
        } catch (Exception e) {
            return new Data();
        }
    }

    public void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("medialab/data.ser"));
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------- Getters -----------------
    public GenericUser getUser(String username, String password) {
        GenericUser user = usernamesToUsers.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new IllegalArgumentException("Λάθος όνομα χρήστη ή κωδικός πρόσβασης");
    }

    public HashSet<Book> getBooks() {
        return books;
    }

    public HashMap<String, HashSet<Book>> getGenreToBooks() {
        return genreToBooks;
    }

    public HashMap<String, GenericUser> getUsernamesToUsers() {
        return usernamesToUsers;
    }

    public HashSet<Lending> getLendings() {
        return lendings;
    }

    public HashSet<Book> searchBooks(String title, String author, String year) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase())
                        && book.getAuthor().toLowerCase().contains(author.toLowerCase())
                        && book.getYear().toLowerCase().contains(year.toLowerCase()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    // ----------------- Adders -----------------
    public void addUser(User user) {
        if (user.getUsername().equals("") || user.getPassword().equals("") || user.getName().equals("")
                || user.getSurname().equals("") || user.getEmail().equals("") || user.getADT().equals("")) {
            throw new IllegalArgumentException("Όλα τα πεδία είναι υποχρεωτικά");
        }
        if (emails.contains(user.getEmail()) || ADTs.contains(user.getADT())
                || usernamesToUsers.keySet().contains(user.getUsername())) {
            throw new IllegalArgumentException("Το Email ή ΑΔΤ ή Όνομα Χρήστη χρησιμοποιούνται ήδη");
        }

        usernamesToUsers.put(user.getUsername(), user);
        emails.add(user.getEmail());
        ADTs.add(user.getADT());
    }

    public void addBook(Book book) {
        books.add(book);
        genreToBooks.get(book.getGenre()).add(book);
    }

    public void addAdmin(Admin admin) {
        usernamesToUsers.put(admin.getUsername(), admin);
    }

    public void addGenre(String genre) {
        if (!genreToBooks.containsKey(genre)) {
            genreToBooks.put(genre, new HashSet<Book>());
        }
    }

    public void addReview(Book book, User user, Review review) {
        book.addReview(review);
        user.addReview(book);
    }

    // ----------------- Updaters -----------------
    public void updateGenre(String oldGenre, String newGenre) {
        HashSet<Book> affectedBooks = genreToBooks.get(oldGenre);
        genreToBooks.remove(oldGenre);
        if (newGenre.equals("")) {
            for (Book book : affectedBooks) {
                books.remove(book);
                lendings.removeIf(lending -> lending.getBook().equals(book));
            }
            return;
        }
        if (!genreToBooks.containsKey(newGenre)) {
            genreToBooks.put(newGenre, new HashSet<Book>());
        }
        genreToBooks.get(newGenre).addAll(affectedBooks);
        for (Book book : affectedBooks) {
            book.setGenre(newGenre);
        }
    }

    public void updateBook(Book oldBook, Book newBook) {
        genreToBooks.get(oldBook.getGenre()).remove(oldBook);

        oldBook.setTitle(newBook.getTitle());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setGenre(newBook.getGenre());
        oldBook.setPublisher(newBook.getPublisher());
        oldBook.setIsbn(newBook.getIsbn());
        oldBook.setYear(newBook.getYear());
        oldBook.setTotalCopies(newBook.getTotalCopies());

        genreToBooks.get(newBook.getGenre()).add(oldBook);
    }

    public void updateUser(User oldUser, User newUser) {
        usernamesToUsers.remove(oldUser.getUsername());
        emails.remove(oldUser.getEmail());
        ADTs.remove(oldUser.getADT());

        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setName(newUser.getName());
        oldUser.setSurname(newUser.getSurname());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setADT(newUser.getADT());

        usernamesToUsers.put(newUser.getUsername(), oldUser);
        emails.add(newUser.getEmail());
        ADTs.add(newUser.getADT());
    }

    public void lendBook(Book book, User user) {
        Lending lending = new Lending(book, user);
        lendings.add(lending);
        user.addLending(book);
        book.decreaseAvailableCopies();
    }

    // ----------------- Deleters -----------------
    public void deleteBook(Book book) {
        books.remove(book);
        genreToBooks.get(book.getGenre()).remove(book);
        lendings.removeIf(lending -> lending.getBook().equals(book));
    }

    public void deleteUser(User user) {
        usernamesToUsers.remove(user.getUsername());
        emails.remove(user.getEmail());
        ADTs.remove(user.getADT());

        lendings.removeIf(lending -> {
            if (lending.getUser().equals(user)) {
                Book book = lending.getBook();
                book.decreaseTotalCopies();
                if (book.getTotalCopies() == 0) {
                    books.remove(book);
                    genreToBooks.get(book.getGenre()).remove(book);
                }
                return true;
            }
            return false;
        });
    }

    public void deleteLending(Lending lending) {
        lendings.remove(lending);
        lending.getBook().increaseAvailableCopies();

        lending.getUser().removeLending(lending.getBook());
    }
}
