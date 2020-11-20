package Scrumbags.logicTest;

import Scrumbags.logic.*;
import Scrumbags.database.*;

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
}