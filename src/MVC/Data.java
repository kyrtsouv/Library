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
import Api.OrderedBookSet;
import Api.Review;
import Api.User;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Data implements java.io.Serializable {

    private HashMap<String, GenericUser> users;

    private HashSet<String> emails;
    private HashSet<String> ADTs;
    private HashSet<String> usernames;

    private OrderedBookSet books;
    private HashMap<String, OrderedBookSet> genreToBooks;

    private HashSet<Lending> lendings;

    private Data() {
        users = new HashMap<>();
        emails = new HashSet<>();
        ADTs = new HashSet<>();
        usernames = new HashSet<>();

        books = new OrderedBookSet();
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
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("medialab/tempData.ser"));
            out.writeObject(this);
            out.close();

            Files.copy(Paths.get("medialab/tempData.ser"), Paths.get("medialab/data.ser"),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------- Getters -----------------
    public GenericUser getUser(String username, String password) {
        GenericUser user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new IllegalArgumentException("Λάθος όνομα χρήστη ή κωδικός πρόσβασης");
    }

    public OrderedBookSet getBooks() {
        return books;
    }

    public HashMap<String, OrderedBookSet> getGenreToBooks() {
        return genreToBooks;
    }

    public HashMap<String, GenericUser> getUsers() {
        return users;
    }

    public HashSet<Lending> getLendings() {
        return lendings;
    }

    public OrderedBookSet searchBooks(String title, String author, String year) {
        return books.stream().filter(book -> book.getTitle().contains(title) && book.getAuthor().contains(author)
                && book.getYear().contains(year)).collect(Collectors.toCollection(OrderedBookSet::new));
    }

    // ----------------- Adders -----------------
    public void addUser(User user) {
        if (user.getUsername().equals("") || user.getPassword().equals("") || user.getName().equals("")
                || user.getSurname().equals("") || user.getEmail().equals("") || user.getADT().equals("")) {
            throw new IllegalArgumentException("Όλα τα πεδία είναι υποχρεωτικά");
        }
        if (emails.contains(user.getEmail()) || ADTs.contains(user.getADT())
                || usernames.contains(user.getUsername())) {
            throw new IllegalArgumentException("Το Email ή ΑΔΤ ή Όνομα Χρήστη χρησιμοποιούνται ήδη");
        }

        users.put(user.getUsername(), user);
        emails.add(user.getEmail());
        ADTs.add(user.getADT());
        usernames.add(user.getUsername());
    }

    public void addBook(Book book) {
        books.add(book);
        book.addSet(books);
        genreToBooks.get(book.getGenre()).add(book);
        book.addSet(genreToBooks.get(book.getGenre()));
    }

    public void addAdmin(Admin admin) {
        users.put(admin.getUsername(), admin);
    }

    public void addGenre(String genre) {
        if (!genreToBooks.containsKey(genre)) {
            genreToBooks.put(genre, new OrderedBookSet());
        }
    }

    public void addReview(Book book, User user, Review review) {
        book.addReview(review);
        user.addReview(book);
    }

    // ----------------- Updaters -----------------
    public void updateGenre(String oldGenre, String newGenre) {
        OrderedBookSet affectedBooks = genreToBooks.get(oldGenre);
        genreToBooks.remove(oldGenre);
        if (newGenre.equals("")) {
            for (Book book : affectedBooks) {
                books.remove(book);
            }
            return;
        }
        if (!genreToBooks.containsKey(newGenre)) {
            genreToBooks.put(newGenre, new OrderedBookSet());
        }
        genreToBooks.get(newGenre).addAll(affectedBooks);
        for (Book book : affectedBooks) {
            book.setGenre(newGenre);
        }
    }

    public void updateBook(Book oldBook, Book newBook) {
        oldBook.setTitle(newBook.getTitle());
        oldBook.setAuthor(newBook.getAuthor());
        books.remove(oldBook);
        genreToBooks.get(oldBook.getGenre()).remove(oldBook);
        books.add(newBook);
        genreToBooks.get(newBook.getGenre()).add(newBook);
    }

    public void updateUser(User oldUser, User newUser) {
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setName(newUser.getName());
        oldUser.setSurname(newUser.getSurname());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setADT(newUser.getADT());

        users.remove(newUser.getUsername());
        users.put(newUser.getUsername(), oldUser);

        emails.remove(oldUser.getEmail());
        emails.add(newUser.getEmail());

        ADTs.remove(oldUser.getADT());
        ADTs.add(newUser.getADT());

        usernames.remove(oldUser.getUsername());
        usernames.add(newUser.getUsername());
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
    }

    public void deleteUser(User user) {
        users.remove(user.getUsername());
        emails.remove(user.getEmail());
        ADTs.remove(user.getADT());
        usernames.remove(user.getUsername());

        for (Book book : lendings.stream().map(lending -> lending.getBook()).peek(book -> book.decreaseTotalCopies())
                .collect(Collectors.toSet())) {
            if (book.getTotalCopies() == 0) {
                deleteBook(book);
            }
        }
    }

    public void deleteLending(Lending lending) {
        lendings.remove(lending);
        lending.getBook().increaseAvailableCopies();

        lending.getUser().getActiveLendings().remove(lending.getBook());
    }
}
