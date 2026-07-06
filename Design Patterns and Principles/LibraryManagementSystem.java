import java.util.Arrays;
import java.util.Comparator;

class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public void display() {
        System.out.println(bookId + " " + title + " " + author);
    }
}

public class LibraryManagementSystem {

    // Linear Search
    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary Search
    public static Book binarySearch(Book[] books, String title) {
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int result = books[mid].title.compareToIgnoreCase(title);

            if (result == 0)
                return books[mid];
            else if (result < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return null;
    }

    public static void main(String[] args) {

        Book[] books = {
                new Book(101, "Java", "James Gosling"),
                new Book(102, "Python", "Guido van Rossum"),
                new Book(103, "C++", "Bjarne Stroustrup"),
                new Book(104, "DBMS", "Korth")
        };

        // Linear Search
        System.out.println("Linear Search:");
        Book book1 = linearSearch(books, "Python");

        if (book1 != null)
            book1.display();
        else
            System.out.println("Book Not Found");

        // Sort for Binary Search
        Arrays.sort(books, Comparator.comparing(b -> b.title));

        // Binary Search
        System.out.println("\nBinary Search:");
        Book book2 = binarySearch(books, "Python");

        if (book2 != null)
            book2.display();
        else
            System.out.println("Book Not Found");
    }
}