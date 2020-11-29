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
* Perustestit tietokantaan lisäämiselle
*/
public class LisaysTest {
    Service service;
    
    public LisaysTest() {
        this.service = new Service(new DatabaseFake());
    }
    
    @Test
    public void addingBookReturnsTrue() {
        assertTrue(this.service.addBook("Aapinen", "Tuntematon"));
    }
    
    @Test
    public void addingLinkReturnsTrue() {
        assertTrue(this.service.addLink("Google", "http://www.google.com"));
    }
    
    @Test
    public void addedBookExistsWithRightName() {
        this.service.addBook("Aapinen2", "Tuntematon2");
        ArrayList<Book> booklist = this.service.getBooksByAuthor("Tuntematon2");
        boolean found = false;
        for (Book b: booklist) {
            if (b.getName().equals("Aapinen2")) {
                found = true;
            }
        }
        assertEquals(true, found);
    }

    @Test
    public void noDuplicateBooksAdded() {
        this.service.addBook("nimi", "kirjailija", "isbn1234", 123, 123);
        assertFalse(this.service.addBook("1", "2", "isbn1234", 3, 4));
    }
}