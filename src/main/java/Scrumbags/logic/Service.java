package Scrumbags.logic;

import Scrumbags.database.Dao;
import java.util.ArrayList;

public class Service {
    private Dao database;
    
     // Create database:
    public Service(Dao database) {
        this.database = database;
    }
    
    // Lisättäessä nimellä ja kirjailijalla, tarkistaa onko samannimistä kirjaa olemassa jolla on isbn.
    // Ei hyväksytä duplaria jos saman niminen isbn:llä varustettu löytyy
    public boolean addBook(String name, String author) {
        Book book = new Book(name, author);
        if (bookNameExists(name) && !getBookByName(name).getIsbn().equals("---")) return false;
        return this.database.addBook(book);
    }
    public boolean addBook(String name, String author, String isbn, int pages, int  year) {
        Book book = new Book(name, author, isbn, pages, year);
        if (bookIsbnExists(isbn)) return false;
        return this.database.addBook(book);
    }
    
    public boolean addLink(String name, String adress) {
        Link link = new Link(name, adress);
        return this.database.addLink(link);
    }

    public ArrayList<Book> getBooksByAuthor(String author) {
        return this.database.getBooksByAuthor(author);
    }
    
    public Book getBookByName(String name) {
        return database.getBookByName(name);
    }
    
    public boolean bookIsbnExists(String isbn) {
        return database.getBookByIsbn(isbn) != null;
    }
    
    public boolean bookNameExists(String isbn) {
        return database.getBookByIsbn(isbn) != null;
    }
}