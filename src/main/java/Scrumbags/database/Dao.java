
package Scrumbags.database;
import Scrumbags.logic.*;
import java.util.ArrayList;

public interface Dao {
    boolean addBook(Book book);
    boolean addLink(Link link);
    boolean removeBook(String isbn);
    boolean removeLink(String url);
    ArrayList<Book> getBooksByName(String name);
    ArrayList<Book> getBooksByAuthor(String author);
    Book getBookByIsbn(String isbn);
    ArrayList<Book> getBooksByYear(int year);
    ArrayList<Link> getLinksByName(String name);
}