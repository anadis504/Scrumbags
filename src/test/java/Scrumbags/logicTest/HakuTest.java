package Scrumbags.logicTest;

import Scrumbags.logic.*;
import Scrumbags.database.*;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rasmus
 */
public class HakuTest {
    Service service;
    
    public HakuTest() {
        this.service = new Service(new DatabaseFake());
    }
    
    @Test
    public void searchReturnsRightBook() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        ArrayList<Book> booklist = this.service.getBooksByAuthor("Taavi");
        boolean found = false;
        for (Book b: booklist) {
            if (b.getName().equals("ABC") && b.getIsbn().equals("123-14") && b.getPages() == 50 && b.getYear() == 2005) {
                found = true;
            }
        }
        assertEquals(true, found);
    }

    @Test
    public void bookAmountIsRight() {
        this.service.addBook("Aapinen", "Nobody");
        this.service.addBook("Bk", "Nobody");
        this.service.addBook("MAGA", "Nobody");
        assertEquals(3, this.service.getBooksByAuthor("Nobody").size());
    }
    

    @Test
    public void isbnSearchReturnsRightBook() {
        this.service.addBook("ASD", "WASD", "123-14", 50, 13);
        ArrayList<Book> booklist = this.service.getBookByIsbn("123-14");
        boolean found = false;
        for (Book b: booklist) {
            if (b.getName().equals("ASD") && b.getIsbn().equals("123-14") && b.getPages() == 50 && b.getYear() == 13) {
                found = true;
            }
        }
        assertEquals(true, found);
    }

    @Test
    public void isbnSearchFailsIfBookNotAddedYet() {
        assertNull(this.service.getBookByIsbn("123-14"));
    }
    
    
    @Test
    public void yearSearchReturnsRightBook() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        ArrayList<Book> booklist = this.service.getBooksByYear(2005);
        boolean found = false;
        for (Book b: booklist) {
            if (b.getName().equals("ABC") && b.getIsbn().equals("123-14") && b.getPages() == 50 && b.getYear() == 2005) {
                found = true;
            }
        }
        assertEquals(true, found);
    }

    @Test
    public void yearSearchFailsIfBookNotAddedYet() {
        assertNull(this.service.getBooksByYear(1044));
    }
    
    @Test
    public void yearSearchFailsIfYearDoesNotExistsInAnyBook() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        this.service.addBook("nimi", "kirjailija", "isbn1234", 123, 123);
        assertNull(this.service.getBooksByYear(2000));
    }
}
