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

    @Override
    public ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> booklist = new ArrayList<>();
        for (Book b: this.books) {
            if (b.getAuthor().equals(author)) {
                booklist.add(b);
                // System.out.println(b.toString());
            }
        }
        return booklist;
    }

    @Override
    public ArrayList<Link> getLinksByName(String name) {
        ArrayList<Link>  linklist = new ArrayList<>();
        return linklist;
    }

    @Override
    public Book getBookByName(String name) {
        for (Book b: this.books) {
            if (b.getName().equals(name)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        for (Book b: this.books) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null;
    }
}