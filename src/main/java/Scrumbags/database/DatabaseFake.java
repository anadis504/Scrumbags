package Scrumbags.database;

import Scrumbags.logic.Book;
import Scrumbags.logic.Link;
import java.util.ArrayList;

public class DatabaseFake implements Dao {

    private ArrayList<Book> books;
    private ArrayList<Link> links;

    public DatabaseFake() {
        this.books = new ArrayList<>();
        this.links = new ArrayList<>();
    }

    public boolean format() {
        this.books.clear();
        this.links.clear();
        return true;
    }
   
    @Override
    public boolean addBook(Book book) {
        this.books.add(book);
        return true;
    }

    @Override
    public boolean addLink(Link link) {
        this.links.add(link);
        return true;
    }

<<<<<<< HEAD
    @Override
    public void getBooks(String author) {
        for (Book b: this.books) {
            if (b.getAuthor().equals(author)) {
                System.out.println(b.toString());
            }
        }
    }

    @Override
    public void getLinks(String name) {
        
    }
=======
    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }
    
>>>>>>> f08d58fb9839a33869bf7ee5223a24107d9cb696

}
