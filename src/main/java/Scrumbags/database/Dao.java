
package Scrumbags.database;
import Scrumbags.logic.*;
import java.util.ArrayList;

public interface Dao {
    boolean addBook(Book book);
    boolean addLink(Link link);
    boolean removeBook(String isbn, String name);
    boolean removeLink(String url);
    boolean addPodcast(Podcast podcast);
    
    ArrayList<Book> getBooksByName(String name);
    ArrayList<Book> getBooksByAuthor(String author);
    Book getBookByIsbn(String isbn);
    ArrayList<Book> getBooksByYear(int year);
    ArrayList<Book> getAllBooks();
    
    ArrayList<Link> getLinksByName(String name);
    ArrayList<Link> getAllLinks();
    
    ArrayList<Podcast> getPodcastsByName(String name);
    ArrayList<Podcast> getAllPodcasts();
}