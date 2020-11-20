
package Scrumbags.database;
import Scrumbags.logic.*;


public interface Dao {
    boolean addBook(Book book);
    boolean addLink(Link link);
    void getBooks(String author);
    void getLinks(String name);
}
