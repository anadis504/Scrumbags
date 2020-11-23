/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scrumbags.database;

import Scrumbags.logic.Book;
import Scrumbags.logic.Link;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author tkoukkar
 */
public class Database implements Dao {
    private Library ldb;
    
    public Database(String path) throws ClassNotFoundException {        
        this.ldb = new Library(path);
    }

    @Override
    public boolean addBook(Book book) {
        String name = book.getName();
        String author = book.getAuthor();
        String isbn = book.getIsbn();
        int year = book.getYear();
        int pages = book.getPages();
        
        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Books (name, author, year, pages, isbn) VALUES (?, ?, ?, ?, ?)");
            
            stmt.setString(1, name);
            stmt.setString(2, author);
            stmt.setInt(3, year);
            stmt.setInt(4, pages);
            stmt.setString(5, isbn);
            
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean addLink(Link link) {
        String name = link.getName();
        String address = link.getAdress();
        
        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Links (name, address) VALUES (?, ?)");
            
            stmt.setString(1, name);
            stmt.setString(2, address);
            
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            return false;
        }
        
        return true;
    }

    @Override
    public void getBooksByAuthor(String author) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getLinksByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}