package Scrumbags.logic;

import Scrumbags.database.Dao;
import java.util.ArrayList;

public class Service {
    private Dao database;
    
     // Create database:
    public Service(Dao database) {
        this.database = database;
    }
    
    public boolean addBook(String name, String author) {
        Book book = new Book(name, author);
        return this.database.addBook(book);
    }
    public boolean addBook(String name, String author, String isbn, int pages, int  year) {
        Book book = new Book(name, author, isbn, pages, year);
        return this.database.addBook(book);
    }
    
    public boolean addLink(String name, String adress) {
        Link link = new Link(name, adress);
        return this.database.addLink(link);
    }

    public ArrayList<Book> getBooksByAuthor(String author) {
        return this.database.getBooksByAuthor(author);
    }
    
    public ArrayList<Link> getLinksByName(String name) {
        return this.database.getLinksByName(name);
    }
}