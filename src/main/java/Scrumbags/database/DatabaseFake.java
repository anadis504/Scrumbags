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
            }
        }
        return booklist;
    }

    @Override
    public ArrayList<Link> getLinksByName(String name) {
        ArrayList<Link>  linklist = new ArrayList<>();
        for (Link l: this.links) {
            if (l.getName().equals(name)) {
                linklist.add(l);
            }
        }
        return linklist;
    }

    @Override
    public ArrayList<Book> getBooksByName(String name) {
        ArrayList<Book> booklist = new ArrayList<>();
        for (Book b: this.books) {
            if (b.getName().equals(name)) {
                booklist.add(b);
            }
        }
        return booklist;
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