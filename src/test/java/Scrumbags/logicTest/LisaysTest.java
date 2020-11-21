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
        this.service.addBook("Aapinen", "Tuntematon");
        assertTrue(this.service.addBook("Aapinen", "Tuntematon"));
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
    
//    @Test
//    public void bookmarkAmountIsRight() {
//        this.service.addBook("Aapinen", "Tuntematon");
//        this.service.addLink("Google", "http://www.google.com");
//        this.service.addLink("Omasivu", "http://www.urpo.fi");
//        assertEquals(this.service.getBookList().size()+this.service.getLinkList().size() == 3);
//    }
    
}