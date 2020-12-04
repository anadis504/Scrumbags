package Scrumbags.database;

import Scrumbags.logic.Book;
import Scrumbags.logic.Link;
import Scrumbags.logic.Podcast;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author tkoukkar
 */
public class Database implements Dao {

    private Library ldb;

    public Database(String path) throws ClassNotFoundException {
        this.ldb = new Library(path);
        try (Connection conn = this.ldb.getConnection()) {
            String ctinx = "CREATE TABLE IF NOT EXISTS ";
            Statement s = conn.createStatement();
            s.execute(ctinx + "Books (name TEXT, author TEXT, year INTEGER, pages INTEGER, isbn TEXT);");
            s.execute(ctinx + "Links (name TEXT, address TEXT UNIQUE);");
            s.execute(ctinx + "podcasts (name TEXT UNIQUE, publisher TEXT, url TEXT, rrs TEXT);");
            s.close();
        } catch (SQLException ex) {
            System.out.println("Virhe luotaessa tietokantatauluja. Yritä käynnistää ohjelma uudestaan.");
        }
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
            System.out.println("Virhe tietokannan käsittelyssä: " + ex.toString());
            return false;
        }

        return true;
    }

    @Override
    public boolean addLink(Link link) {
        String name = link.getName();
        String address = link.getAddress();

        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Links (name, address) VALUES (?, ?)");

            stmt.setString(1, name);
            stmt.setString(2, address);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Virhe tietokannan käsittelyssä: " + ex.getErrorCode());
            return false;
        }

        return true;
    }

    @Override
    public boolean addPodcast(Podcast podcast) {
        String name = podcast.getName();
        String publisher = podcast.getPublisher();
        String url = podcast.getUrl();
        String rrs = podcast.getRrs();

        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Podcasts(name, publisher, url, rrs) VALUES (?, ?, ?, ?)");

            stmt.setString(1, name);
            stmt.setString(2, publisher);
            stmt.setString(3, url);
            stmt.setString(4, rrs);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Virhe tietokannan käsittelyssä: " + ex.toString());
            return false;
        }

        return true;
    }

    @Override
    public boolean removeBook(String isbn, String name) {
        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Books WHERE isbn=? AND name=?");
            stmt.setString(1, isbn);
            stmt.setString(2, name);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Virhe tietokannan käsittelyssä: " + ex.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean removeLink(String url) {
        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Links WHERE address=?");
            stmt.setString(1, url);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Virhe tietokannan käsittelyssä: " + ex.toString());
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
    public ArrayList<Link> getAllLinks() {
        ArrayList<Link> linklist = new ArrayList<>();

        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Links");
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
    public ArrayList<Book> getBooksByName(String name) {
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
            if (booklist.isEmpty()) {
                return null;
            }
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

    @Override
    public ArrayList<Book> getBooksByYear(int year) {
        ArrayList<Book> booklist = new ArrayList<>();
        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books WHERE year=?");
            stmt.setInt(1, year);
            ResultSet res = stmt.executeQuery();
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
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> booklist = new ArrayList<>();

        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Book book = new Book(res.getString("name"), res.getString("author"), res.getString("isbn"), res.getInt("pages"), res.getInt("year"));
                booklist.add(book);
            }
            stmt.close();
            if (booklist.isEmpty()) {
                return null;
            }
            return booklist;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public ArrayList<Podcast> getPodcastsByName(String name) {
        ArrayList<Podcast> podcastlist = new ArrayList<>();
        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Podcasts WHERE name=?");
            stmt.setString(1, name);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Podcast podcast = new Podcast(res.getString("name"), res.getString("publisher"), res.getString("url"), res.getString("rrs"));
                podcastlist.add(podcast);
            }
            stmt.close();
            if (podcastlist.isEmpty()) {
                return null;
            }
            return podcastlist;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<Podcast> getAllPodcasts() {
        ArrayList<Podcast> podcastlist = new ArrayList<>();
        try (Connection conn = this.ldb.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Podcasts");
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Podcast podcast = new Podcast(res.getString("name"), res.getString("publisher"), res.getString("url"), res.getString("rrs"));
                podcastlist.add(podcast);
            }
            stmt.close();
            if (podcastlist.isEmpty()) {
                return null;
            }
            return podcastlist;
        } catch (SQLException ex) {
            return null;
        }
    }
}
