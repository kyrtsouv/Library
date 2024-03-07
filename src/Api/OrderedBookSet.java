package Api;

import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeSet;

public class OrderedBookSet extends TreeSet<Book> {

    private static class BookComparator implements Comparator<Book>, Serializable {
        @Override
        public int compare(Book b1, Book b2) {
            return b2.getRating() - b1.getRating();
        }
    }

    public OrderedBookSet() {
        super(new BookComparator());
    }
}
