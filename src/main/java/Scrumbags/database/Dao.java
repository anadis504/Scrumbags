
package Scrumbags.database;
import Scrumbags.logic.*;
import java.util.ArrayList;

public interface Dao {
    boolean addBook(Book book);
    boolean addLink(Link link);
    boolean addPodcast(Podcast podcast);
    boolean removeBook(String isbn, String name);
    boolean removeLink(String url);
    boolean removePodcast(String name);
    
    ArrayList<Book> getBooksByName(String name);
    ArrayList<Book> getBooksByAuthor(String author);
    Book getBookByIsbn(String isbn);
    ArrayList<Book> getBooksByYear(int year);
    
    ArrayList<Link> getLinksByName(String name);
    ArrayList<Link> getAllLinks();
    
    ArrayList<Podcast> getPodcastsByName(String name);
}