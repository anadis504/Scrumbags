
package Scrumbags.database;
import Scrumbags.logic.*;


public interface Dao {
    boolean addBook(Book book);
    boolean addLink(Link link);
    void getBooksByAuthor(String author);
    void getLinksByName(String name);
}
