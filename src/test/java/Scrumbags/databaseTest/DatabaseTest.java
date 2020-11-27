/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scrumbags.databaseTest;

import Scrumbags.database.*;
import Scrumbags.logic.*;
import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toniramo
 */
public class DatabaseTest {

    private String testDbName = "databaseTest.db";
    private Dao db;

    private Book joulupukinLomakirja = new Book("Joulupukin lomakirja", "Mauri Kunnas", "978-951-1-35993-7", 30, 2020);
    private Book kuinkaPuutKasvavat = new Book("Kuinka puut kasvavat", "Saku Tuominen", "978-951-1-36427-6", 123, 2021);
    private Book pizze = new Book("Pizze", "Saku Tuominen", "978-951-1-29097-1", 62, 2015);

    private Link tira = new Link("Tietorakenteet ja algoritmit", "https://tira-s19.mooc.fi/");
    private Link ohtu = new Link("Ohjelmistotuotanto", "https://ohjelmistotuotanto-hy.github.io/");

    @Before
    public void setUp() throws ClassNotFoundException {
        db = new Database("jdbc:sqlite:" + testDbName);
        db.addBook(joulupukinLomakirja);
        db.addLink(tira);
    }

    @After
    public void tearDown() {
        try {
            File db = new File(testDbName);
            db.delete();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Test
    public void addingNewValidBookReturnsTrue() {
        assertTrue(db.addBook(kuinkaPuutKasvavat));
    }

    @Test
    public void existingBookCanBeFoundByName() {
        assertTrue(db.getBooksByName("Joulupukin lomakirja").contains(joulupukinLomakirja));
    }

    @Test
    public void existingBookCanBeFoundByYear() {
        assertTrue(db.getBooksByYear(2020).contains(joulupukinLomakirja));
    }

    @Test
    public void existingLinkCanBeFoundByName() {
        assertTrue(db.getLinksByName("Tietorakenteet ja algoritmit").contains(tira));
    }

    @Test
    public void afterAddingNewValidBookItCanBeFoundByAuthor() {
        db.addBook(kuinkaPuutKasvavat);
        assertTrue(db.getBooksByAuthor("Saku Tuominen").contains(kuinkaPuutKasvavat));
    }

    @Test
    public void afterAddingNewValidBookItCanBeFoundByISBN() {
        db.addBook(kuinkaPuutKasvavat);
        assertEquals(kuinkaPuutKasvavat, (db.getBookByIsbn("978-951-1-36427-6")));
    }

    @Test
    public void bookCannotBeFoundByAuthorBeforeItIsAdded() {
        db.addBook(pizze);
        assertFalse(db.getBooksByAuthor("Saku Tuominen").contains(kuinkaPuutKasvavat));
    }

    @Test
    public void bookCannotBeFoundByISBNBeforeItIsAdded() {
        assertEquals(null, db.getBookByIsbn("978-951-1-36427-6"));
    }

    @Test
    public void addingExistingBookWithSameISBNReturnsFalse() {
        assertFalse(db.addBook(joulupukinLomakirja));
    }

    @Test
    public void addingExistingBookWithoutISBNReturnsTrue() {
        pizze.setIsbn(null);
        assertTrue(db.addBook(pizze));
        assertTrue(db.addBook(pizze));
    }

    @Test
    public void nonExistingBookCannotBeFoundByName() {
        assertFalse(db.getBooksByName(pizze.getName()).contains(pizze));
    }

    @Test
    public void nonExistingBookCannotBeFoundByYear() {
        assertFalse(db.getBooksByYear(pizze.getYear()).contains(pizze));
    }
    
    @Test
    public void nonExistingLinkCannotBeFoundByName() {
        assertEquals(null, db.getLinksByName(ohtu.getName()));
    }
}
