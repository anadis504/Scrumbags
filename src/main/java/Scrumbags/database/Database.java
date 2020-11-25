
package Scrumbags.database;

import Scrumbags.logic.Book;
import Scrumbags.logic.Link;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



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
    public ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> booklist = new ArrayList<>();
        
        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books WHERE author=?");
            stmt.setString(1, author);
            ResultSet res = stmt.executeQuery();
            // Luodaan Book-olio
            while (res.next()) {
                Book book = new Book(res.getString("name"), res.getString("author"), res.getString("isbn"), res.getInt("pages"), res.getInt("year"));
                booklist.add(book);
            }
            stmt.close();
            if (booklist.isEmpty()) {
                return null;
            }
            return booklist;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<Link> getLinksByName(String name) {
        ArrayList<Link> linklist = new ArrayList<>();

        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Links WHERE name=?");
            stmt.setString(1, name);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Link link = new Link(res.getString("name"), res.getString("address"));
                linklist.add(link);
            }
            stmt.close();
            if (linklist.isEmpty()) {
                return null;
            }
            return linklist;
        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public ArrayList<Book>  getBooksByName(String name) {
        ArrayList<Book> booklist = new ArrayList<>();
        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books WHERE name=?");
            stmt.setString(1, name);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Book book = new Book(res.getString("name"), res.getString("author"), res.getString("isbn"), res.getInt("pages"), res.getInt("year"));
                booklist.add(book);
            }
            stmt.close();
            return booklist;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books WHERE isbn=?");
            stmt.setString(1, isbn);
            ResultSet res = stmt.executeQuery();
            // Luodaan Book-olio mutta ei silloin jos isbn vastaa tyhjää
            while (res.next()) {
                if (res.getString("isbn").equals("---") || res.getString("isbn").equals("")) {
                    continue;
                }
                Book book = new Book(res.getString("name"), res.getString("author"), res.getString("isbn"), res.getInt("pages"), res.getInt("year"));
                return book;
            }
            stmt.close();
            return null;
        } catch (SQLException ex) {
            return null;
        }
    }
}