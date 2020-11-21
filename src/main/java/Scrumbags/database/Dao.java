
package Scrumbags.database;
import Scrumbags.logic.*;
import java.util.ArrayList;

public interface Dao {
    boolean addBook(Book book);
    boolean addLink(Link link);
    ArrayList<Book> getBooksByAuthor(String author);
    ArrayList<Link> getLinksByName(String name);
}